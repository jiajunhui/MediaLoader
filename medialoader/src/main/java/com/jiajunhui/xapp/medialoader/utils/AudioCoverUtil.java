package com.jiajunhui.xapp.medialoader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

/**
 * Created by Taurus on 2017/5/22.
 */

public class AudioCoverUtil {


    /**
     * get audio cover
     * @param filePath
     * @return
     */
    public static Bitmap createAlbumArt(String filePath) {
        Bitmap bitmap = null;
        //能够获取多媒体文件元数据的类
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath); //设置数据源
            byte[] bytes = retriever.getEmbeddedPicture(); //得到字节型数据
            if(bytes!=null){
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length); //转换为图片
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }


}
