package com.jiajunhui.xapp.medialoader;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.util.Log;

import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnFileLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnPhotoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.loader.AbsLoaderCallBack;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Taurus on 2017/5/23.
 */

public class MediaLoader {

    private final String TAG = "MediaLoader";
    private static MediaLoader loader = new MediaLoader();
    private Queue<LoaderTask> mLoadQueue = new LinkedList<>();

    private final int MSG_CODE_LOAD_FINISH = 101;
    private final int MSG_CODE_LOAD_START = 102;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_CODE_LOAD_FINISH:
                    sendEmptyMessage(MSG_CODE_LOAD_START);
                    break;
                case MSG_CODE_LOAD_START:
                    if(mLoadQueue.size()>0){
                        LoaderTask task = mLoadQueue.poll();
                        if(task!=null){
                            queueLoader(task.activity.get(),task.onLoaderCallBack);
                        }
                        Log.d(TAG,"after poll current queue size = " + mLoadQueue.size());
                    }
                    break;
            }
        }
    };

    private MediaLoader(){
    }

    public static MediaLoader getLoader(){
        return loader;
    }

    private void loadMedia(FragmentActivity activity, AbsLoaderCallBack absLoaderCallBack){
        activity.getSupportLoaderManager().restartLoader(0,null,absLoaderCallBack);
    }

    private void load(FragmentActivity activity, OnLoaderCallBack onLoaderCallBack){
        mLoadQueue.offer(new LoaderTask(new WeakReference<>(activity),onLoaderCallBack));
        Log.d(TAG,"after offer current queue size = " + mLoadQueue.size());
        if(mLoadQueue.size()==1){
            mHandler.sendEmptyMessage(MSG_CODE_LOAD_START);
        }
    }

    private void queueLoader(FragmentActivity activity, OnLoaderCallBack onLoaderCallBack){
        loadMedia(activity, new AbsLoaderCallBack(activity,onLoaderCallBack){
            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                super.onLoaderReset(loader);
                mLoadQueue.clear();
                Log.d(TAG,"***onLoaderReset***");
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                super.onLoadFinished(loader, data);
                mHandler.sendEmptyMessage(MSG_CODE_LOAD_FINISH);
                Log.d(TAG,"***onLoaderFinished***");
            }
        });
    }

    public void loadPhotos(FragmentActivity activity, OnPhotoLoaderCallBack onPhotoLoaderCallBack){
        load(activity,onPhotoLoaderCallBack);
    }

    public void loadVideos(FragmentActivity activity, OnVideoLoaderCallBack onVideoLoaderCallBack){
        load(activity,onVideoLoaderCallBack);
    }

    public void loadAudios(FragmentActivity activity, OnAudioLoaderCallBack onAudioLoaderCallBack){
        load(activity,onAudioLoaderCallBack);
    }

    public void loadFiles(FragmentActivity activity, OnFileLoaderCallBack onFileLoaderCallBack){
        load(activity,onFileLoaderCallBack);
    }

    public static class LoaderTask{
        public WeakReference<FragmentActivity> activity;
        public OnLoaderCallBack onLoaderCallBack;

        public LoaderTask(WeakReference<FragmentActivity> activity, OnLoaderCallBack onLoaderCallBack) {
            this.activity = activity;
            this.onLoaderCallBack = onLoaderCallBack;
        }
    }

}
