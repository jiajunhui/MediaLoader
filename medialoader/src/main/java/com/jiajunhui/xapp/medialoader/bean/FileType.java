package com.jiajunhui.xapp.medialoader.bean;

import com.jiajunhui.xapp.medialoader.config.FileLoaderConfig;

/**
 * Created by Taurus on 2017/5/23.
 */

public enum  FileType {

    DOC(new FileProperty(null, FileLoaderConfig.documentMIME)),
    APK(new FileProperty(FileLoaderConfig.apkExtension,null)),
    ZIP(new FileProperty(FileLoaderConfig.zipExtension,null));

    FileProperty property;

    FileType(FileProperty property){
        this.property = property;
    }

    public FileProperty getProperty() {
        return property;
    }

    public void setProperty(FileProperty property) {
        this.property = property;
    }
}
