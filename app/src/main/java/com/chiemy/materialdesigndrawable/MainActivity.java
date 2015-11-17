package com.chiemy.materialdesigndrawable;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.ref.WeakReference;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    private static class MyHandler extends Handler{
        private WeakReference<MainActivity> ref;
        public MyHandler(MainActivity act){
            ref = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity act = ref.get();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
