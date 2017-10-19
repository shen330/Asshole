package me.sjun.assholelibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * xxx
 * <p/>
 * author shenwenjun
 * date 19/10/2017
 */

public class AssActivity extends AppCompatActivity {

    private static final String KEY_TYPE = "Ass.START_TYPE";

    private static final int ARG_SHOW = 3001;
    private static final int ARG_HIDE = 3002;

    public static void show(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, AssActivity.class).putExtra(KEY_TYPE, ARG_SHOW));
        activity.overridePendingTransition(0, 0);
    }

    public static void hide(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, AssActivity.class).putExtra(KEY_TYPE, ARG_HIDE));
        activity.overridePendingTransition(0, 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isShowAction(getIntent())) {
            setContentView(Asshole.createAssView(this));
        } else {
            Log.w("ASS", "finish in onCreate, maybe wrong");
            closeActivity();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (isShowAction(intent)) {
            Log.w("ASS", "show in onNewIntent, maybe call show() repetitively. Do nothing.");
        } else {
            closeActivity();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void closeActivity() {
        finish();
        overridePendingTransition(0, 0);
    }

    private static boolean isShowAction(Intent intent) {
        int intExtra = intent.getIntExtra(KEY_TYPE, ARG_HIDE);
        return intExtra == ARG_SHOW;
    }

}
