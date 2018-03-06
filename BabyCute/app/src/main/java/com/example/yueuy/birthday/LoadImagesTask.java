package com.example.yueuy.birthday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yueuy on 17-12-24.
 */

public class LoadImagesTask extends AsyncTask<String, Void ,Bitmap> {
    private ImageView mImageView;

    public LoadImagesTask(ImageView imageView){
        this.mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params){
        URL imageUrl = null;
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            imageUrl = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        mImageView.setImageBitmap(bitmap);
    }
}
