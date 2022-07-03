package com.jackson.kream.adapter.holder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jackson.kream.adapter.holder.sales.first.MyFirstFragment;
import com.jackson.kream.adapter.holder.sales.second.MySecondFragment;

public class MySalesPagerAdapter extends FragmentStateAdapter {

    public MySalesPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MySalesPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MySalesPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return MyFirstFragment.getInstance();
            default:
                return MySecondFragment.getInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
