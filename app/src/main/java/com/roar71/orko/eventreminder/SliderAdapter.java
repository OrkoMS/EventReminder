package com.roar71.orko.eventreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater ;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slide_images={
            R.drawable.place,
            R.drawable.travel,
            R.drawable.gallery
    };

    public String[] slide_headings={
            "Place",
            "Travel",
            "Gallery"
    };

    public String[] slide_descriptions={
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words."
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==(LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_content, container, false);

        ImageView sliderImage = view.findViewById(R.id.slider_imaage);
        TextView sliderHeading = view.findViewById(R.id.slider_heading);
        TextView sliderDescription = view.findViewById(R.id.slider_description);

        sliderImage.setImageResource(slide_images[position]);
        sliderHeading.setText(slide_headings[position]);
        sliderDescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
