package com.chiemy.materialdesigndrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VectorDrawableActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startVectorDrawableAnim();
    }


    private void startVectorDrawableAnim(){
        ImageView vectorAnimIv = (ImageView) findViewById(R.id.iv_vector_anim);
        startAnim(vectorAnimIv);
        vectorAnimIv.setOnClickListener(this);

        TextView vectorAnimIv1 = (TextView) findViewById(R.id.tv_vector_anim1);
        vectorAnimIv1.setOnClickListener(this);
    }

    private void startAnim(ImageView vectorIv){
        Drawable drawable1 = vectorIv.getBackground();
        if (drawable1 instanceof Animatable){
            ((Animatable)drawable1).start();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_vector_anim1:
                Drawable[] drawables1 = ((TextView)view).getCompoundDrawables();
                for (int i = 0 ; i < drawables1.length ; i++){
                    if (drawables1[i] instanceof Animatable){
                        ((Animatable)drawables1[i]).start();
                    }
                }
                break;
            case R.id.iv_vector_anim:
                startAnim((ImageView) view);
                break;
        }
    }
}
