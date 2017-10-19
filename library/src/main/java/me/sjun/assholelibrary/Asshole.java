package me.sjun.assholelibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

/**
 * Control AssView show/hide globally whit simple api.
 * author shenwenjun
 * Date 9/20/16.
 */
public final class Asshole {

    private static final int WHAT_SHOW = 1002;
    private static final int WHAT_SHOW_NEW_WINDOW = 1003;
    private static final int WHAT_HIDE = 1004;

    private final ActivityWatcher mActivityWatcher;
    private final AssholeContainerManager mAssholeContainerManager;
    private AssViewCreator mAssViewCreator;
    private boolean mShowing;
    private boolean mShowInNewWindow;

    private final Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_SHOW:
                    performShowAss();
                    break;
                case WHAT_SHOW_NEW_WINDOW:
                    performShowAssNewWindow();
                    break;
                case WHAT_HIDE:
                    performHideAss();
                    break;
            }
        }
    };

    private Asshole() {
        mActivityWatcher = new ActivityWatcher();
        mAssholeContainerManager = new DefaultContainerManager();
        mAssViewCreator = new DefaultAssViewCreator();
    }

    private static Asshole get() {
        return InstanceHolder.INSTANCE;
    }

    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(get().mActivityWatcher);
        get().initActivityWatcher();
    }

    public static void setAssViewCreator(@NonNull AssViewCreator assViewCreator) {
        get().mAssViewCreator = assViewCreator;
    }

    public static void show() {
        get().mMainHandler.sendEmptyMessage(WHAT_SHOW);
    }

    public static void showNewWindow() {
        get().mMainHandler.sendEmptyMessage(WHAT_SHOW_NEW_WINDOW);
    }

    public static void hide() {
        get().mMainHandler.sendEmptyMessage(WHAT_HIDE);
    }

    public static ViewGroup createAssView(Activity activity) {
        return get().mAssholeContainerManager.create(activity, get().mAssViewCreator);
    }


    /* ================================================================= */
    /* ========================      inner       ======================= */
    /* ================================================================= */

    private void initActivityWatcher() {
        mActivityWatcher.setResumedListener(new ActivityWatcher.OnActivityResumedListener() {
            @MainThread
            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                onStateChanged(activity);
            }
        });
    }

    @MainThread
    private void performShowAss() {
        mShowing = true;
        mShowInNewWindow = false;
        onStateChanged(mActivityWatcher.current());
    }

    @MainThread
    private void performShowAssNewWindow() {
        mShowing = true;
        mShowInNewWindow = true;
        Activity currentActivity = mActivityWatcher.current();
        if (currentActivity == null) return;
        AssActivity.show(currentActivity);
    }

    @MainThread
    private void performHideAss() {
        if (mShowInNewWindow) {
            Activity currentActivity = mActivityWatcher.current();
            if (currentActivity == null) return;
            AssActivity.hide(currentActivity);
        } else {
            mShowing = false;
            onStateChanged(mActivityWatcher.current());
        }
    }

    @MainThread
    private void onStateChanged(@Nullable Activity activity) {
        if (mShowInNewWindow) return;

        if (activity == null) return;
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup assHolder = mAssholeContainerManager.find(activity);

        if (mShowing) {
            if (assHolder != null) return;
            assHolder = mAssholeContainerManager.create(activity, mAssViewCreator);
            decorView.addView(assHolder);
        } else {
            if (assHolder == null) return;
            decorView.removeView(assHolder);
        }
    }

    private static final class InstanceHolder {

        private static volatile Asshole INSTANCE = new Asshole();
    }

}
