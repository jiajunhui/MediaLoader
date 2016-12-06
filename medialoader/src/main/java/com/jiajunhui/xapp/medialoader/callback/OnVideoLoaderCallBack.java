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

import android.database.Cursor;
import android.support.v4.content.Loader;
import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;
import java.util.ArrayList;
import java.util.List;
import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.DISPLAY_NAME;
import static android.provider.MediaStore.Video.VideoColumns.DURATION;
import static android.provider.MediaStore.MediaColumns.SIZE;

/**
 * Created by Taurus on 16/8/28.
 */
public abstract class OnVideoLoaderCallBack implements OnLoaderCallBack {
    @Override
    public void onLoadFinish(Loader<Cursor> loader, Cursor data) {
        List<VideoItem> result = new ArrayList<>();
        VideoItem item;
        while (data.moveToNext()) {
            item = new VideoItem();
            int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
            String name = data.getString(data.getColumnIndexOrThrow(DISPLAY_NAME));
            String path = data.getString(data.getColumnIndexOrThrow(DATA));
            long duration = data.getLong(data.getColumnIndexOrThrow(DURATION));
            long size = data.getLong(data.getColumnIndexOrThrow(SIZE));
            item.setId(imageId);
            item.setDisplayName(name);
            item.setPath(path);
            item.setDuration(duration);
            item.setSize(size);
            result.add(item);
        }
        onResultList(result);
    }

    public abstract void onResultList(List<VideoItem> items);
}
