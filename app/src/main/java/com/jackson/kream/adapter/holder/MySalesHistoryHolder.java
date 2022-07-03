package com.jackson.kream.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.jackson.kream.R;
import com.jackson.kream.adapter.MyAdapter;
import com.jackson.kream.adapter.holder.sales.MySalesPagerAdapter;

public class MySalesHistoryHolder extends RecyclerView.ViewHolder {

    ViewPager2 viewPager2;
    MySalesPagerAdapter adapter;

    public MySalesHistoryHolder(@NonNull View itemView) {
        super(itemView);
        viewPager2 = itemView.findViewById(R.id.vp);
    }

    public void onBind(FragmentManager fragmentManager, Lifecycle lifecycle, MyAdapter.InitialzedViewPagerTabLayout initialzedViewPagerTabLayout) {
        if (adapter == null) {
            adapter = new MySalesPagerAdapter(fragmentManager, lifecycle);
        }
        viewPager2.setAdapter(adapter);

        if (initialzedViewPagerTabLayout != null && viewPager2 != null) {
            initialzedViewPagerTabLayout.onPagerBind(viewPager2);
        }
    }

    public void onViewRecycled(MyAdapter.InitialzedViewPagerTabLayout initialzedViewPagerTabLayout) {
        if (initialzedViewPagerTabLayout != null) {
            initialzedViewPagerTabLayout.onPagerRecycled(viewPager2);
        }
    }
}
