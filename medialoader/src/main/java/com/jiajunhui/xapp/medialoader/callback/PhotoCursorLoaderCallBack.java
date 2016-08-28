package com.jiajunhui.xapp.medialoader.callback;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.Loader;
import com.jiajunhui.xapp.medialoader.base.AbsLoaderCallBack;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;

/**
 * Created by Taurus on 16/8/28.
 */
public class PhotoCursorLoaderCallBack extends AbsLoaderCallBack {

    public PhotoCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        super.onLoadFinished(loader, data);

    }

    @Override
    public Uri getQueryUri() {
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    @Override
    public String[] getSelectProjection() {
        String[] PROJECTION = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_MODIFIED
        };
        return PROJECTION;
    }

}
