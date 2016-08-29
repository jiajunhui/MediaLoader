package com.jiajunhui.xapp.medialoaderdemo;

import android.content.Context;
import android.provider.MediaStore;
import com.jiajunhui.xapp.medialoader.base.BasePhotoCursorLoaderCallBack;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;

/**
 * Created by Taurus on 2016/8/29.
 */
public class MyPhotoCursorLoaderCallBack extends BasePhotoCursorLoaderCallBack {
    public MyPhotoCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public String[] getSelectProjection() {
        String[] PROJECTION = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.DATE_MODIFIED
        };
        return PROJECTION;
    }

    @Override
    public String getSelections() {
        return MediaStore.MediaColumns.SIZE + " > ?" + " and " + MediaStore.Images.Media.WIDTH + " > ? and " + MediaStore.Images.Media.HEIGHT + " > ?";
    }

    @Override
    public String[] getSelectionsArgs() {
        return new String[]{"0","500","500"};
    }
}
