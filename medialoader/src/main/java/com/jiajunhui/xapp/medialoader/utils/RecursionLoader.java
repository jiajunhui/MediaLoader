/*
 * Copyright 2016 jiajunhui
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jiajunhui.xapp.medialoader.utils;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.jiajunhui.xapp.medialoader.bean.MediaType;
import com.jiajunhui.xapp.medialoader.bean.PhotoItem;
import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.jiajunhui.xapp.medialoader.inter.OnLoadListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2016/9/8.
 */
public class RecursionLoader {

    public static void loadMedia(final MediaType mediaType, final OnLoadListener onLoadListener){
        if(onLoadListener !=null){
            postOnStart(onLoadListener);
        }
        final List<VideoItem> videoItems = new ArrayList<>();
        final List<AudioItem> audioItems = new ArrayList<>();
        final List<PhotoItem> photoItems = new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                super.run();
                if(mediaType == MediaType.VIDEO){
                    getVideos(videoItems, Environment.getExternalStorageDirectory());
                    if(onLoadListener !=null){
                        postOnResult(onLoadListener, videoItems);
                    }
                }else if(mediaType == MediaType.AUDIO){
                    getAudios(audioItems, Environment.getExternalStorageDirectory());
                    if(onLoadListener !=null){
                        postOnResult(onLoadListener, audioItems);
                    }
                }else if(mediaType == MediaType.IMAGE){
                    getPhotos(photoItems, Environment.getExternalStorageDirectory());
                    if(onLoadListener !=null){
                        postOnResult(onLoadListener, photoItems);
                    }
                }
            }
        }.start();

    }

    private static void postOnResult(final OnLoadListener onLoadListener, final List<?> videoItems) {
        Looper.prepare();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                onLoadListener.onResultList(videoItems);
            }
        });
        Looper.loop();
    }

    private static void postOnStart(final OnLoadListener onLoadListener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                onLoadListener.onStart();
            }
        });
    }

    private static void getVideos(final List<VideoItem> list, File file){
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();

                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        VideoItem vi = new VideoItem();
                        vi.setDisplayName(file.getName());
                        vi.setPath(file.getAbsolutePath());
                        vi.setSize(file.length());
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getVideos(list, file);
                }
                return false;
            }
        });
    }

    private static void getPhotos(final List<PhotoItem> list, File file){
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (file.length()>0 && (name.equalsIgnoreCase(".jpg")
                            || name.equalsIgnoreCase(".jpeg")
                            || name.equalsIgnoreCase(".png")
                            || name.equalsIgnoreCase(".gif")
                            || name.equalsIgnoreCase(".bmp"))) {
                        PhotoItem vi = new PhotoItem();
                        vi.setDisplayName(file.getName());
                        vi.setPath(file.getAbsolutePath());
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getPhotos(list, file);
                }
                return false;
            }
        });
    }

    private static void getAudios(final List<AudioItem> list, File file){
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp3")
                            || name.equalsIgnoreCase(".wma")
                            || name.equalsIgnoreCase(".wav")
                            || name.equalsIgnoreCase(".aac")) {
                        AudioItem vi = new AudioItem();
                        vi.setDisplayName(file.getName());
                        vi.setPath(file.getAbsolutePath());
                        vi.setSize(file.length());
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getAudios(list, file);
                }
                return false;
            }
        });
    }

}
