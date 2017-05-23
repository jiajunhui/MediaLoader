package com.jiajunhui.xapp.medialoader.callback;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.Loader;

import com.jiajunhui.xapp.medialoader.inter.ILoader;

/**
 * Created by Taurus on 2017/5/23.
 */

public abstract class OnLoaderCallBack implements ILoader {

    public abstract void onLoadFinish(Loader<Cursor> loader, Cursor data);

    public void onLoaderReset(){
    }

    @Override
    public String[] getSelectProjection() {
        return new String[0];
    }

    @Override
    public Uri getQueryUri() {
        return null;
    }

    @Override
    public String getSelections() {
        return null;
    }

    @Override
    public String[] getSelectionsArgs() {
        return new String[0];
    }

    @Override
    public String getSortOrderSql() {
        return MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
    }
}
