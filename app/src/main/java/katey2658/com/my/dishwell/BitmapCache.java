package katey2658.com.my.dishwell;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 11456 on 2016/10/16.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> mCache;

    public BitmapCache() {
        int maxSize=10*1024*1024;
        mCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);
    }



    //高效的获得图片

    //从资源中获取适当大小的图片
    public static  Bitmap decodeSampleBitmapFromResources(Resources res,int resId,int reqWidth,int reqHeight){
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);

        //编译图片用得到的比例大小
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(res,resId,options);
    }

    //计算机所需要的尺寸
    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        final int height=options.outHeight;
        final int width=options.outWidth;
        int inSampleSize=1;

        if (height>reqHeight||width>reqWidth){
            final int halfHeight=height/2;
            final int halfWidth=width/2;

            //计算出最符合的大小inSampleSize
            while ((halfHeight/inSampleSize)>=reqHeight&&halfWidth/inSampleSize>=reqWidth){
                inSampleSize*=2;
            }

        }
        return inSampleSize;
    }


}
