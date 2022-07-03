package com.jackson.kream.adapter.holder.sales.first;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jackson.kream.R;
import com.jackson.kream.adapter.holder.sales.first.adapter.MyFirstAdapter;
import com.jackson.kream.utils.Utils;

public class MyFirstFragment extends Fragment {

    public static MyFirstFragment getInstance() {
        return MyFirstFragment.getInstance(null);
    }

    public static MyFirstFragment getInstance(Bundle bundle) {
        MyFirstFragment instance = new MyFirstFragment();
        instance.setArguments(bundle);
        return instance;
    }

    RecyclerView rv;
    MyFirstAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        return view;
    }

    private void initView(View itemView) {
        rv = itemView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new MyFirstAdapter();
        rv.setAdapter(adapter);
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                int itemCount = state.getItemCount();
                if (position < itemCount-1) {
                    outRect.bottom = Utils.dpToPx(10);
                }
            }
        });
    }
}
