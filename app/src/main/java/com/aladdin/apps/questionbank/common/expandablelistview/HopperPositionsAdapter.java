package com.aladdin.apps.questionbank.common.expandablelistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aladdin.apps.questionbank.R;

import java.util.List;

/**
 * Created by AndySun on 2016/12/9.
 */
public class HopperPositionsAdapter extends BaseExpandableListAdapter {

    //public static Map<Integer, Map<Integer, Boolean>> isSelectedMap;
    private List<HopperPositionsEntity> orderList ;
    private LayoutInflater inflater ;
    private View group ;
    private HopperPositionsEntity hopperConditionEntity;
    public HopperPositionsAdapter(List<HopperPositionsEntity> orderList, Context context) {
        this.orderList = orderList ;
        inflater = LayoutInflater.from(context) ;
        group = new FrameLayout(context) ;
        //setDefaultChkList();
    }


    @Override
    public int getGroupCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public int getChildrenCount(int groupPOSPostion) {
        List<HopperPositionsSubEntity> children = ((HopperPositionsEntity)orderList.get(groupPOSPostion)).getHopperPositionsSubEntityList();
        int size = children.size() ;
        // 加上header和footer
        return size == 0 ? 0 : size + 2 ;
    }

    @Override
    public Object getGroup(int groupPOSPostion) {
        return orderList.get(groupPOSPostion);
    }

    @Override
    public Object getChild(int groupPOSPostion, int childPOSPosition) {
         Log.d("groupPOSPostion:",groupPOSPostion+"");
         Log.d("childPOSPosition:",childPOSPosition+"");
        return orderList.get(groupPOSPostion).getHopperPositionsSubEntityList().get(childPOSPosition) ;
    }

    @Override
    public long getGroupId(int groupPOSPostion) {
        return groupPOSPostion;
    }

    @Override
    public long getChildId(int groupPOSPostion, int childPOSPosition) {
        return childPOSPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

/*    // 初始化 设置所有checkbox都为未选择
    public void setDefaultChkList() {
        isSelectedMap = new HashMap<Integer, Map<Integer, Boolean>>();
        Map<Integer,Boolean> subMap = new HashMap<Integer,Boolean>();
        Log.d("group count:",this.getGroupCount()+"");
        int groupCount = this.getGroupCount();
        int childCount = 0;
        for(int i=0;i<groupCount;i++){
            subMap = new HashMap<Integer,Boolean>();
            childCount = this.getChildrenCount(i);
            for(int j=1;j<childCount-1;j++){
                subMap.put(j,false);
            }
            isSelectedMap.put(i,subMap);
        }
    }*/

    @Override
    public View getGroupView(int groupPOSPostion, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                // 显示Group名称，即问题主题,hopper_conditon_entity_view
                convertView = inflater.inflate(R.layout.hopper_positions_entity_view, null);
            }
            TextView group = (TextView) convertView.findViewById(R.id.expandGroup);
            String strGroupTitle = ((HopperPositionsEntity)orderList.get(groupPOSPostion)).getGroupTitle();
            group.setText(strGroupTitle);
/*            if(groupPOSPostion==0){
                group.setHeight(0);
                group.setVisibility(INVISIBLE);
            }*/

/*        if(groupPOSPostion==0){
            group.setText("请选择跳槽原因：");
        }else if(groupPOSPostion==1){
            group.setText("请输入当前月薪：");
        }else{
            group.setText("该group未设置标题");
        }*/
        //group.setText("题" + (groupPOSPostion + 1));

        return convertView;
    }

    // 得到小组成员的view
    @Override
    public View getChildView(final int groupPOSPostion, final int childPOSPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //Log.d("getChildView begin ","************************************************");

        do {
            // 判断childPOSPosition的索引数是否不是header或不是footer
            if (childPOSPosition > 0 && childPOSPosition < getChildrenCount(groupPOSPostion) - 1) {
                HopperPositionsSubEntity item = (HopperPositionsSubEntity) getChild(groupPOSPostion,childPOSPosition - 1);
                ChildHolder holder ;
                // 复用View
                if (convertView == null) {
                    holder = new ChildHolder();//hopper_condition_sub_entity_view
                    convertView = inflater.inflate(R.layout.hopper_positions_sub_entity_view,null) ;
                    CheckBox optSeq = (CheckBox) convertView.findViewById(R.id.optSeq);
                    TextView optContent = (TextView) convertView.findViewById(R.id.optContent);
                    TextView tvQuantity = (TextView) convertView.findViewById(R.id.tvQuantity);

                    holder.optSeq = optSeq ;
                    holder.optContent = optContent ;
                    holder.quantity = tvQuantity ;
                    convertView.setTag(holder);
                } else {
                    holder = (ChildHolder) convertView.getTag();
                }

                holder.optSeq.setText(item.getOptSeq());

                int childCount=this.getChildrenCount(groupPOSPostion);
                holder.optSeq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        Log.d("CheckedChangeListener","a1");

                    }
                });
                // 这点语句必须在设置监听器之后，
                //holder.optSeq.setChecked(isSelectedMap.get(groupPOSPostion).get(childPOSPosition));
                holder.optContent.setText(item.getOptContent());
                //holder.quantity.setText(String.valueOf(item.getQuantity()));
                holder.quantity.setText("100.01");
                break ;
            }

            HopperPositionsEntity order = (HopperPositionsEntity) getGroup(groupPOSPostion);

            // 判断是否是header
            if (childPOSPosition == 0) {
                convertView = inflater.inflate(R.layout.hopper_positions_sub_entity_header,null) ;
                TextView header = (TextView) convertView.findViewById(R.id.headerEntity);
                header.setText(order.getPositionName());
                //header.setText("共" + order.getItems().size() + "份美食");
                break ;
            }

            if (childPOSPosition == getChildrenCount(groupPOSPostion) - 1) {
                FooterHolder footerHolder;
                if(convertView==null) {
                    footerHolder = new FooterHolder();
                    convertView = inflater.inflate(R.layout.common_view_order_footer, null);
                    TextView answerResultFooter = (TextView) convertView.findViewById(R.id.answerResultFooter);
                    TextView correctAnswerFooter = (TextView) convertView.findViewById(R.id.correctAnswerFooter);
                    Button submitFooter = (Button) convertView.findViewById(R.id.submitFooter);
                    footerHolder.submitFooter = submitFooter;
                    footerHolder.answerResultFooter = answerResultFooter;
                    footerHolder.correctAnswerFooter = correctAnswerFooter;
                    convertView.setTag(footerHolder);
                }else{
                    footerHolder = (FooterHolder)convertView.getTag();
                }
/*                // 单选题隐藏按钮，直接显示结果
                if("单选题".equals(order.getQuestionType())){
                    order.setFootVisibility("GONE,VISIBLE");
                }else if("多选题".equals(order.getQuestionType())){
                    order.setFootVisibility("VISIBLE,VISIBLE");
                }*/
                // 一定要设置该语句
/*                footerHolder.submitFooter.setFocusable(false);
                int childCount = this.getChildrenCount(groupPOSPostion);
                footerHolder.submitFooter.setOnClickListener(new submitBtnClick(
                        convertView,groupPOSPostion,childPOSPosition,childCount,this
                ));
                if("INVISIBLE,VISIBLE".equals(order.getFootVisibility())) {
                    footerHolder.submitFooter.setVisibility(INVISIBLE);
                    footerHolder.answerResultFooter.setVisibility(VISIBLE);
                }else if("VISIBLE,VISIBLE".equals(order.getFootVisibility())){
                    footerHolder.submitFooter.setVisibility(VISIBLE);
                    footerHolder.answerResultFooter.setVisibility(VISIBLE);
                }else if("INVISIBLE,INVISIBLE".equals(order.getFootVisibility())){
                    footerHolder.submitFooter.setVisibility(INVISIBLE);
                    footerHolder.answerResultFooter.setVisibility(INVISIBLE);
                }else if("VISIBLE,INVISIBLE".equals(order.getFootVisibility())) {
                    footerHolder.submitFooter.setVisibility(VISIBLE);
                    footerHolder.answerResultFooter.setVisibility(INVISIBLE);
                }else if("GONE,GONE".equals(order.getFootVisibility())){
                    footerHolder.submitFooter.setVisibility(GONE);
                    footerHolder.answerResultFooter.setVisibility(GONE);
                }else if("VISIBLE,GONE".equals(order.getFootVisibility())){
                    footerHolder.submitFooter.setVisibility(VISIBLE);
                    footerHolder.answerResultFooter.setVisibility(GONE);
                }else if("GONE,VISIBLE".equals(order.getFootVisibility())) {
                    footerHolder.submitFooter.setVisibility(GONE);
                    footerHolder.answerResultFooter.setVisibility(VISIBLE);
                }
                if(order.getAnswerResult()==null){
                    footerHolder.answerResultFooter.setText("答题结果：_______");
                }else {
                    footerHolder.answerResultFooter.setText("答题结果:  " + order.getAnswerResult().toString());
                }
                footerHolder.correctAnswerFooter.setText("正确答案:  " + order.getCorrectAnswer());*/


                break ;
            }
        } while(false) ;

        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //setSelectedPosition(groupPOSPostion, childPOSPosition);
                notifyDataSetChanged();
            }
        });
        // Log.d("getChildView end ","************************************************");
        return convertView;
    }



    private class submitBtnClick implements Button.OnClickListener{
        View view = null;
        int groupPOSPostion=0;
        int childPOSPosition=0;
        int childCount = 0;
        QuestionAdapter adapter;
        public submitBtnClick(View view,int groupPOSPostion,int childPOSPosition,int childCount,QuestionAdapter adapter){
            this.view = view;
            this.groupPOSPostion = groupPOSPostion;
            this.childPOSPosition = childPOSPosition;
            this.childCount = childCount;
            this.adapter = adapter;
        }
        @Override
        public void onClick(View view) {
            //markingQuestions(groupPOSPostion,childCount);
            adapter.notifyDataSetChanged();
        }
    }

    private class CheckedChange implements CompoundButton.OnCheckedChangeListener{
        View view = null;
        int groupPOSPostion=0;
        int childPOSPosition=0;
        int childCount =0;
        QuestionAdapter  adapter;
        public CheckedChange(View view,int groupPOSPostion,int childPOSPosition,QuestionAdapter adapter,int childCount){
            this.view = view;
            this.groupPOSPostion = groupPOSPostion;
            this.childPOSPosition = childPOSPosition;
            this.adapter = adapter;
            this.childCount = childCount;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            //setOnCheckedChanged(view,groupPOSPostion,childPOSPosition,b);
            Log.d("CheckedChange","end----------------------------------------------");
        }
    }

    @Override
    public int getChildType(int groupPOSPostion, int childPOSPosition) {
        boolean isHeadOrFoot = childPOSPosition == 0 || (childPOSPosition == getChildrenCount(groupPOSPostion) - 1) ;
        //如果是Header或者Footer,返回一个负数,我们返回ListView默认值
        if (isHeadOrFoot) {
            return ListView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER ;
        }

        return super.getChildType(groupPOSPostion, childPOSPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPOSPostion, int childPOSPosition) {
        return false;
    }

    static class ChildHolder {
        public CheckBox optSeq ;
        public TextView optContent ;
        public TextView quantity ;
    }

    static class FooterHolder{
        public TextView answerResultFooter;
        public TextView correctAnswerFooter;
        public Button submitFooter;
    }



}
