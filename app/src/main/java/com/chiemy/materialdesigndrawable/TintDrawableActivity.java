package com.chiemy.materialdesigndrawable;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class TintDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tint_drawable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView iv = (ImageView) findViewById(R.id.iv);
        // Tint drawable with below:
        // Drawable drawable = iv.getBackground();
        // drawable.setTint(Color.argb(255, 39, 28, 123));
        // iv.setImageDrawable(drawable);
        // or use
        // iv.setColorFilter(Color.argb(255, 39, 28, 123));
        // tintlist(iv);
        tint(iv, ColorStateList.valueOf(Color.parseColor("#303F9F")));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    /**
     *
     * @param iv
     */
    private void tintlist(ImageView iv){
        int[][] states = new int[][]{new int[]{android.R.attr.state_pressed}, new int[]{}};
        int[] colors = {Color.parseColor("#303F9F"), Color.parseColor("#FF4081")};
        ColorStateList stateList = new ColorStateList(states, colors);
        Drawable drawable = iv.getBackground();
        if (iv instanceof AppCompatImageView) {
            // appcompat button replaces tint of its drawable background
            Log.d("", "AppCompatImageView");
            ((AppCompatImageView)iv).setSupportBackgroundTintList(stateList);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Lollipop button replaces tint of its drawable background
            // however it is not equal to setTintList(c)
            iv.setBackgroundTintList(stateList);
        } else {
            // this should only happen if
            // * manually creating a Button instead of AppCompatButton
            // * LayoutInflater did not translate a Button to AppCompatButton
            tint(iv, stateList);
        }
    }

    /**
     *
     * @param iv
     */
    private void tint(ImageView iv, ColorStateList stateList){
        // 系统为了优化性能，相同的Drawble资源共享同一份拷贝，如果直接对drawable进行着色，则其他引用到该图片的敌方也会变色
        // 为了打破这种共享，我们调用mutate()方法。但这并不是拷贝了一份相同的图片，内存开销比较小
        Drawable drawable1 = DrawableCompat.wrap(iv.getBackground().mutate());
        DrawableCompat.setTintList(drawable1, stateList);
        iv.setBackground(drawable1);
    }

}
