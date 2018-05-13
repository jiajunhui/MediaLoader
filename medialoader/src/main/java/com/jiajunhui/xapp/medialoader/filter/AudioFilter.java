package com.jiajunhui.xapp.medialoader.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Taurus on 2017/5/24.
 */

public class AudioFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        String name = pathname.getName();
        int i = name.lastIndexOf('.');
        if (i != -1) {
            name = name.substring(i);
            if (name.equalsIgnoreCase(".mp3")
                    || name.equalsIgnoreCase(".wma")
                    || name.equalsIgnoreCase(".wav")
                    || name.equalsIgnoreCase(".flac")
                    || name.equalsIgnoreCase(".amr")
                    || name.equalsIgnoreCase(".aac")) {
                return true;
            }
        }
        return false;
    }
}
