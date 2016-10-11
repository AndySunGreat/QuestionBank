package com.aladdin.apps.questionbank.common.listview;

/**
 * Created by AndySun on 2016/10/10.
 */

import com.aladdin.apps.questionbank.R;
import com.aladdin.apps.questionbank.base.BaseListViewAdapter;
import com.aladdin.apps.questionbank.base.BaseViewHolder;
import com.aladdin.apps.questionbank.common.entity.PackageListViewEntity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
 import java.util.List;

/**
 * Created by smyhvae on 2015/5/4.
 */
public class PackageListVAdapter extends BaseListViewAdapter<PackageListViewEntity> implements TextView.OnClickListener{

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public PackageListVAdapter(Context context, List<PackageListViewEntity> datas) {
        super(context, datas, R.layout.packages_listview_row);
    }

    @Override
    public void convert(BaseViewHolder holder, PackageListViewEntity bean) {

        ((TextView) holder.getView(R.id.hiddenPackageId)).setText(bean.getPackageId());
        ((TextView) holder.getView(R.id.hiddenBankIds)).setText(bean.getBankIds());
        ((TextView) holder.getView(R.id.titleTv)).setText(bean.getTitle());
        ((TextView) holder.getView(R.id.descTv)).setText(bean.getDesc());
        ((TextView) holder.getView(R.id.timeTv)).setText(bean.getTime());
        ((TextView) holder.getView(R.id.phoneTv)).setText(bean.getPhone());
        ((TextView) holder.getView(R.id.phoneTv)).setOnClickListener(this);
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
