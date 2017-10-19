package me.sjun.assholelibrary;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import me.sjun.assholelibrary.util.SimpleActivityLifecycleCallbacks;

/**
 * hold current activity with {@link WeakReference}
 * <p/>
 * author shenwenjun
 * date 19/10/2017
 */

final class ActivityWatcher extends SimpleActivityLifecycleCallbacks {

    interface OnActivityResumedListener {

        @MainThread
        void onActivityResumed(@NonNull Activity activity);
    }

    private final WeakHashMap<Integer, Activity> map = new WeakHashMap<>();
    private OnActivityResumedListener mResumedListener;

    public void setResumedListener(OnActivityResumedListener resumedListener) {
        mResumedListener = resumedListener;
    }

    @Nullable
    public Activity current() {
        return map.get(0);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        map.put(0, activity);
        if (mResumedListener != null) mResumedListener.onActivityResumed(activity);
    }

}
