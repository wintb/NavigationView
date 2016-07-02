package tien.dinh.navigationview.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

/**
 * Created by NETVIS on 6/10/2016.
 */
public class BitmapLoader {

    public static void LoadImageNotScale(Context context, ImageView imageView, String imageUrl) {
        if (context == null) {
            return;
        }
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .asBitmap()
                    .into(imageView);
        } catch (OutOfMemoryError ex) {
            Log.d("BitmapLoader", "LoadImageNotScale is OutOfMemoryError: " + ex.getMessage());
        } catch (Exception ex) {
            Log.d("BitmapLoader", "LoadImageNotScale has Exception: " + ex.getMessage());
        }
    }

    public static void LoadImageNotScale(Context context, ImageView imageView, int imageId) {
        if (context == null) {
            return;
        }
        try {
            Glide.with(context)
                    .load(imageId)
                    .asBitmap()
                    .into(imageView);
        } catch (OutOfMemoryError ex) {
            Log.d("BitmapLoader", "LoadImageNotScale is OutOfMemoryError: " + ex.getMessage());
        } catch (Exception ex) {
            Log.d("BitmapLoader", "LoadImageNotScale has Exception: " + ex.getMessage());
        }

    }

    public static void LoadImageWithScale(Context context, ImageView imageView, int imageId, int width) {
        if (context == null) {
            return;
        }
        try {
            Glide.with(context)
                    .load(imageId)
                    .asBitmap()
                    .override(width, Target.SIZE_ORIGINAL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(imageView);

        } catch (OutOfMemoryError ex) {

        } catch (Exception ex) {
        }

    }

    public static void LoadImageWithScale(Context context, ImageView imageView, String imageUrl, int width) {
        if (context == null) {
            return;
        }
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .asBitmap()
                    .override(width, Target.SIZE_ORIGINAL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(imageView);

        } catch (OutOfMemoryError ex) {

        } catch (Exception ex) {
        }

    }
}
