package com.jiajunhui.xapp.medialoader.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Taurus on 2017/5/24.
 */

public class PhotoFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if(pathname.length()<=0)
            return false;
        String name = pathname.getName();
        int i = name.lastIndexOf('.');
        if (i != -1) {
            name = name.substring(i);
            if (name.equalsIgnoreCase(".jpg")
                    || name.equalsIgnoreCase(".jpeg")
                    || name.equalsIgnoreCase(".png")
                    || name.equalsIgnoreCase(".gif")
                    || name.equalsIgnoreCase(".bmp")) {
                return true;
            }
        }
        return false;
    }
}
