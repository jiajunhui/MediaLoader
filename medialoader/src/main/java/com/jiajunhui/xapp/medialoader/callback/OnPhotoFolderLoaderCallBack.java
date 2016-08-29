package com.jiajunhui.xapp.medialoader.callback;

import android.database.Cursor;
import android.support.v4.content.Loader;
import com.jiajunhui.xapp.medialoader.bean.PhotoFolder;
import com.jiajunhui.xapp.medialoader.bean.PhotoItem;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;
import java.util.ArrayList;
import java.util.List;
import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.DISPLAY_NAME;
import static android.provider.MediaStore.Images.Media.BUCKET_ID;
import static android.provider.MediaStore.Images.Media.BUCKET_DISPLAY_NAME;

/**
 * Created by Taurus on 16/8/28.
 */
public abstract class OnPhotoFolderLoaderCallBack implements OnLoaderCallBack {
    @Override
    public void onLoadFinish(Loader<Cursor> loader, Cursor data) {
        List<PhotoFolder> folders = new ArrayList<>();
        if(data == null){
            onResultFolders(folders);
            return;
        }
        PhotoFolder allImagesFolder = new PhotoFolder();
        allImagesFolder.setId("ALL");
        allImagesFolder.setName("ALL Images");
        PhotoFolder folder;
        PhotoItem item;
        while (data.moveToNext()) {
            String folderId = data.getString(data.getColumnIndexOrThrow(BUCKET_ID));
            String folderName = data.getString(data.getColumnIndexOrThrow(BUCKET_DISPLAY_NAME));
            int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
            String name = data.getString(data.getColumnIndexOrThrow(DISPLAY_NAME));
            String path = data.getString(data.getColumnIndexOrThrow(DATA));
            folder = new PhotoFolder();
            folder.setId(folderId);
            folder.setName(folderName);
            item = new PhotoItem(imageId,name,path);
            if(folders.contains(folder)){
                folders.get(folders.indexOf(folder)).addItem(item);
            }else{
                folder.setCover(path);
                folder.addItem(item);
                folders.add(folder);
            }
            allImagesFolder.addItem(item);
        }
        if(allImagesFolder.getItems().size()>0){
            allImagesFolder.setCover(allImagesFolder.getItems().get(0).getPath());
        }
        folders.add(0,allImagesFolder);
        onResultFolders(folders);
    }

    public abstract void onResultFolders(List<PhotoFolder> folders);

}
