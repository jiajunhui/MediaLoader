package com.jiajunhui.xapp.medialoader.bean;

import java.util.List;

/**
 * Created by Taurus on 2017/5/24.
 */

public class VideoResult extends BaseResult {

    private List<VideoFolder> folders;
    private List<VideoItem> items;

    public VideoResult() {
    }

    public VideoResult(List<VideoFolder> folders, List<VideoItem> items) {
        this.folders = folders;
        this.items = items;
    }

    public VideoResult(List<VideoFolder> folders, List<VideoItem> items,long totalSize) {
        super(totalSize);
        this.folders = folders;
        this.items = items;
    }

    public List<VideoFolder> getFolders() {
        return folders;
    }

    public void setFolders(List<VideoFolder> folders) {
        this.folders = folders;
    }

    public List<VideoItem> getItems() {
        return items;
    }

    public void setItems(List<VideoItem> items) {
        this.items = items;
    }
}
