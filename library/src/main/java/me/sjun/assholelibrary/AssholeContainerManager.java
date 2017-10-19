package me.sjun.assholelibrary;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

/**
 * creator of asshole container ViewGroup
 * <p/>
 * author shenwenjun
 * date 19/10/2017
 */

public interface AssholeContainerManager {

    @NonNull
    ViewGroup create(Activity activity, AssViewCreator assViewCreator);

    @Nullable
    ViewGroup find(Activity activity);

}
