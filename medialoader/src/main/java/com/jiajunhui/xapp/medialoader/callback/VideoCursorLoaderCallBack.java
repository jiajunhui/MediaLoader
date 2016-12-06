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

package com.jiajunhui.xapp.medialoader.callback;

import android.content.Context;
import android.provider.MediaStore;
import com.jiajunhui.xapp.medialoader.base.BaseVideoCursorLoaderCallBack;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;

/**
 * Created by Taurus on 16/8/28.
 */
public class VideoCursorLoaderCallBack extends BaseVideoCursorLoaderCallBack {
    public VideoCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public String[] getSelectProjection() {
        String[] PROJECTION = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.BUCKET_ID,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.MediaColumns.SIZE,
                MediaStore.Video.Media.DATE_MODIFIED
        };
        return PROJECTION;
    }

    @Override
    public String getSelections() {
        return MediaStore.MediaColumns.SIZE + " > ?";
    }

    @Override
    public String[] getSelectionsArgs() {
        return new String[]{"0"};
    }

}
