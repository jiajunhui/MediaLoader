package com.jiajunhui.xapp.medialoaderdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.jiajunhui.xapp.medialoader.bean.PhotoItem;
import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.loader.MediaLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_photo_info;
    private TextView tv_video_info;
    private TextView tv_audio_info;
    private TextView tv_query_info;

    private final int MSG_PHOTO_OVER = 100;
    private final int MSG_VIDEO_OVER = 101;
    private final int MSG_AUDIO_OVER = 102;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_PHOTO_OVER:
                    loadVideos();
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

        start = System.currentTimeMillis();

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
}
