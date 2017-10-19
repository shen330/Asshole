package me.sjun.assholelibrary;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

/**
 * default asshole container creator use custom ViewGroup {@link HoleContainer}
 * <p/>
 * author shenwenjun
 * date 19/10/2017
 */

public class DefaultContainerManager implements AssholeContainerManager {

    @NonNull
    @Override
    public ViewGroup create(Activity activity, AssViewCreator assViewCreator) {
        HoleContainer container = new HoleContainer(activity);
        container.setId(R.id.asshole_container);
        container.addView(assViewCreator.create(activity));
        return container;
    }

    @Nullable
    @Override
    public ViewGroup find(Activity activity) {
        return activity.findViewById(R.id.asshole_container);
    }
}
