package com.jiajunhui.xapp.medialoader.inter;

import java.util.List;

/**
 * Created by Taurus on 2016/9/8.
 */
public interface OnLoadListener<T> {
    void onStart();
    void onResultList(List<T> result);
}
