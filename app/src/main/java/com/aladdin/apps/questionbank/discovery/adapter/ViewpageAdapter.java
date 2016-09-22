package com.aladdin.apps.questionbank.discovery.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cg on 2015/10/28.
 */
public class ViewpageAdapter extends PagerAdapter {
    private List<View> list_view;

    public ViewpageAdapter(List<View> list_view) {
        this.list_view = list_view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(list_view.get(position % list_view.size()));
        return list_view.get(position % list_view.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list_view.get(position % list_view.size()));
    }
}