package sjun.me.asshole;

import android.app.Application;

import me.sjun.assholelibrary.Asshole;

/**
 * Asshole
 * package sjun.me.asshole
 * author shenwenjun
 * Date 9/21/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Asshole.init(this);
    }

}
