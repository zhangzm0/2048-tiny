# 2048 Tiny - 仅25KB的安卓版2048游戏

[![Android](https://img.shields.io/badge/Android-4.0%2B-brightgreen.svg)](https://android.com)
[![APK Size](https://img.shields.io/badge/APK-25KB-blue.svg)](https://github.com/zhangzm0/2048-tiny/releases)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> 🎯 极致精简的2048游戏实现，安装包仅25KB！比一张图片还小！

## ✨ 特性

- 📦 **超小体积**: 安装包仅25KB，难以置信的轻量
- 🚀 **零依赖**: 完全原生实现，不依赖任何第三方库
- 🎮 **完整功能**: 包含2048所有核心玩法
- 💾 **进度保存**: 自动保存游戏状态
- 🏆 **最高分记录**: 持久化保存最佳成绩
- 📱 **广泛兼容**: 支持Android 4.0+ (API 14+)

## 📥 下载安装

直接下载APK文件安装：
[下载 latest-release.apk](https://github.com/zhangzm0/2048-tiny/releases/latest)

## 🛠️ 构建说明

### 环境要求
- Android Studio
- Gradle
- Android SDK

### 构建步骤
```bash
# 克隆项目
git clone https://github.com/zhangzm0/2048-tiny.git

# 构建发布版本
cd 2048-tiny
./gradlew assembleRelease
```

构建完成后，APK文件位于：`app/build/outputs/apk/release/app-release.apk`

## 🎯 技术亮点

### 极致优化策略
- ✅ **纯代码绘制UI**: 零图片资源，所有图形用Canvas绘制
- ✅ **代码高度精简**: 仅2个主要类，150个方法
- ✅ **资源极度压缩**: 只保留英语资源和armeabi-v7a架构
- ✅ **ProGuard优化**: 启用代码压缩和资源收缩
- ✅ **零第三方库**: 完全自主实现，无任何外部依赖

### 核心实现
```java
// 使用Canvas纯代码绘制游戏界面
protected void onDraw(Canvas canvas) {
    drawBoard(canvas);
    drawTiles(canvas);
    if (gameOver) drawGameOverOverlay(canvas);
}
```

## 📁 项目结构
```
2048-tiny/
├── app/
│   ├── src/main/java/com/tiny/game2048/
│   │   ├── MainActivity.java    # 主界面
│   │   └── GameView.java        # 游戏核心逻辑
│   └── build.gradle             # 极简构建配置
```

## 📊 体积分析
| 组件 | 大小 | 说明 |
|------|------|------|
| 代码 | ~15KB | 高度优化的Java代码 |
| 资源 | ~5KB | 仅包含应用图标 |
| 库 | 0KB | 零第三方依赖 |
| 其他 | ~5KB | 清单文件和资源表 |
| **总计** | **~25KB** | **难以置信的轻量** |

## 🎮 游戏玩法
- ➡️ **滑动操作**: 上下左右滑动移动方块
- 🔄 **数字合并**: 相同数字碰撞会合并相加
- 🎯 **游戏目标**: 尽可能创造2048方块
- 🔁 **重新开始**: 随时开始新游戏
- 💾 **自动保存**: 退出后自动恢复进度

## 🤝 贡献
欢迎提交Issue和Pull Request！

## 📄 许可证
本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

---

⭐ 如果这个项目对你有帮助，请给它一个Star！