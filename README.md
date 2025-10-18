# 2048 Tiny - 25KB Android 2048 Game

[![Android](https://img.shields.io/badge/Android-4.0%2B-brightgreen.svg)](https://android.com)
[![APK Size](https://img.shields.io/badge/APK-25KB-blue.svg)](https://github.com/zhangzm0/2048-tiny/releases)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> 🎯 Ultra-lightweight 2048 implementation - only 25KB APK! Smaller than most images!

**[中文](README_zh.md) | English**

## ✨ Features

- 📦 **Tiny Size**: Only 25KB APK - incredibly lightweight
- 🚀 **Zero Dependencies**: Pure native implementation, no third-party libraries
- 🎮 **Full Features**: Complete 2048 gameplay experience
- 💾 **Auto-save**: Automatically saves game progress
- 🏆 **High Score**: Persistent best score tracking
- 📱 **Wide Compatibility**: Supports Android 4.0+ (API 14+)

## 📥 Download & Install

Direct APK download:
[Download latest-release.apk](https://github.com/zhangzm0/2048-tiny/releases/latest)

## 🛠️ Build Instructions

### Requirements
- Android Studio
- Gradle
- Android SDK

### Build Steps
```bash
# Clone repository
git clone https://github.com/zhangzm0/2048-tiny.git

# Build release version
cd 2048-tiny
./gradlew assembleRelease
```

Built APK location: `app/build/outputs/apk/release/app-release.apk`

## 🎯 Technical Highlights

### Optimization Strategies
- ✅ **Pure Code UI**: Zero image resources, all graphics drawn with Canvas
- ✅ **Minimal Code**: Only 2 main classes, ~150 methods
- ✅ **Resource Optimization**: English only + armeabi-v7a architecture
- ✅ **ProGuard**: Code shrinking and resource optimization
- ✅ **No Dependencies**: Completely self-contained implementation

### Core Implementation
```java
// Pure Canvas drawing for game interface
protected void onDraw(Canvas canvas) {
    drawBoard(canvas);
    drawTiles(canvas);
    if (gameOver) drawGameOverOverlay(canvas);
}
```

## 📁 Project Structure
```
2048-tiny/
├── app/
│   ├── src/main/java/com/tiny/game2048/
│   │   ├── MainActivity.java    # Main interface
│   │   └── GameView.java        # Core game logic
│   └── build.gradle             # Minimal build config
```

## 📊 Size Analysis
| Component | Size | Description |
|-----------|------|-------------|
| Code | ~15KB | Highly optimized Java code |
| Resources | ~5KB | Essential icons and strings only |
| Libraries | 0KB | Zero third-party dependencies |
| Other | ~5KB | Manifest and resource tables |
| **Total** | **~25KB** | **Incredibly lightweight** |

## 🎮 How to Play
- ➡️ **Swipe**: Move tiles in four directions
- 🔄 **Merge**: Combine matching numbers to double them
- 🎯 **Goal**: Create a 2048 tile
- 🔁 **Restart**: Start new game anytime
- 💾 **Auto-save**: Resume progress automatically

## 🤝 Contributing
Issues and Pull Requests are welcome!

## 📄 License
MIT License - see [LICENSE](LICENSE) file

---

⭐ If you find this project helpful, please give it a Star!