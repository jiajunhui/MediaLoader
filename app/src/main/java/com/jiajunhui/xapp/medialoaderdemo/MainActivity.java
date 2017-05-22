package com.jiajunhui.xapp.medialoaderdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.jiajunhui.xapp.medialoader.bean.MediaType;
import com.jiajunhui.xapp.medialoader.bean.PhotoFolder;
import com.jiajunhui.xapp.medialoader.bean.PhotoItem;
import com.jiajunhui.xapp.medialoader.bean.VideoFolder;
import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoFolderLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoFolderLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.inter.OnLoadListener;
import com.jiajunhui.xapp.medialoader.loader.MediaLoader;
import com.jiajunhui.xapp.medialoader.utils.RecursionLoader;

import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {

    private TextView tv_photo_info;
    private TextView tv_video_info;
    private TextView tv_audio_info;
    private TextView tv_query_info;
    private TextView tv_custom;

    private final int MSG_PHOTO_OVER = 100;
    private final int MSG_VIDEO_OVER = 101;
    private final int MSG_AUDIO_OVER = 102;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_PHOTO_OVER:
                    loadVideoFolders();
                    break;

                case MSG_VIDEO_OVER:
                    loadAudios();
                    break;

                case MSG_AUDIO_OVER:
                    tv_query_info.setText("耗时: " + (System.currentTimeMillis() - start) + " ms");
                    break;
            }
        }
    };
    private long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_photo_info = (TextView) findViewById(R.id.tv_photo_info);
        tv_video_info = (TextView) findViewById(R.id.tv_video_info);
        tv_audio_info = (TextView) findViewById(R.id.tv_audio_info);
        tv_query_info = (TextView) findViewById(R.id.tv_query_info);
        tv_custom = (TextView) findViewById(R.id.tv_custom);

        tv_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomLoadActivity.class);
                startActivity(intent);
            }
        });

        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();

//        loadPhotoFolders();

//        RecursionLoader.loadMedia(MediaType.IMAGE, new OnLoadListener<PhotoItem>() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onResultList(List<PhotoItem> result) {
//                Toast.makeText(MainActivity.this, "image num : " + result.size(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

//    private void loadPhotoFolders() {
//        MediaLoader.loadPhotoFolders(this, new OnPhotoFolderLoaderCallBack() {
//            @Override
//            public void onResultFolders(List<PhotoFolder> folders) {
//                tv_photo_info.setText("图片目录: " + folders.size() + " 个" + " 共: " + folders.get(0).getItems().size() + " 张图片");
//                handler.sendEmptyMessage(MSG_PHOTO_OVER);
//            }
//        });
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void onPermissionSuccess(){
        start = System.currentTimeMillis();
        loadPhotos();
    }

    @PermissionFail(requestCode = 100)
    public void onPermissionFail(){
        Toast.makeText(this, "permission deny", Toast.LENGTH_SHORT).show();
    }

    private void loadPhotos() {
        MediaLoader.loadPhotos(this, new OnPhotoLoaderCallBack() {
            @Override
            public void onResultList(List<PhotoItem> items) {
                tv_photo_info.setText("图片: " + items.size() + " 张");
                handler.sendEmptyMessage(MSG_PHOTO_OVER);
            }
        });
    }

    private void loadAudios() {
        MediaLoader.loadAudios(this, new OnAudioLoaderCallBack() {
            @Override
            public void onResultList(List<AudioItem> items) {
                tv_audio_info.setText("音乐: " + items.size() + " 个");
                handler.sendEmptyMessage(MSG_AUDIO_OVER);
            }
        });
    }

    private void loadVideos() {
        MediaLoader.loadVideos(this, new OnVideoLoaderCallBack() {
            @Override
            public void onResultList(List<VideoItem> items) {
                tv_video_info.setText("视频: " + items.size() + " 个");
                handler.sendEmptyMessage(MSG_VIDEO_OVER);
            }
        });
    }

    private void loadVideoFolders(){
        MediaLoader.loadVideoFolders(this, new OnVideoFolderLoaderCallBack() {
            @Override
            public void onResultFolders(List<VideoFolder> folders, int totalNum) {
                tv_video_info.setText("视频文件夹: " + folders.size() + " 个" + " 共: " + totalNum + " 个视频文件");
                handler.sendEmptyMessage(MSG_VIDEO_OVER);
            }
        });
    }
}
