package me.sjun.assholelibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * creator of AssView
 * <p/>
 * author shenwenjun
 * date 17/10/2017
 */

public interface AssViewCreator {

    @NonNull
    View create(Context context);

}
