package com.jiajunhui.xapp.medialoader.base;

import android.content.Context;
import android.support.v4.content.CursorLoader;

import com.jiajunhui.xapp.medialoader.inter.ILoader;

/**
 * Created by Taurus on 16/8/28.
 */
public class BaseCursorLoader extends CursorLoader {

    public BaseCursorLoader(Context context, ILoader iLoader) {
        super(context);
        setProjection(iLoader.getSelectProjection());
        setUri(iLoader.getQueryUri());
        setSelection(iLoader.getSelections());
        setSelectionArgs(iLoader.getSelectionsArgs());
        setSortOrder(iLoader.getSortOrderSql());
    }
}
