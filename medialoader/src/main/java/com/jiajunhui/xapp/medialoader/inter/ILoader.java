package com.jiajunhui.xapp.medialoader.inter;

import android.net.Uri;

/**
 * Created by Taurus on 16/8/28.
 */
public interface ILoader {

    String[] getSelectProjection();
    Uri getQueryUri();
    String getSortOrderSql();
    String getSelections();
    String[] getSelectionsArgs();

}
