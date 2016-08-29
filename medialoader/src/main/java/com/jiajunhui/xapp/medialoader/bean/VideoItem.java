package com.jiajunhui.xapp.medialoader.bean;


/**
 * Created by Taurus on 16/8/28.
 */
public class VideoItem extends BaseItem {
    private long duration;
    private long size;

    public VideoItem() {
    }

    public VideoItem(int id, String displayName, String path, long duration, long size) {
        super(id, displayName, path);
        this.duration = duration;
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
