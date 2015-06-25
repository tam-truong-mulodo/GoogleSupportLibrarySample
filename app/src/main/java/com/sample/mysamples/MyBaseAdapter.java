package com.sample.mysamples;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by truong.tam on 15/06/25.
 */
public class MyBaseAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";

    ArrayList<ListData> myList = new ArrayList<ListData>();
    LayoutInflater inflater;
    Context context;

    ImageLoader mImageLoader;

    public MyBaseAdapter(Context context, ArrayList<ListData> object) {
        this.myList = object;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: " + myList.size());
        return myList.size();
    }

    @Override
    public ListData getItem(int position) {
        Log.d(TAG, "getItem: " + myList.get(position));
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item, parent, false);

            NetworkImageView imageView = (NetworkImageView)convertView.findViewById(R.id.image);
            TextView textView = (TextView)convertView.findViewById(R.id.message);

            mViewHolder = new MyViewHolder();
            mViewHolder.ivImg = imageView;
            mViewHolder.tvMessage = textView;

            convertView.setTag(mViewHolder);
            Log.d(TAG, "convertView == null");
        } else {
            Log.d(TAG, "convertView != null");
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListData currentListData = getItem(position);

        mViewHolder.tvMessage.setText(currentListData.getMessage());

        String imageUrl = currentListData.getImgUrl();
        Log.d(TAG, "imageUrl: " + imageUrl);
        Log.d(TAG, "Message: " + currentListData.getMessage());

        mImageLoader = new ImageLoader(Volley.newRequestQueue(this.context), new LruCacheSample());
        ImageListener listener = mImageLoader.getImageListener(mViewHolder.ivImg, 0, 0);
        mImageLoader.get(imageUrl, listener);

        // リクエストのキャンセル処理
        ImageContainer imageContainer = (ImageContainer)mViewHolder.ivImg.getTag();
        if (imageContainer != null) {
            Log.d(TAG, "imageContainer != null");
            imageContainer.cancelRequest();
        }

        return convertView;
    }

    private class MyViewHolder {
        TextView tvMessage;
        //ImageView ivImg;
        NetworkImageView ivImg;
    }

}
