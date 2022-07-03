package com.jackson.kream.adapter.holder.sales.first.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackson.kream.R;
import com.jackson.kream.adapter.holder.sales.first.adapter.holder.MyFirstCategoriesHolder;
import com.jackson.kream.adapter.holder.sales.first.adapter.holder.MyFirstFooterHolder;
import com.jackson.kream.adapter.holder.sales.first.adapter.holder.MyFirstHoldingProductHolder;
import com.jackson.kream.adapter.holder.sales.first.adapter.holder.MyFirstSalesHistoryHolder;

public class MyFirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_SALES_HISTORY = 1010;
    public static final int VIEW_TYPE_HOLDING_PRODUCT = 2020;
    public static final int VIEW_TYPE_CATEGORIES = 3030;
    public static final int VIEW_TYPE_FOOTER = 4040;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view;
        RecyclerView.ViewHolder holder;

        switch (viewType) {
            case VIEW_TYPE_SALES_HISTORY:
                view = inflater.inflate(R.layout.listitem_sales_history, parent, false);
                holder = new MyFirstSalesHistoryHolder(view);
                break;
            case VIEW_TYPE_HOLDING_PRODUCT:
                view = inflater.inflate(R.layout.listitem_my_holding_product, parent, false);
                holder = new MyFirstHoldingProductHolder(view);
                break;
            case VIEW_TYPE_CATEGORIES:
                view = inflater.inflate(R.layout.listitem_my_categories, parent, false);
                holder = new MyFirstCategoriesHolder(view);
                break;
            default:
                view = inflater.inflate(R.layout.listitem_my_footer, parent, false);
                holder = new MyFirstFooterHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return VIEW_TYPE_SALES_HISTORY;
            case 1:
                return VIEW_TYPE_HOLDING_PRODUCT;
            case 2:
                return VIEW_TYPE_CATEGORIES;
            default:
                return VIEW_TYPE_FOOTER;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
