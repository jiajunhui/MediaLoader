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
