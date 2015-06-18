package com.clearbridgemobile.baseapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by nikitakramarovsky on 15-03-11.
 */
public class VolleySingleton {
    /**
     * The volley singleton instance.
     */
    private static VolleySingleton mInstance;

    /**
     * The network request queue.
     */
    private RequestQueue mRequestQueue;

    /**
     * The shared volley network image loader.
     */
    private ImageLoader mImageLoader;

    /**
     * The image loaded listener.
     */
    private ImageLoader.ImageListener mImageListener;

    private VolleySingleton(Context context) {

        mRequestQueue = Volley.newRequestQueue(context);
        mImageListener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean b) {
            }
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.w("VolleyError", volleyError.toString());
            }
        };
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);


            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });

    }

    /**
     * Gets the instance.
     *
     * @param context The current Context.
     *
     * @return The instance.
     */
    public static VolleySingleton getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    /**
     * Helper method to load images with out an image view.
     *
     * @param url The url path for the image.
     */
    public void loadAndCacheImage(String url)
    {
        mImageLoader.get(url, mImageListener);
    }

    /**
     * Return the shared volley network image loader.
     *
     * @return The shared volley network image loader.
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}

