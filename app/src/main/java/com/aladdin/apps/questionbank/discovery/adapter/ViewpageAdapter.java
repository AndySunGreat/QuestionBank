package com.aladdin.apps.questionbank.discovery.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cg on 2015/10/28.
 */
public class ViewpageAdapter extends PagerAdapter {
    private List<View> pages = null;

    public ViewpageAdapter(List<View> pages) {
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = pages.get(position);
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(v);
        return v;
        //container.addView(pages.get(position % pages.size()));
        //return pages.get(position % pages.size());
       /* v = pages.get(p)
       */
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(pages.get(position % pages.size()));
        container.removeView((View)object);
    }
}