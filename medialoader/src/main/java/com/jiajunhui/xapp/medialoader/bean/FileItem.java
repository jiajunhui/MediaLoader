package com.jiajunhui.xapp.medialoader.bean;

/**
 * Created by Taurus on 2017/5/23.
 */

public class FileItem extends BaseItem {

    private String mime;
    private long size;

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
