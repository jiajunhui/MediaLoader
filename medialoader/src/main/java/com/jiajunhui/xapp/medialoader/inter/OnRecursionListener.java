package com.jiajunhui.xapp.medialoader.inter;

import java.io.File;
import java.util.List;

/**
 * Created by Taurus on 2017/5/24.
 */

public interface OnRecursionListener {
    void onStart();
    void onItemAdd(File file, int counter);
    void onFinish(List<File> files);
}
