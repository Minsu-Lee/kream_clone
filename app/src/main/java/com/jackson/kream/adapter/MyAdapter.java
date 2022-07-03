package com.jackson.kream.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.jackson.kream.R;
import com.jackson.kream.adapter.holder.MyProfileHolder;
import com.jackson.kream.adapter.holder.MySalesHistoryHolder;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface InitialzedViewPagerTabLayout {
        void onPagerBind(ViewPager2 viewPager2);
        void onPagerRecycled(ViewPager2 viewPager2);
    }

    public static final int VIEW_TYPE_PROFILE = 1010;
    public static final int VIEW_TYPE_SALES_HISTORY = 2020;

    FragmentManager fragmentManager;
    Lifecycle lifecycle;
    private InitialzedViewPagerTabLayout initialzedViewPagerTabLayout;

    private MyAdapter() { }

    public MyAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, InitialzedViewPagerTabLayout initialzedViewPagerTabLayout) {
        this.fragmentManager = fragmentManager;
        this.lifecycle = lifecycle;
        this.initialzedViewPagerTabLayout = initialzedViewPagerTabLayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case VIEW_TYPE_PROFILE:
                view = inflater.inflate(R.layout.listitem_my_profile, parent, false);
                return new MyProfileHolder(view);
            default:
                view = inflater.inflate(R.layout.listitem_my_sales_history, parent, false);
                return new MySalesHistoryHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_PROFILE:
                MyProfileHolder profileHolder = (MyProfileHolder) holder;
                profileHolder.onBind();
                break;
            default:
                MySalesHistoryHolder salesHistoryHolder = (MySalesHistoryHolder) holder;
                salesHistoryHolder.onBind(fragmentManager, lifecycle, initialzedViewPagerTabLayout);
                break;
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case VIEW_TYPE_PROFILE:
                break;
            default:
                MySalesHistoryHolder salesHistoryHolder = (MySalesHistoryHolder) holder;
                salesHistoryHolder.onViewRecycled(initialzedViewPagerTabLayout);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return VIEW_TYPE_PROFILE;
            default:
                return VIEW_TYPE_SALES_HISTORY;

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
