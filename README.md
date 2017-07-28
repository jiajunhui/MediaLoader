MediaLoader
====
Use this library , you can load pictures,videos,audios very fast in Phone Storage.
<br>
you can visit my site : [jiajunhui.cn](http://www.jiajunhui.cn)
# Dependency
```gradle
dependencies {
    compile 'com.jiajunhui.xapp.medialoader:medialoader:1.0.0'
}
```
###release
[release jar](https://github.com/jiajunhui/MediaLoader/blob/master/release/medialoader.jar)
Usage
====
###add permission

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```
<br>
In android M device , please pay attention to the Runtime Permission.
###Default Loader
####Load Images
load images folders
<br>

```java
MediaLoader.loadPhotoFolders(this, new OnPhotoFolderLoaderCallBack() {
            @Override
            public void onResultFolders(List<PhotoFolder> folders) {
                
            }
        });
```
<br>
load all images
<br>

```java
MediaLoader.loadPhotos(this, new OnPhotoLoaderCallBack() {
            @Override
            public void onResultList(List<PhotoItem> items) {
                
            }
        });
```
####Load Videos
load videos folders
<br>

```java
MediaLoader.loadVideoFolders(this, new OnVideoFolderLoaderCallBack() {
            @Override
            public void onResultFolders(List<VideoFolder> folders, int totalNum) {
                
            }
        });
```
<br>
load all videos
<br>

```java
MediaLoader.loadVideos(this, new OnVideoLoaderCallBack() {
            @Override
            public void onResultList(List<VideoItem> items) {
                
            }
        });
```
####Load Audios
load all audios
<br>

```java
MediaLoader.loadAudios(this, new OnAudioLoaderCallBack() {
            @Override
            public void onResultList(List<AudioItem> items) {
                
            }
        });
```
Custom Loader
====

```java
public class MyPhotoCursorLoaderCallBack extends BasePhotoCursorLoaderCallBack {
    public MyPhotoCursorLoaderCallBack(Context context, OnLoaderCallBack onLoaderCallBack) {
        super(context, onLoaderCallBack);
    }

    @Override
    public String[] getSelectProjection() {
        String[] PROJECTION = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.DATE_MODIFIED
        };
        return PROJECTION;
    }

    @Override
    public String getSelections() {
        return MediaStore.MediaColumns.SIZE + " > ?" + " and " + MediaStore.Images.Media.WIDTH + " > ? and " + MediaStore.Images.Media.HEIGHT + " > ?";
    }

    @Override
    public String[] getSelectionsArgs() {
        return new String[]{"0","500","500"};
    }
}
```
<br>

```java
MediaLoader.loadMedia(this,new MyPhotoCursorLoaderCallBack(getApplicationContext(), new OnLoaderCallBack() {
            @Override
            public void onLoadFinish(Loader<Cursor> loader, Cursor data) {
                List<MyPhoto> photos = new ArrayList<>();
                MyPhoto photo;
                while (data.moveToNext()) {
                    photo = new MyPhoto();
                    int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
                    String name = data.getString(data.getColumnIndexOrThrow(DISPLAY_NAME));
                    String path = data.getString(data.getColumnIndexOrThrow(DATA));
                    int width = data.getInt(data.getColumnIndexOrThrow(WIDTH));
                    int height = data.getInt(data.getColumnIndexOrThrow(HEIGHT));
                    photo.setId(imageId);
                    photo.setDisplayName(name);
                    photo.setPath(path);
                    photo.setWidth(width);
                    photo.setHeight(height);
                    photos.add(photo);
                }
                tv_photo_info.setText("宽高均大于500的图片: " + photos.size() + " 张");
            }
        }));
```
