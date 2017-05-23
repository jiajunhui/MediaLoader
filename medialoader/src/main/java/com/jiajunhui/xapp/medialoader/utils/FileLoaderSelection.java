package com.jiajunhui.xapp.medialoader.utils;

import android.provider.MediaStore;

/**
 * Created by Taurus on 2017/5/23.
 */

public class FileLoaderSelection {

    public static String getSelection(String[] fileExtensionNames, String[] fileMIMETypes){
        String selection = null;
        if(fileExtensionNames != null && fileExtensionNames.length > 0){
            StringBuilder extensionBuilder = new StringBuilder();
            for(String extension : fileExtensionNames){
                extensionBuilder.append(MediaStore.Files.FileColumns.DATA).append(" like ").append("%" + extension);
            }
        }
        return selection;
    }

}
