package com.jiajunhui.xapp.medialoaderdemo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;
import com.jiajunhui.xapp.medialoader.loader.MediaLoader;
import java.util.ArrayList;
import java.util.List;
import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.DISPLAY_NAME;
import static android.provider.MediaStore.Images.Media.WIDTH;
import static android.provider.MediaStore.Images.Media.HEIGHT;

/**
 * Created by Taurus on 2016/8/29.
 */
public class CustomLoadActivity extends AppCompatActivity {

    private TextView tv_photo_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        tv_photo_info = (TextView) findViewById(R.id.tv_photo_info);

        MediaLoader.loadMedia(this,new MyPhotoCursorLoaderCallBack(getApplicationContext(), new OnLoaderCallBack() {
            @Override
            public void onLoadFinish(Loader<Cursor> loader, Cursor data) {
                List<MyPhoto> photos = new ArrayList<>();
                MyPhoto photo;
                while (data.moveToNext()) {
                    photo = new MyPhoto();
                    int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
                    String name = data.getString(data.getColumnIndexOrThrow(DISPLAY_NAME));
                    String path = data.getString(data.getColumnIndexOrThrow(DATA));
                    int width = data.getInt(data.getColumnIndexOrThrow(WIDTH));
                    int height = data.getInt(data.getColumnIndexOrThrow(HEIGHT));
                    photo.setId(imageId);
                    photo.setDisplayName(name);
                    photo.setPath(path);
                    photo.setWidth(width);
                    photo.setHeight(height);
                    photos.add(photo);
                }
                tv_photo_info.setText("宽高均大于500的图片: " + photos.size() + " 张");
            }
        }));
    }
}
