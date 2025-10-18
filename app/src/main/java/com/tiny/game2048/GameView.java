package com.tiny.game2048;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameView extends View {
    private int[][] board = new int[4][4];
    private int score = 0;
    private int bestScore = 0;
    private boolean gameOver = false;
    private Paint paint;
    private TextView scoreView;
    private TextView bestScoreView;
    private float startX, startY;

    // 颜色配置
    private final int[] tileColors = {
        Color.parseColor("#CDC1B4"), // 0
        Color.parseColor("#EEE4DA"), // 2
        Color.parseColor("#EDE0C8"), // 4
        Color.parseColor("#F2B179"), // 8
        Color.parseColor("#F59563"), // 16
        Color.parseColor("#F67C5F"), // 32
        Color.parseColor("#F65E3B"), // 64
        Color.parseColor("#EDCF72"), // 128
        Color.parseColor("#EDCC61"), // 256
        Color.parseColor("#EDC850"), // 512
        Color.parseColor("#EDC53F"), // 1024
        Color.parseColor("#EDC22E")  // 2048
    };

    // SharedPreferences 键值
    private static final String PREFS_NAME = "Game2048Prefs";
    private static final String KEY_BOARD = "board";
    private static final String KEY_SCORE = "score";
    private static final String KEY_BEST_SCORE = "bestScore";

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        loadGameState();

        // 如果加载的状态是空的，则开始新游戏
        if (isBoardEmpty()) {
            startGame();
        }
    }

    public void setScoreViews(TextView scoreView, TextView bestScoreView) {
        this.scoreView = scoreView;
        this.bestScoreView = bestScoreView;
        updateScoreDisplay();
    }

    private void startGame() {
        // 初始化棋盘
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
        score = 0;
        gameOver = false;
        updateScoreDisplay();
        addRandomTile();
        addRandomTile();
        invalidate();
    }

    public void restartGame() {
        startGame();
    }

    private void addRandomTile() {
        int emptyCells = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) emptyCells++;
            }
        }

        if (emptyCells == 0) return;

        int position = (int)(Math.random() * emptyCells);
        int value = Math.random() < 0.9 ? 2 : 4;

        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    if (count == position) {
                        board[i][j] = value;
                        return;
                    }
                    count++;
                }
            }
        }
    }

    private void updateScoreDisplay() {
        if (scoreView != null) {
            scoreView.setText(String.valueOf(score));
        }
        if (bestScoreView != null) {
            bestScoreView.setText("Best: " + bestScore);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawTiles(canvas);

        // 如果游戏结束，绘制蒙版和游戏结束文字
        if (gameOver) {
            drawGameOverOverlay(canvas);
        }
    }

    private void drawBoard(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int padding = 10;
        int tileSize = (size - padding * 5) / 4;

        // 绘制背景
        paint.setColor(Color.parseColor("#BBADA0"));
        canvas.drawRoundRect(0, 0, size, size, 10, 10, paint);

        // 绘制格子
        paint.setColor(Color.parseColor("#CDC1B4"));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int left = padding + j * (tileSize + padding);
                int top = padding + i * (tileSize + padding);
                canvas.drawRoundRect(left, top, left + tileSize, top + tileSize, 5, 5, paint);
            }
        }
    }

    private void drawTiles(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int padding = 10;
        int tileSize = (size - padding * 5) / 4;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    int left = padding + j * (tileSize + padding);
                    int top = padding + i * (tileSize + padding);

                    // 绘制方块背景
                    int colorIndex = (int)(Math.log(board[i][j]) / Math.log(2));
                    if (colorIndex >= tileColors.length) colorIndex = tileColors.length - 1;
                    paint.setColor(tileColors[colorIndex]);
                    canvas.drawRoundRect(left, top, left + tileSize, top + tileSize, 5, 5, paint);

                    // 绘制数字
                    paint.setColor(board[i][j] <= 4 ? Color.parseColor("#776E65") : Color.parseColor("#F9F6F2"));
                    paint.setTextSize(tileSize / 3);
                    String text = String.valueOf(board[i][j]);
                    Rect bounds = new Rect();
                    paint.getTextBounds(text, 0, text.length(), bounds);
                    float textWidth = paint.measureText(text);
                    float textHeight = bounds.height();
                    canvas.drawText(text, left + (tileSize - textWidth) / 2, 
									top + (tileSize + textHeight) / 2, paint);
                }
            }
        }
    }

    private void drawGameOverOverlay(Canvas canvas) {
		int width = getWidth();
		int height = getHeight();
		int size = Math.min(width, height);

		// 绘制半透明黑色蒙版
		paint.setColor(Color.argb(200, 0, 0, 0));
		canvas.drawRect(0, 0, size, size, paint);

		// 绘制游戏结束文字
		paint.setColor(Color.WHITE);
		paint.setTextSize(48);
		String gameOverText = "Game Over";
		Rect bounds = new Rect();
		paint.getTextBounds(gameOverText, 0, gameOverText.length(), bounds);
		float textWidth = paint.measureText(gameOverText);
		canvas.drawText(gameOverText, (size - textWidth) / 2, size / 2 - 50, paint);

		// 修改提示文字，提示使用按钮重新开始
		paint.setTextSize(24);
		String restartText = "Use 'New Game' button to restart";
		float restartWidth = paint.measureText(restartText);
		canvas.drawText(restartText, (size - restartWidth) / 2, size / 2 + 20, paint);
	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameOver) {
            // 游戏结束时，不再响应滑动触摸，但保留按钮功能
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                float endY = event.getY();
                handleSwipe(startX, startY, endX, endY);
                break;
        }
        return true;
    }

    private void handleSwipe(float startX, float startY, float endX, float endY) {
        float dx = endX - startX;
        float dy = endY - startY;

        if (Math.abs(dx) > Math.abs(dy)) {
            // 水平滑动
            if (Math.abs(dx) > 50) { // 最小滑动距离
                if (dx > 0) {
                    move(Direction.RIGHT);
                } else {
                    move(Direction.LEFT);
                }
            }
        } else {
            // 垂直滑动
            if (Math.abs(dy) > 50) { // 最小滑动距离
                if (dy > 0) {
                    move(Direction.DOWN);
                } else {
                    move(Direction.UP);
                }
            }
        }
    }

    // 定义方向枚举
    private enum Direction {
        LEFT, RIGHT, UP, DOWN
		}

    // 统一的移动方法
    private void move(Direction direction) {
        boolean moved = false;

        for (int i = 0; i < 4; i++) {
            int[] line = new int[4];

            // 根据方向提取一行或一列
            switch (direction) {
                case LEFT:
                case RIGHT:
                    // 提取行
                    for (int j = 0; j < 4; j++) {
                        line[j] = board[i][j];
                    }
                    break;
                case UP:
                case DOWN:
                    // 提取列
                    for (int j = 0; j < 4; j++) {
                        line[j] = board[j][i];
                    }
                    break;
            }

            // 如果需要反转行/列（RIGHT和DOWN方向）
            if (direction == Direction.RIGHT || direction == Direction.DOWN) {
                reverseArray(line);
            }

            // 移动和合并
            boolean lineMoved = moveAndMergeLine(line);

            // 如果需要反转回来
            if (direction == Direction.RIGHT || direction == Direction.DOWN) {
                reverseArray(line);
            }

            // 将处理后的行/列放回棋盘
            switch (direction) {
                case LEFT:
                case RIGHT:
                    for (int j = 0; j < 4; j++) {
                        if (board[i][j] != line[j]) {
                            moved = true;
                        }
                        board[i][j] = line[j];
                    }
                    break;
                case UP:
                case DOWN:
                    for (int j = 0; j < 4; j++) {
                        if (board[j][i] != line[j]) {
                            moved = true;
                        }
                        board[j][i] = line[j];
                    }
                    break;
            }
        }

        if (moved) {
            addRandomTile();
            updateScoreDisplay();

            // 检查游戏是否结束
            if (isGameOver()) {
                gameOver = true;
            }

            invalidate();
            saveGameState();
        }
    }

    // 移动并合并一行
    private boolean moveAndMergeLine(int[] line) {
        boolean moved = false;

        // 第一步：移动所有非零元素到前面
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < 4; readIndex++) {
            if (line[readIndex] != 0) {
                if (readIndex != writeIndex) {
                    line[writeIndex] = line[readIndex];
                    line[readIndex] = 0;
                    moved = true;
                }
                writeIndex++;
            }
        }

        // 第二步：合并相邻的相同数字
        for (int i = 0; i < 3; i++) {
            if (line[i] != 0 && line[i] == line[i + 1]) {
                line[i] *= 2;
                line[i + 1] = 0;
                score += line[i];
                moved = true;

                // 更新最高分
                if (score > bestScore) {
                    bestScore = score;
                }

                // 跳过下一个元素，因为它已经被合并
                i++;
            }
        }

        // 第三步：再次移动非零元素（处理合并后产生的空格）
        writeIndex = 0;
        for (int readIndex = 0; readIndex < 4; readIndex++) {
            if (line[readIndex] != 0) {
                if (readIndex != writeIndex) {
                    line[writeIndex] = line[readIndex];
                    line[readIndex] = 0;
                    moved = true;
                }
                writeIndex++;
            }
        }

        return moved;
    }

    // 反转数组
    private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    // 检查游戏是否结束
    private boolean isGameOver() {
        // 检查是否有空格
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        // 检查是否有可合并的相邻格子
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 检查右边
                if (j < 3 && board[i][j] == board[i][j + 1]) {
                    return false;
                }
                // 检查下边
                if (i < 3 && board[i][j] == board[i + 1][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // 检查棋盘是否为空
    private boolean isBoardEmpty() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 保存游戏状态
    public void saveGameState() {
        SharedPreferences prefs = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // 保存棋盘状态
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardString.append(board[i][j]).append(",");
            }
        }
        editor.putString(KEY_BOARD, boardString.toString());

        // 保存分数
        editor.putInt(KEY_SCORE, score);
        editor.putInt(KEY_BEST_SCORE, bestScore);

        editor.apply();
    }

    // 加载游戏状态
    public void loadGameState() {
        SharedPreferences prefs = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // 加载棋盘状态
        String boardString = prefs.getString(KEY_BOARD, "");
        if (!boardString.isEmpty()) {
            String[] values = boardString.split(",");
            int index = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (index < values.length) {
                        board[i][j] = Integer.parseInt(values[index]);
                        index++;
                    }
                }
            }
        }

        // 加载分数
        score = prefs.getInt(KEY_SCORE, 0);
        bestScore = prefs.getInt(KEY_BEST_SCORE, 0);

        // 检查游戏是否结束
        if (isGameOver()) {
            gameOver = true;
        }

        updateScoreDisplay();
        invalidate();
    }
}
