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

import android.os.AsyncTask;

import com.jiajunhui.xapp.medialoader.inter.OnRecursionListener;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Taurus on 2016/9/8.
 */
public class RecursionLoader {

    private static void load(File root, FileFilter filter,List<File> result, OnLoadListener onLoadListener){
        if(root!=null && root.isDirectory()){
            File[] files = root.listFiles();
            Queue<File> dirQueue = new LinkedList<>();
            if(files!=null && files.length>0){
                for(File file : files){
                    if(filter.accept(file)){
                        result.add(file);
                        if(onLoadListener!=null){
                            onLoadListener.onItemAdd(file);
                        }
                    }
                    if(file.isDirectory()){
                        dirQueue.offer(file);
                    }
                }

                while (!dirQueue.isEmpty()){
                    File dir = dirQueue.poll();
                    File[] dirFiles = dir.listFiles();
                    if(dirFiles!=null && dirFiles.length>0){
                        for(File file : dirFiles){
                            if(filter.accept(file)){
                                result.add(file);
                                if(onLoadListener!=null){
                                    onLoadListener.onItemAdd(file);
                                }
                            }
                            if(file.isDirectory()){
                                dirQueue.offer(file);
                            }
                        }
                    }
                }

            }
        }
    }


    public static AsyncTask load(LoadParams params, final OnRecursionListener onRecursionListener){
        return AsyncTaskExecutor.executeConcurrently(new AsyncTask<LoadParams, File, List<File>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if(onRecursionListener!=null){
                    onRecursionListener.onStart();
                }
            }

            @Override
            protected List<File> doInBackground(LoadParams... params) {
                List<File> result = new ArrayList<>();
                LoadParams p = params[0];
                load(p.root, p.fileFilter, result, new OnLoadListener() {
                    @Override
                    public void onItemAdd(File file) {
                        publishProgress(file);
                    }
                });
                return result;
            }

            @Override
            protected void onProgressUpdate(File... values) {
                super.onProgressUpdate(values);
                if(onRecursionListener!=null){
                    onRecursionListener.onItemAdd(values[0]);
                }
            }

            @Override
            protected void onPostExecute(List<File> files) {
                super.onPostExecute(files);
                if(onRecursionListener!=null){
                    onRecursionListener.onFinish(files);
                }
            }
        },params);
    }

    public static class LoadParams{
        public File root;
        public FileFilter fileFilter;
    }

    public interface OnLoadListener{
        void onItemAdd(File file);
    }

}
