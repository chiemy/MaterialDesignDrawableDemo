package com.chiemy.materialdesigndrawable;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;

import java.lang.ref.WeakReference;

public class PaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MyHandler handler = new MyHandler(this);
        final BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.mipmap.ic_launcher);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Palette palette = Palette.from(bitmapDrawable.getBitmap()).generate();
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("vibrantColor", palette.getVibrantColor(Color.BLACK));
                bundle.putInt("vibrantDrakColor", palette.getDarkVibrantColor(Color.BLACK));
                bundle.putInt("vibrantLightColor", palette.getLightVibrantColor(Color.BLACK));
                bundle.putInt("mutedColor", palette.getMutedColor(Color.BLACK));
                bundle.putInt("mutedLightColor", palette.getLightMutedColor(Color.BLACK));
                bundle.putInt("mutedDrakColor", palette.getDarkMutedColor(Color.BLACK));
                msg.obj = bundle;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private static class MyHandler extends Handler {
        private WeakReference<PaletteActivity> ref;
        public MyHandler(PaletteActivity act){
            ref = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            PaletteActivity act = ref.get();
            if (act != null){
                Bundle bundle = (Bundle) msg.obj;
                act.findViewById(R.id.tv_vibrant).setBackgroundColor(bundle.getInt("vibrantColor"));
                act.findViewById(R.id.tv_vibrant_light).setBackgroundColor(bundle.getInt("vibrantLightColor"));
                act.findViewById(R.id.tv_vibrant_dark).setBackgroundColor(bundle.getInt("vibrantDrakColor"));
                act.findViewById(R.id.tv_muted).setBackgroundColor(bundle.getInt("mutedColor"));
                act.findViewById(R.id.tv_muted_light).setBackgroundColor(bundle.getInt("mutedLightColor"));
                act.findViewById(R.id.tv_muted_dark).setBackgroundColor(bundle.getInt("mutedDrakColor"));
            }
        }
    }

}
