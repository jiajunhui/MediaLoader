package com.jiajunhui.xapp.medialoaderdemo;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jiajunhui.xapp.medialoader.MediaLoader;
import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.jiajunhui.xapp.medialoader.bean.BaseFolder;
import com.jiajunhui.xapp.medialoader.bean.FileItem;
import com.jiajunhui.xapp.medialoader.bean.FileType;
import com.jiajunhui.xapp.medialoader.bean.PhotoFolder;
import com.jiajunhui.xapp.medialoader.bean.PhotoItem;
import com.jiajunhui.xapp.medialoader.bean.VideoFolder;
import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnFileLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoLoaderCallBack;

import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {

    private TextView tv_photo_info;
    private TextView tv_video_info;
    private TextView tv_audio_info;
    private TextView tv_file_info;

    private long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_photo_info = (TextView) findViewById(R.id.tv_photo_info);
        tv_video_info = (TextView) findViewById(R.id.tv_video_info);
        tv_audio_info = (TextView) findViewById(R.id.tv_audio_info);
        tv_file_info = (TextView) findViewById(R.id.tv_file_info);

        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void onPermissionSuccess(){
        start = System.currentTimeMillis();
        startLoad();
    }

    private void startLoad() {
        loadPhotos();
        loadAudios();
        loadVideos();
        final StringBuilder mInfos = new StringBuilder();
        MediaLoader.getLoader().loadFiles(MainActivity.this, new OnFileLoaderCallBack(FileType.DOC) {
            @Override
            public void onResult(List<FileItem> items) {
                mInfos.append("doc file : " + items.size()).append("\n");
            }
        });

        MediaLoader.getLoader().loadFiles(MainActivity.this, new OnFileLoaderCallBack(FileType.ZIP) {
            @Override
            public void onResult(List<FileItem> items) {
                mInfos.append("zip file : " + items.size()).append("\n");
            }
        });

        MediaLoader.getLoader().loadFiles(MainActivity.this, new OnFileLoaderCallBack(FileType.APK) {
            @Override
            public void onResult(List<FileItem> items) {
                mInfos.append("apk file : " + items.size()).append("\n");
                mInfos.append("consume time : " + (System.currentTimeMillis() - start)).append("ms");
                tv_file_info.setText(mInfos.toString());
            }
        });
    }

    @PermissionFail(requestCode = 100)
    public void onPermissionFail(){
        Toast.makeText(this, "permission deny", Toast.LENGTH_SHORT).show();
    }

    private void loadPhotos() {
        MediaLoader.getLoader().loadPhotos(this, new OnPhotoLoaderCallBack() {
            @Override
            public void onResult(List<PhotoFolder> photoFolders, List<PhotoItem> photoItems) {
                tv_photo_info.setText("图片: " + photoItems.size() + " 张");
            }

            @Override
            public void onResultTotalSize(long size) {
                super.onResultTotalSize(size);
                System.out.println("photo_size = " + size);
            }
        });
    }

    private void loadAudios() {
        MediaLoader.getLoader().loadAudios(this, new OnAudioLoaderCallBack() {
            @Override
            public void onResult(List<BaseFolder> baseFolders, List<AudioItem> audioItems) {
                tv_audio_info.setText("音乐: " + audioItems.size() + " 个");
            }

            @Override
            public void onResultTotalSize(long size) {
                super.onResultTotalSize(size);
                System.out.println("audio_size = " + size);
            }
        });
    }

    private void loadVideos() {
        MediaLoader.getLoader().loadVideos(this, new OnVideoLoaderCallBack() {
            @Override
            public void onResult(List<VideoFolder> videoFolders, List<VideoItem> videoItems) {
                tv_video_info.setText("视频: " + videoItems.size() + " 个");
            }

            @Override
            public void onResultTotalSize(long size) {
                super.onResultTotalSize(size);
                System.out.println("video_size = " + size);
            }
        });
    }

}
