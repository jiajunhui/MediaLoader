package com.jiajunhui.xapp.medialoader.bean;

import java.io.Serializable;

/**
 * Created by Taurus on 2017/5/24.
 */

public class BaseResult implements Serializable {
    private long totalSize;

    public BaseResult() {
    }

    public BaseResult(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
}
