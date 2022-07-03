package com.jackson.kream.adapter;

import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

public class TabLayoutManager implements MyAdapter.InitialzedViewPagerTabLayout {

    private TabLayoutMediator tabLayoutMediator;
    private OnPageChangeCallback tabChangeCallback;
    private final TabLayout tabLayout;
    private final List<String> tabNames;

    private TabLayoutManager(
        TabLayout tabLayout,
        OnPageChangeCallback tabChangeCallback,
        List<String> tabNames) {
        this.tabLayout = tabLayout;
        this.tabChangeCallback = tabChangeCallback;
        this.tabNames = tabNames;
        tabLayout.setTabMode(TabLayout.MODE_AUTO); // todo improve flexible
    }

    public static TabLayoutManager.TabLayoutManagerBuilder builder() {
        return new TabLayoutManagerBuilder();
    }

    @Override
    public void onPagerBind(ViewPager2 viewPager2) {
        attach(viewPager2);
    }

    @Override
    public void onPagerRecycled(ViewPager2 viewPager2) {
        detach(viewPager2);
    }

    private void attach(ViewPager2 viewPager2) {
        if (isAttached()) {
            return;
        }
        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabNames.get(position)));
        tabLayoutMediator.attach();
        viewPager2.registerOnPageChangeCallback(tabChangeCallback);
    }

    private void detach(ViewPager2 viewPager2) {
        if (!isAttached()) {
            return;
        }
        tabLayoutMediator.detach();
        viewPager2.unregisterOnPageChangeCallback(tabChangeCallback);
    }

    private boolean isAttached() {
        if (tabLayoutMediator == null) {
            return false;
        }
        return tabLayoutMediator.isAttached();
    }

    public static class TabLayoutManagerBuilder {
        private TabLayout tabLayout;
        private OnPageChangeCallback tabChangeCallback;
        private final List<String> tabNames = new ArrayList<>();

        private TabLayoutManagerBuilder() {
        }

        public TabLayoutManagerBuilder withTabLayout(TabLayout tabLayout) {
            this.tabLayout = tabLayout;
            return this;
        }

        public TabLayoutManagerBuilder withTabChangeCallback(OnPageChangeCallback tabChangeCallback) {
            this.tabChangeCallback = tabChangeCallback;
            return this;
        }

        public TabLayoutManagerBuilder withTabNames(List<String> tabNames) {
            this.tabNames.addAll(tabNames);
            return this;
        }

        public TabLayoutManagerBuilder withTabNames(String... tabNames) {
            withTabNames(Arrays.asList(tabNames));
            return this;
        }

        public TabLayoutManagerBuilder setTabNames(List<String> tabNames) {
            this.tabNames.clear();
            return withTabNames(tabNames);
        }

        public TabLayoutManagerBuilder setTabNames(String... tabNames) {
            return setTabNames(Arrays.asList(tabNames));
        }

        public TabLayoutManager build() {
            if (tabNames.isEmpty()) {
                throw new IllegalStateException("'tabNames' is required");
            }
            if (tabLayout == null) {
                throw new IllegalStateException("'tabLayout' is required");
            }
            if (tabChangeCallback == null ){
                this.tabChangeCallback = NoopCallback.NOOP;
            }
            return new TabLayoutManager(tabLayout, tabChangeCallback, tabNames);
        }

        private static class NoopCallback extends OnPageChangeCallback {
            static OnPageChangeCallback NOOP = new NoopCallback();
        }
    }
}


