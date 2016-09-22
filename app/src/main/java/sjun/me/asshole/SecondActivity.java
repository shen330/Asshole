package sjun.me.asshole;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.sjun.assholelibrary.Asshole;

/**
 * Asshole
 * package sjun.me.asshole
 * author shenwenjun
 * Date 9/21/16.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Asshole.hide();
                finish();
            }
        }, 5000);
    }

}
