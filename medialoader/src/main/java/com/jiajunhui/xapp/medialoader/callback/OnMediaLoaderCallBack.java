package com.jiajunhui.xapp.medialoader.callback;

import android.provider.MediaStore;

import java.util.List;

/**
 * Created by Taurus on 2017/5/23.
 */

public abstract class OnMediaLoaderCallBack<Folder,Item> extends BaseLoaderCallBack {

    public abstract void onResult(List<Folder> folders, List<Item> items);

    @Override
    public String getSelections() {
        return MediaStore.MediaColumns.SIZE + " > ?";
    }

    @Override
    public String[] getSelectionsArgs() {
        return new String[]{"0"};
    }

}
