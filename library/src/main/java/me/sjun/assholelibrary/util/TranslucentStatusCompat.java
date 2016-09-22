package me.sjun.assholelibrary.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * IHere
 * package us.nonda.util
 * author shenwenjun
 * Date 8/27/15.
 */
public class TranslucentStatusCompat {

    private TranslucentStatusCompat() {
    }

    public static void translucentStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }

        Window window = activity.getWindow();
        if (window == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            // 打开下面两个 flag, 这样 decorView 就可以延伸到 status bar 后面。
            // 添加 FLAG_TRANSLUCENT_STATUS 默认会打开这两个， 但是 statusBar 样式会变成 api 19 的样子， 所以这里手动设置 flag
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            // api 19 的样式， 在 status bar 上有一个黑到透明的渐变
            // FLAG_TRANSLUCENT_STATUS 隐性包含了 View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            // View.SYSTEM_UI_FLAG_LAYOUT_STABLE 这两个 flag, 不需要再手动打开了
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }

}
