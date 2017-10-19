package me.sjun.assholelibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.FrameLayout;

/**
 * Asshole
 * package me.sjun.assholelibrary
 * author shenwenjun
 * Date 9/21/16.
 */
class HoleContainer extends FrameLayout {

    private OnBackPressedListener onBackPressedListener;

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

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
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
        if (keyCode == KeyEvent.KEYCODE_BACK && onBackPressedListener != null) {
            return onBackPressedListener.onBackPressed();
        }
        return true;
    }

    interface OnBackPressedListener {

        boolean onBackPressed();
    }

}
