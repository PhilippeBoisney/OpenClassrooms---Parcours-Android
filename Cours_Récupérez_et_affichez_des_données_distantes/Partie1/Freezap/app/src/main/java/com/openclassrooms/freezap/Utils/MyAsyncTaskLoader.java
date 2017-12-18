package com.openclassrooms.freezap.Utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Philippe on 18/12/2017.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<Long> {

    public MyAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public Long loadInBackground() {
        return Utils.executeLongActionDuring7seconds();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


}
