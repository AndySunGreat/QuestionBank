package com.aladdin.apps.questionbank.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AndySun on 2016/10/10.
 */
public class BaseViewHolder {
    // Stored all row item views ,each view is one BaseViewHolder
    private SparseArray<View> mViews;
    // row view index
    private int mPosition;
    //
    private View mConvertView;
    // context
    private Context context;
    // Initialize this BaseViewHolder instance
    public BaseViewHolder(Context context, ViewGroup parent,int layoutId, int position){
        this.mPosition = position;
        this.context = context;
        this.mViews = new SparseArray<View>();
        // 将充气袋充气
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        // Add this BaseViewHolder object as view's attribute into View
        mConvertView.setTag(this);
    }

    /**
     * Reuse
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static BaseViewHolder get(Context context,View convertView, ViewGroup parent, int layoutId,int position){
        // check if currect baseViewHolder is existing
        if(convertView==null){
            return new  BaseViewHolder(context,parent,layoutId,position);
        }else{
            BaseViewHolder holder = (BaseViewHolder)convertView.getTag();
            holder.mPosition = position; // update new position
            return holder;
        }
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view ==null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }
}
