package me.sjun.assholelibrary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

/**
 * default AssView use original ProgressBar with some trick
 * <p/>
 * author shenwenjun
 * date 19/10/2017
 */

public class DefaultAssViewCreator implements AssViewCreator {

    @NonNull
    @Override
    public View create(Context context) {
        // 这里的目的是不使用 Material 的 ProgressBar Style , 因为 api 21 ~ 24 上 ProgressBar 默认的
        // indeterminate drawable 实现有问题， 会强占 Main Looper 所处队列
        ProgressBar progressBar;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            progressBar = new ProgressBar(context,
                null,
                0, // defStyleAttr = 0 时系统才会使用下一个参数
                android.R.style.Widget_Holo_ProgressBar);
        } else {
            progressBar = new ProgressBar(context);
        }
        return progressBar;
    }
}
