package com.example.sparemate.Adapter;

 import android.content.Context;
 import androidx.annotation.NonNull;
 import androidx.viewpager.widget.PagerAdapter;
import com.example.sparemate.R;
 import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import java.util.ArrayList;

 import android.content.Context;
 import androidx.annotation.NonNull;
 import androidx.viewpager.widget.PagerAdapter;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import java.util.ArrayList;
 import com.example.sparemate.R; // Assuming this is the correct import for your R class

public class ViewPageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Integer> images;

    // Constructor
    public ViewPageAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        // Initialize the ArrayList and add images here
        this.images = new ArrayList<>();
        this.images.add(R.drawable.img1);
        this.images.add(R.drawable.img2);
        this.images.add(R.drawable.img3);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image_slider, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(images.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}




//public class ViewPageAdapter extends PagerAdapter {
//
//    private Context context;
//     // You can replace Integer with your actual image type
//     // Initialize the ArrayList to hold the image resources
//     ArrayList<Integer> images = new ArrayList<>();
//
//        // Add the image resources to the ArrayList
//        images.add(R.drawable.banner_img_01); // Assuming banner_img_01.png is the name of your image resource
//        images.add(R.drawable.banner_img_02); // Assuming
//    public ViewPageAdapter(Context context, ArrayList<Integer> images) {
//        this.context = context;
//        this.images = images;
//    }
//
//    @Override
//    public int getCount() {
//        return images.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.item_image_slider, container, false);
//
//        ImageView imageView = view.findViewById(R.id.imageView);
//        imageView.setImageResource(images.get(position));
//
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//}

