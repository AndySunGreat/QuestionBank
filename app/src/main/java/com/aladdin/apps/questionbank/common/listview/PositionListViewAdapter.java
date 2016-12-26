package com.aladdin.apps.questionbank.common.listview;

/**
 * Created by AndySun on 2016/10/10.
 */

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseListViewAdapter;
import com.aladdin.apps.questionbank.base.BaseViewHolder;

import java.util.List;

/**
 * Created by smyhvae on 2015/5/4.
 */
public class PositionListViewAdapter extends BaseListViewAdapter<PositionListViewEntity> implements TextView.OnClickListener{

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public PositionListViewAdapter(Context context, List<PositionListViewEntity> datas) {
        super(context, datas, R.layout.hopper_positions_listview_row);
    }

    @Override
    public void convert(BaseViewHolder holder, PositionListViewEntity bean) {

        ((TextView) holder.getView(R.id.hiddenCompanyId)).setText(bean.getCompanyId());
        ((TextView) holder.getView(R.id.hiddenPositionId)).setText(bean.getPositionId());
        ((TextView) holder.getView(R.id.titlePosition)).setText(bean.getPositionName());
        ((TextView) holder.getView(R.id.descPosition)).
                setText("工作经验："+ bean.getExperience() + " 城市:" + bean.getCity());

        ((TextView) holder.getView(R.id.timePosition)).setText(bean.getChangeData());
        ((TextView) holder.getView(R.id.doPosition)).setText("详细");
        ((TextView) holder.getView(R.id.doPosition)).setOnClickListener(this);
/*
        TextView tv = holder.getView(R.id.titleTv);
        tv.setText(...);

       ImageView view = getView(viewId);
       Imageloader.getInstance().loadImag(view.url);
*/
    }

    @Override
    public void onClick(View view) {
            // click phoneTV icon image event
    }
}
