package com.roar71.orko.eventreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private SliderAdapter sliderAdapter;

    private TextView[] mDots = new TextView[3];

    private Button nextBtn;
    private Button backBtn;

    private int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        viewPager = findViewById(R.id.viewpager_slider);
        linearLayout = findViewById(R.id.dots_layout);

        nextBtn = findViewById(R.id.next_btn);
        backBtn = findViewById(R.id.back_btn);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator();
        mDots[0].setTextColor(getResources().getColor(R.color.colorWhite));
        viewPager.addOnPageChangeListener(viewListener);

        SharedPreferences sharedPref = getSharedPreferences("firstUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("newUser",false);
        editor.apply();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentPage==2)
                {
                    Intent intent = new Intent(TutorialActivity.this,HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                else
                {
                    viewPager.setCurrentItem(mCurrentPage+1);
                }

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    public  void addDotsIndicator(){
        for(int i=0; i <mDots.length;i++)
        {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(38);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            linearLayout.addView(mDots[i]);
        }


    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            for(int j=0; j <mDots.length;j++) {
                mDots[j].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            }
            mDots[i].setTextColor(getResources().getColor(R.color.colorWhite));
        }

        @Override
        public void onPageSelected(int i) {

            mCurrentPage=i;
            if(i==0) {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText("Next");
                backBtn.setText("");
            }
            else if(i==mDots.length-1)
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("Finish");
                backBtn.setText("Back");
            }
            else
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("Next");
                backBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
