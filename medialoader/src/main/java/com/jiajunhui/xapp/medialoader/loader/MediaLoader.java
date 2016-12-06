/*
 * Copyright 2016 jiajunhui
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jiajunhui.xapp.medialoader.loader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import com.jiajunhui.xapp.medialoader.base.AbsLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.AudioCursorLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoFolderLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoFolderLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.PhotoCursorLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.VideoCursorLoaderCallBack;
import com.jiajunhui.xapp.medialoader.utils.UriGetPath;

/**
 * Created by Taurus on 16/8/28.
 */
public class MediaLoader {


    public static void loadMedia(FragmentActivity activity, AbsLoaderCallBack absLoaderCallBack){
        activity.getSupportLoaderManager().restartLoader(0,null,absLoaderCallBack);
    }

    public static void loadPhotoFolders(FragmentActivity activity, OnPhotoFolderLoaderCallBack onPhotoFolderLoaderCallBack){
        loadMedia(activity,new PhotoCursorLoaderCallBack(activity.getApplicationContext(),onPhotoFolderLoaderCallBack));
    }

    public static void loadPhotos(FragmentActivity activity, OnPhotoLoaderCallBack onPhotoLoaderCallBack){
        loadMedia(activity,new PhotoCursorLoaderCallBack(activity.getApplicationContext(),onPhotoLoaderCallBack));
    }

    public static void loadVideoFolders(FragmentActivity activity, OnVideoFolderLoaderCallBack onVideoFolderLoaderCallBack){
        loadMedia(activity,new VideoCursorLoaderCallBack(activity.getApplicationContext(),onVideoFolderLoaderCallBack));
    }

    public static void loadVideos(FragmentActivity activity, OnVideoLoaderCallBack onVideoLoaderCallBack){
        loadMedia(activity,new VideoCursorLoaderCallBack(activity.getApplicationContext(),onVideoLoaderCallBack));
    }

    public static void loadAudios(FragmentActivity activity, OnAudioLoaderCallBack onAudioLoaderCallBack){
        loadMedia(activity,new AudioCursorLoaderCallBack(activity.getApplicationContext(),onAudioLoaderCallBack));
    }

    public static String getPathFromUri(Context context, Uri uri){
        return UriGetPath.getPath(context, uri);
    }

}
