package com.jiajunhui.xapp.medialoader.bean;

import java.io.Serializable;

/**
 * Created by Taurus on 16/8/28.
 */
public class BaseItem implements Serializable {
    private int id;
    private String displayName;
    private String path;

    public BaseItem() {
    }

    public BaseItem(int id, String displayName, String path) {
        this.id = id;
        this.displayName = displayName;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
