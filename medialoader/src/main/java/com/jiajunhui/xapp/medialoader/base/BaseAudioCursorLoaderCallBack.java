package com.jiajunhui.xapp.medialoader.base;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;

/**
 * Created by Taurus on 16/8/28.
 */
public abstract class BaseAudioCursorLoaderCallBack extends AbsLoaderCallBack {
    public BaseAudioCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public Uri getQueryUri() {
        return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }

}
