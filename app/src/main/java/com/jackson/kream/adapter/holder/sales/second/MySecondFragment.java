package com.jackson.kream.adapter.holder.sales.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jackson.kream.R;

public class MySecondFragment extends Fragment {

    public static MySecondFragment getInstance() {
        return MySecondFragment.getInstance(null);
    }

    public static MySecondFragment getInstance(Bundle bundle) {
        MySecondFragment instance = new MySecondFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }
}
