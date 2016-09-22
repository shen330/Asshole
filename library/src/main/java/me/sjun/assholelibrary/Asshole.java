package me.sjun.assholelibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.WeakHashMap;

import me.sjun.assholelibrary.util.SimpleActivityLifecycleCallbacks;

/**
 * Sample
 * package co.nonda.sample
 * author shenwenjun
 * Date 9/20/16.
 */
public final class Asshole extends SimpleActivityLifecycleCallbacks {

    private boolean mShowing;

    private Handler mMainH;

    private WeakHashMap<Integer, Activity> map;

    private final Runnable showRunnable = new Runnable() {
        @Override
        public void run() {
            mShowing = true;
            onStateChanged();
        }
    };

    private final Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            mShowing = false;
            onStateChanged();
        }
    };

    private Asshole() {
        map = new WeakHashMap<>();
        mMainH = new Handler(Looper.getMainLooper());
    }

    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(get());
    }

    public static void show() {
        get().showInternal();
    }

    public static void hide() {
        get().hideInternal();
    }

    @Override
    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        map.put(0, activity);
        onStateChanged();
    }

    private void showInternal() {
        mMainH.post(showRunnable);
    }

    private void hideInternal() {
        mMainH.post(hideRunnable);
    }

    private void onStateChanged() {
        Activity activity = map.get(0);
        if (activity == null) {
            Log.w("Asshole", "onStateChanged called when activity is not ready ~");
            return;
        }
        ViewGroup container = (ViewGroup) activity.getWindow().getDecorView();
        View pb = activity.findViewById(R.id.asshole_container);

        if (mShowing) {
            if (pb != null) {
                return;
            }
            pb = create(activity);
            container.addView(pb);
        } else {
            if (pb == null) {
                return;
            }
            container.removeView(pb);
        }
    }

    private static View create(Context context) {
        HoleContainer container = new HoleContainer(context);
        container.setId(R.id.asshole_container);

        ProgressBar progressBar = new ProgressBar(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
        );
        lp.gravity = Gravity.CENTER;

        container.addView(progressBar, lp);
        return container;
    }

    private static Asshole get() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder {
        private static volatile Asshole INSTANCE = new Asshole();
    }

}
