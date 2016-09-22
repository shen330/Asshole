package me.sjun.assholelibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;

/**
 * Asshole
 * package me.sjun.assholelibrary
 * author shenwenjun
 * Date 9/21/16.
 */
public class HoleContainer extends FrameLayout {

    public HoleContainer(Context context) {
        super(context);
        init();
    }

    public HoleContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HoleContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HoleContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        // 禁止点击屏幕其他区域
        setClickable(true);

        // 防止按返回键退出
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        requestFocusFromTouch();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK;
    }

}
