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

package com.jiajunhui.xapp.medialoader.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2016/8/29.
 */
public class VideoFolder extends BaseFolder {
    private List<VideoItem> items = new ArrayList<>();

    public List<VideoItem> getItems() {
        return items;
    }

    public void setItems(List<VideoItem> items) {
        this.items = items;
    }

    public void addItem(VideoItem item){
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoFolder)) return false;

        VideoFolder directory = (VideoFolder) o;

        boolean hasId = !TextUtils.isEmpty(getId());
        boolean otherHasId = !TextUtils.isEmpty(directory.getId());

        if (hasId && otherHasId) {
            if (!TextUtils.equals(getId(), directory.getId())) {
                return false;
            }

            return TextUtils.equals(getName(), directory.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (TextUtils.isEmpty(getId())) {
            if (TextUtils.isEmpty(getName())) {
                return 0;
            }

            return getName().hashCode();
        }

        int result = getId().hashCode();

        if (TextUtils.isEmpty(getName())) {
            return result;
        }

        result = 31 * result + getName().hashCode();
        return result;
    }
}
