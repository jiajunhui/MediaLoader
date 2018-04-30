package com.jiajunhui.xapp.medialoaderdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiajunhui.xapp.medialoader.MediaLoader;
import com.jiajunhui.xapp.medialoader.bean.AudioResult;
import com.jiajunhui.xapp.medialoader.bean.FileResult;
import com.jiajunhui.xapp.medialoader.bean.FileType;
import com.jiajunhui.xapp.medialoader.bean.PhotoResult;
import com.jiajunhui.xapp.medialoader.bean.VideoResult;
import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnFileLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;

public class TestFragment extends Fragment {

    private final String TAG = "TestFragment";

    private TextView tv_photo_info;
    private TextView tv_video_info;
    private TextView tv_audio_info;
    private TextView tv_file_info;
    private TextView tv_traversal_info;

    private long start;

    private View mRootView;

    public static TestFragment createInstance(FragmentManager fragmentManager){
        return new TestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_test, null);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_photo_info = (TextView) mRootView.findViewById(R.id.tv_photo_info);
        tv_video_info = (TextView) mRootView.findViewById(R.id.tv_video_info);
        tv_audio_info = (TextView) mRootView.findViewById(R.id.tv_audio_info);
        tv_file_info = (TextView) mRootView.findViewById(R.id.tv_file_info);
        tv_traversal_info = (TextView) mRootView.findViewById(R.id.tv_traversal_info);

        start = System.currentTimeMillis();
        startLoad();
    }

    private void startLoad() {
        loadPhotos();
        loadAudios();
        loadVideos();
        final StringBuilder mInfos = new StringBuilder();
        MediaLoader.getLoader().loadFiles(getActivity(), new OnFileLoaderCallBack(FileType.DOC) {
            @Override
            public void onResult(FileResult result) {
                mInfos.append("doc file : " + result.getItems().size()).append("\n");
            }
        });

        MediaLoader.getLoader().loadFiles(getActivity(), new OnFileLoaderCallBack(FileType.ZIP) {
            @Override
            public void onResult(FileResult result) {
                mInfos.append("zip file : " + result.getItems().size()).append("\n");
            }
        });

        MediaLoader.getLoader().loadFiles(getActivity(), new OnFileLoaderCallBack(FileType.APK) {
            @Override
            public void onResult(FileResult result) {
                mInfos.append("apk file : " + result.getItems().size()).append("\n");
                mInfos.append("consume time : " + (System.currentTimeMillis() - start)).append("ms");
                tv_file_info.setText(mInfos.toString());
            }
        });
    }

    private void loadPhotos() {
        MediaLoader.getLoader().loadPhotos(getActivity(), new OnPhotoLoaderCallBack() {
            @Override
            public void onResult(PhotoResult result) {
                Log.d(TAG,"onResult photo ...");
                tv_photo_info.setText("图片: " + result.getItems().size() + " 张");
            }
        });
    }

    private void loadAudios() {
        MediaLoader.getLoader().loadAudios(getActivity(), new OnAudioLoaderCallBack() {
            @Override
            public void onResult(AudioResult result) {
                Log.d(TAG,"onResult audio ...");
                tv_audio_info.setText("音乐: " + result.getItems().size() + " 个");
            }
        });
    }

    private void loadVideos() {
        MediaLoader.getLoader().loadVideos(getActivity(), new OnVideoLoaderCallBack() {
            @Override
            public void onResult(VideoResult result) {
                Log.d(TAG,"onResult video ...");
                tv_video_info.setText("视频: " + result.getItems().size() + " 个");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
