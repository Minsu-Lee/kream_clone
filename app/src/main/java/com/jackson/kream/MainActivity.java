package com.jackson.kream;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jackson.kream.adapter.MyAdapter;
import com.jackson.kream.utils.Utils;

public class MainActivity extends AppCompatActivity implements MyAdapter.InitialzedViewPagerTabLayout {

    ImageView ivSettings;
    TabLayout tabLayout;
    ImageView ivPhotos;

    String[] tabNames = new String[] { "내 쇼핑", "내 프로필" };
    TabLayoutMediator tabLayoutMediator;
    ViewPager2.OnPageChangeCallback callback;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        getSupportActionBar().hide();
        ivSettings = findViewById(R.id.ivSettings);
        tabLayout = findViewById(R.id.tabLayout);
        ivPhotos = findViewById(R.id.ivPhotos);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.setAdapter(new MyAdapter(getSupportFragmentManager(), getLifecycle(), MainActivity.this));
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    outRect.bottom = Utils.dpToPx(10);
                }
            }
        });
    }

    @Override
    public void onPagerBind(ViewPager2 viewPager2) {
        /**
         * init TabLayout
         */
        if (tabLayoutMediator == null) {
            tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    tab.setText(tabNames[position]);
                }
            });
        }
        tabLayout.setTabMode(TabLayout.MODE_AUTO);
        tabLayoutMediator.attach();

        /**
         * init PageChangeCallback
         */
        if (callback == null) {
            callback = new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    Log.e("onPageSelected", "position : " + position);
                }
            };
            viewPager2.registerOnPageChangeCallback(callback);
        }
    }

    @Override
    public void onPagerRecycled(ViewPager2 viewPager2) {
        /**
         * detach tabLayout mediator
         */
        if (tabLayoutMediator != null) {
            tabLayoutMediator.detach();
            tabLayoutMediator = null;
        }

        /**
         * unregister PageChangeCallback
         */
        if (callback != null) {
            viewPager2.unregisterOnPageChangeCallback(callback);
            callback = null;
        }
    }
}