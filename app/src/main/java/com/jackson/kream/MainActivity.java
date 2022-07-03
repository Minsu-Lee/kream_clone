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
import com.jackson.kream.adapter.MyAdapter;
import com.jackson.kream.adapter.TabLayoutManager;
import com.jackson.kream.utils.Utils;

public class MainActivity extends AppCompatActivity {

    ImageView ivSettings;
    private TabLayoutManager tabLayoutManager;
    ImageView ivPhotos;
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
        tabLayoutManager = TabLayoutManager.builder()
            .withTabLayout(findViewById(R.id.tabLayout))
            .withTabNames("내 쇼핑", "내 프로필")
            .withTabChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.e("onPageSelected", "position : " + position);
                }
            })
            .build();
        ivPhotos = findViewById(R.id.ivPhotos);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.setAdapter(new MyAdapter(getSupportFragmentManager(), getLifecycle(), tabLayoutManager));
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
}