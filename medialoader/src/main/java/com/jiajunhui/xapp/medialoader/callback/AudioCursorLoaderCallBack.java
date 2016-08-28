package com.jiajunhui.xapp.medialoader.callback;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import com.jiajunhui.xapp.medialoader.base.AbsLoaderCallBack;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;

/**
 * Created by Taurus on 16/8/28.
 */
public class AudioCursorLoaderCallBack extends AbsLoaderCallBack {
    public AudioCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public String[] getSelectProjection() {
        String[] PROJECTION = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DATE_MODIFIED
        };
        return PROJECTION;
    }

    @Override
    public Uri getQueryUri() {
        return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }

}
