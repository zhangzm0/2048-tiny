# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 基本保留规则
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.view.View

# 保留自定义View的构造方法
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

# 保留触摸事件方法
-keepclassmembers class * {
    public boolean onTouchEvent(android.view.MotionEvent);
}

# 保留onDraw方法
-keepclassmembers class * {
    protected void onDraw(android.graphics.Canvas);
}