package com.example.se1417_day10_picturesandmenus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryHieuActivity extends AppCompatActivity {

    private Integer[] imageIDs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
    };

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;

        public ImageAdapter(Context context) {
            this.context = context;
            TypedArray arr = this.context.obtainStyledAttributes(R.styleable.GalleryHieuBD);
            itemBackground = arr.getResourceId(R.styleable.GalleryHieuBD_android_galleryItemBackground, 0);
        }

        @Override
        public int getCount() {
            return imageIDs.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null) {
                imageView = new ImageView(this.context);
                imageView.setImageResource(imageIDs[i]);
                imageView.setLayoutParams(new Gallery.LayoutParams(200, 140));
            } else {
                imageView = (ImageView) view;
            }
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_hieu);
        Gallery gallery = findViewById(R.id.galleryViewHieuBD);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView imageView = findViewById(R.id.imageViewHieuBD);
                imageView.setBackgroundResource(imageIDs[i]);
            }
        });
    }
}