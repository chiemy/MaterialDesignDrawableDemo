package com.chiemy.materialdesigndrawable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String[] datas = {"Tint drawable", "Vector drawable", "Palette"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter(this);
        adapter.setOnItemClickListener(new ListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, TintDrawableActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, VectorDrawableActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, PaletteActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private static class ListAdapter extends RecyclerView.Adapter<CustomViewHolder> {
        private LayoutInflater inflater;

        public ListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CustomViewHolder(inflater.inflate(R.layout.item_main_list, parent, false), myItemClickListener);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.itemView.setTag(position);
            holder.tv.setText(datas[position]);
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }

        private MyItemClickListener myItemClickListener;
        public void setOnItemClickListener(MyItemClickListener listener){
            myItemClickListener = listener;
        }

        public interface MyItemClickListener{
            void onItemClick(View view, int position);
        }
    }

    private static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv;

        private ListAdapter.MyItemClickListener myItemClickListener;
        public CustomViewHolder(View itemView,  ListAdapter.MyItemClickListener listener) {
            super(itemView);
            myItemClickListener = listener;
            itemView.setOnClickListener(this);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
        }

        @Override
        public void onClick(View view) {
            myItemClickListener.onItemClick(view, getAdapterPosition());
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
