package com.jiajunhui.xapp.medialoader.bean;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2016/8/29.
 */
public class PhotoFolder extends BaseFolder{
    private String cover;
    private List<PhotoItem> items = new ArrayList<>();

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<PhotoItem> getItems() {
        return items;
    }

    public void setItems(List<PhotoItem> items) {
        this.items = items;
    }

    public void addItem(PhotoItem item){
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoFolder)) return false;

        PhotoFolder directory = (PhotoFolder) o;

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
