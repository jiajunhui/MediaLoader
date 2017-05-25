package com.jiajunhui.xapp.medialoader.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Taurus on 2017/5/25.
 */

public class NullFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return true;
    }
}
