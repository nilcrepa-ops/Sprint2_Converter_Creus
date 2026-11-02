package com.example.sprint2_converter_creus.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtils {
    public static Bitmap decodeBitmapFromResource(Context context, int resId){
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }
}
