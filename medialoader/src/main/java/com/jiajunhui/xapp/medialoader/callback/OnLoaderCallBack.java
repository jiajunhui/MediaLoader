package com.jiajunhui.xapp.medialoader.callback;

import android.database.Cursor;
import android.support.v4.content.Loader;

import com.jiajunhui.xapp.medialoader.inter.ILoader;

/**
 * Created by Taurus on 2017/5/23.
 */

public abstract class OnLoaderCallBack implements ILoader {

    public abstract void onLoadFinish(Loader<Cursor> loader, Cursor data);

    public void onLoaderReset(){
    }

}
