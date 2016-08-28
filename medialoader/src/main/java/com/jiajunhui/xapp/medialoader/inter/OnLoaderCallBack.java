package com.jiajunhui.xapp.medialoader.inter;

import android.database.Cursor;
import android.support.v4.content.Loader;

/**
 * Created by Taurus on 16/8/28.
 */
public interface OnLoaderCallBack {
    void onLoadFinish(Loader<Cursor> loader, Cursor data);
}
