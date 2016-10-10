package com.aladdin.apps.questionbank.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by AndySun on 2016/10/10.
 * Common ListView Adapter, all custom list view adpater can extend  this common class
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    // 为了让子类访问，将属性设置为protected
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId; // 不同的ListView的item布局可能不同，所以把布局单独提取出来

    public BaseListViewAdapter(Context context, List<T> datas, int layoutId){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.mDatas = datas;
    }

    @Override
    public int getCount(){
        return mDatas.size();
    }

    @Override
    public T getItem(int position){
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化ViewHolder,使用通用的ViewHolder，一行代码就搞定ViewHolder的初始化咯
        BaseViewHolder holder = BaseViewHolder.get(mContext, convertView, parent, layoutId, position);//layoutId就是单个item的布局
        convert(holder, getItem(position));
        return holder.getConvertView(); //这一行的代码要注意了
    }

    //将convert方法公布出去
    public abstract void convert(BaseViewHolder holder, T t);

}
