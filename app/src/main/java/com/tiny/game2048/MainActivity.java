package com.tiny.game2048;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private GameView gameView;
    private TextView scoreView;
    private TextView bestScoreView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // 动态创建界面，不使用XML布局
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#FAF8EF"));
        
        // 创建标题和分数区域
        LinearLayout headerLayout = new LinearLayout(this);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        
        // 创建标题
        TextView titleView = new TextView(this);
        titleView.setText("2048");
        titleView.setTextSize(32);
        titleView.setTextColor(Color.parseColor("#776E65"));
        titleView.setLayoutParams(new LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
        ));
        
        // 创建分数和按钮区域
        LinearLayout scoreButtonLayout = new LinearLayout(this);
        scoreButtonLayout.setOrientation(LinearLayout.VERTICAL);
        
        // 创建分数区域
        LinearLayout scoreLayout = new LinearLayout(this);
        scoreLayout.setOrientation(LinearLayout.HORIZONTAL);
        
        // 当前分数
        scoreView = new TextView(this);
        scoreView.setTextSize(16);
        scoreView.setTextColor(Color.WHITE);
        scoreView.setBackgroundColor(Color.parseColor("#BBADA0"));
        scoreView.setPadding(20, 10, 20, 10);
        scoreView.setText("0");
        
        // 最高分
        bestScoreView = new TextView(this);
        bestScoreView.setTextSize(16);
        bestScoreView.setTextColor(Color.WHITE);
        bestScoreView.setBackgroundColor(Color.parseColor("#BBADA0"));
        bestScoreView.setPadding(20, 10, 20, 10);
        bestScoreView.setText("Best: 0");
        
        scoreLayout.addView(scoreView);
        scoreLayout.addView(bestScoreView);
        
        // 创建重新开始按钮
        Button restartButton = new Button(this);
        restartButton.setText("New Game");
        restartButton.setTextSize(14);
        restartButton.setBackgroundColor(Color.parseColor("#8F7A66"));
        restartButton.setTextColor(Color.WHITE);
        restartButton.setPadding(20, 10, 20, 10);
        
        // 设置按钮点击事件
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameView != null) {
                    gameView.restartGame();
                }
            }
        });
        
        // 将分数和按钮添加到垂直布局
        scoreButtonLayout.addView(scoreLayout);
        scoreButtonLayout.addView(restartButton);
        
        headerLayout.addView(titleView);
        headerLayout.addView(scoreButtonLayout);
        
        // 创建游戏视图
        gameView = new GameView(this);
        gameView.setScoreViews(scoreView, bestScoreView);
        
        layout.addView(headerLayout);
        layout.addView(gameView);
        
        setContentView(layout);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if (gameView != null) {
            gameView.saveGameState();
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (gameView != null) {
            gameView.loadGameState();
        }
    }
}
