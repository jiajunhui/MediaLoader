package com.jiajunhui.xapp.medialoader.bean;

/**
 * Created by Taurus on 2017/5/23.
 */

public class FileItem extends BaseItem {

    private String mime;
    private boolean checked;

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
