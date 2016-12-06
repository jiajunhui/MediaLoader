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

package com.jiajunhui.xapp.medialoader.base;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import com.jiajunhui.xapp.medialoader.inter.ILoader;
import com.jiajunhui.xapp.medialoader.inter.OnLoaderCallBack;
import java.lang.ref.WeakReference;

/**
 * Created by Taurus on 16/8/28.
 */
public abstract class AbsLoaderCallBack implements LoaderManager.LoaderCallbacks<Cursor>,ILoader {

    private WeakReference<Context> context;
    private OnLoaderCallBack onLoaderCallBack;

    public AbsLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack){
        this.context = new WeakReference<>(context);
        this.onLoaderCallBack = onLoaderCallBack;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new BaseCursorLoader(context.get(),this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(onLoaderCallBack !=null){
            onLoaderCallBack.onLoadFinish(loader,data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public abstract String[] getSelectProjection();

    @Override
    public abstract Uri getQueryUri();

    @Override
    public String getSortOrderSql(){
        return MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
    }

    @Override
    public String getSelections() {
        return null;
    }

    @Override
    public String[] getSelectionsArgs() {
        return null;
    }
}
