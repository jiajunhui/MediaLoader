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

import java.io.Serializable;

/**
 * Created by Taurus on 16/8/28.
 */
public class BaseItem implements Serializable {
    private int id;
    private String displayName;
    private String path;
    private long size;
    private long modified;

    public BaseItem() {
    }

    public BaseItem(int id, String displayName, String path) {
        this(id, displayName, path, 0);
    }

    public BaseItem(int id, String displayName, String path, long size) {
        this(id, displayName, path, size, 0);
    }

    public BaseItem(int id, String displayName, String path, long size, long modified) {
        this.id = id;
        this.displayName = displayName;
        this.path = path;
        this.size = size;
        this.modified = modified;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }
}
