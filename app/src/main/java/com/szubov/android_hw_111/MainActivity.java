package com.szubov.android_hw_111;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random mRandom = new Random();
    private ItemsDataAdapter mAdapter;
    private List<Drawable> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        FloatingActionButton mFab = findViewById(R.id.fab);
        ListView mListView = findViewById(R.id.listView);

        setSupportActionBar(mToolbar);

        fillImages();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomData();
            }
        });

        mAdapter = new ItemsDataAdapter(this, null);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showItemData(position);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.removeItem(position);
                return true;
            }
        });
    }

    private void fillImages() {
        mImages.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_compass));
        mImages.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_day));
        mImages.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_agenda));
        mImages.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_camera));
        mImages.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_call));
    }

    private void generateRandomData() {
        mAdapter.addItem(new ItemData(mImages.get(mRandom.nextInt(mImages.size())),
                "Hello" + mAdapter.getCount(),"It\\'s me", mRandom.nextBoolean()));
    }

    private void showItemData(int position) {
        ItemData itemData = mAdapter.getItem(position);
        Toast.makeText(MainActivity.this,"Title: " + itemData.getTitle() + "\n"
                + "Subtitle: " + itemData.getSubtitle() +
                "\n" + "Checked: " + itemData.isChecked(), Toast.LENGTH_LONG).show();
    }
}