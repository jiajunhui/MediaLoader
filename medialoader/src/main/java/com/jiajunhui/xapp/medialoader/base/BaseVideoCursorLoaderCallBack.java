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

package com.jiajunhui.xapp.medialoader.base;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;

/**
 * Created by Taurus on 16/8/28.
 */
public abstract class BaseVideoCursorLoaderCallBack extends AbsLoaderCallBack {
    public BaseVideoCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public Uri getQueryUri() {
        return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }

}
