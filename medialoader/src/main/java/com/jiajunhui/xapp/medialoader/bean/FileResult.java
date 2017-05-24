package com.jiajunhui.xapp.medialoader.bean;

import java.util.List;

/**
 * Created by Taurus on 2017/5/24.
 */

public class FileResult extends BaseResult {

    private List<FileItem> items;

    public FileResult() {
    }

    public FileResult(long totalSize, List<FileItem> items) {
        super(totalSize);
        this.items = items;
    }

    public List<FileItem> getItems() {
        return items;
    }

    public void setItems(List<FileItem> items) {
        this.items = items;
    }
}
