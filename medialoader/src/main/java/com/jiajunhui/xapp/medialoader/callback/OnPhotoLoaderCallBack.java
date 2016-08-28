package com.jiajunhui.xapp.medialoader.callback;

import android.database.Cursor;
import android.support.v4.content.Loader;
import com.jiajunhui.xapp.medialoader.bean.PhotoItem;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;
import java.util.ArrayList;
import java.util.List;
import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.DISPLAY_NAME;

/**
 * Created by Taurus on 16/8/28.
 */
public abstract class OnPhotoLoaderCallBack implements OnLoaderCallBack {
    @Override
    public void onLoadFinish(Loader<Cursor> loader, Cursor data) {
        List<PhotoItem> result = new ArrayList<>();
        PhotoItem item;
        while (data.moveToNext()) {
            item = new PhotoItem();
            int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
            String name = data.getString(data.getColumnIndexOrThrow(DISPLAY_NAME));
            String path = data.getString(data.getColumnIndexOrThrow(DATA));
            item.setId(imageId);
            item.setDisplayName(name);
            item.setPath(path);
            result.add(item);
        }
        onResultList(result);
    }

    public abstract void onResultList(List<PhotoItem> items);
}
