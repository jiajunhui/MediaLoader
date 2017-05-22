package com.jiajunhui.xapp.medialoader.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;

/**
 * Created by Taurus on 2017/5/22.
 */

public class VideoThumbnailUtil {

    /**
     * get video thumb from path
     * @param path
     * @return
     */
    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime();
    }

    /**
     * get thumb from system ThumbnailUtils
     * @param path
     * @param kind such as MINI_KIND、MICRO_KIND、FULL_SCREEN_KIND.
     * @return
     */
    public static Bitmap getVideoThumb(String path, int kind) {
        return ThumbnailUtils.createVideoThumbnail(path, kind);
    }

}
