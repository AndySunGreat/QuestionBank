package com.aladdin.apps.questionbank.common.expandablelistview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aladdin.apps.questionbank.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.ExpandableListView.*;

/**
 * Created by AndySun on 2016/10/12.
 */
public class QuestionAdapter extends BaseExpandableListAdapter {
    public static Map<Integer, Map<Integer, Boolean>> isSelectedMap;
    private List<QuestionOrder> orderList ;
    private LayoutInflater inflater ;
    private View group ;
    private QuestionOrder questionOrder;
    public QuestionAdapter(List<QuestionOrder> orderList,Context context) {
        this.orderList = orderList ;
        inflater = LayoutInflater.from(context) ;
        group = new FrameLayout(context) ;
        setDefaultChkList();
    }

    public Map getAnswersResultMap(){
        Map answerResultMap = new HashMap();
        int groupPosition = this.getGroupCount();
        int childrenCount = 0;
        List<QuestionOrder> questionOrderList  = new ArrayList<>();
        int score =0;
        int wrongQuestionCount = 0;
        String strWrongQuestIds = "";
        for(int i=0;i<groupPosition-1;i++) {
            questionOrder  = (QuestionOrder) getGroup(i);
            childrenCount = this.getChildrenCount(i);
            questionOrder = markingQuestions(i,childrenCount);
            if("问题错误".equals(questionOrder.getAnswerResult())){
                strWrongQuestIds =  strWrongQuestIds + questionOrder.getQuestionId() + ",";
                wrongQuestionCount++;
            }
            questionOrderList.add(questionOrder);
        }
        if(strWrongQuestIds.endsWith(",")) {
            strWrongQuestIds = strWrongQuestIds.substring(0, strWrongQuestIds.length() - 1);
        }
        score = wrongQuestionCount/groupPosition*100;
        answerResultMap.put("wrongQuestIds",strWrongQuestIds);
        answerResultMap.put("score",score);
        return answerResultMap;
    }



    @Override
    public int getGroupCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<QuestionItem> children = ((QuestionOrder)orderList.get(groupPosition)).getItems();
        int size = children.size() ;
        // 加上header和footer
        return size == 0 ? 0 : size + 2 ;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return orderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.d("groupPosition:",groupPosition+"");
        Log.d("childPosition:",childPosition+"");
        return orderList.get(groupPosition).getItems().get(childPosition) ;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // 初始化 设置所有checkbox都为未选择
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
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // 显示Group名称，即问题主题
            convertView = inflater.inflate(R.layout.common_view_group,null) ;
        }
        TextView group = (TextView) convertView.findViewById(R.id.tvGroup);
        group.setText("题" + (groupPosition + 1));

        return convertView;
    }



    // 得到小组成员的view
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d("getChildView begin ","************************************************");

        do {
            // 判断childPosition的索引数是否不是header或不是footer
            if (childPosition > 0 && childPosition < getChildrenCount(groupPosition) - 1) {
                QuestionItem item = (QuestionItem) getChild(groupPosition,childPosition - 1);
                ChildHolder holder ;
                // 复用View
                if (convertView == null) {
                    holder = new ChildHolder();
                    convertView = inflater.inflate(R.layout.common_view_order_item,null) ;
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

                int childCount=this.getChildrenCount(groupPosition);
                Log.d("testingGetView",childCount+"");
                Log.d("groupPosition:",groupPosition+"");
                Log.d("childPosition:",childPosition+"");
                Log.d("isLastChild:",String.valueOf(isLastChild));
                holder.optSeq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        Log.d("当前groupPosition:", groupPosition+"");
                        Log.d("当前childPosition",childPosition+"");
                         QuestionOrder order = (QuestionOrder) getGroup(groupPosition);
                        Log.d("QuesAdapter",order.getQuestionType());
                        int childCount = getChildrenCount(groupPosition);
                        // 1.单选题逻辑
                        if("单选题".equals(order.getQuestionType())){
                            // 1.1 若是单选题，则使同组的checkbox为false,
                            boolean setChckFlag = false;
                            String strChildPosition = childPosition + "";
                            if(isChecked) {
                                for (int i = 1; i <childCount-1; i++) {
                                    setChckFlag = false;
                                    if (i == childPosition) {
                                        setChckFlag=true;
                                    }
                                    isSelectedMap.get(groupPosition).put(i,setChckFlag);
                                }
                                // 1.2 判断单选题用户答案是否正确
                                if(strChildPosition.equals(order.getCorrectPostions())){
                                    // 回答正确,则直接收起该group的item内容,或是整体隐藏
                                    //order.setFootVisibility(View.VISIBLE);
                                    order.setAnswerResult("回答正确");
                                }else{
                                    // 若错误在footer中显示“回答错误”
                                    order.setAnswerResult("回答错误");
                                }
                            }else{
                                isSelectedMap.get(groupPosition).put(childPosition,false);
                            }

                        }else if("多选题".equals(order.getQuestionType())){
                            // 若多选题，则在footer中显示"提交答案"按钮。
                            //isSelectedMap.get(groupPosition).put(childPosition, isChecked);
                            //Log.d("multiple-choose","clicked");
                            //Log.d("groupPosition",groupPosition+"");
                            //Log.d("childPosition",childPosition+"");
                        }
                        notifyDataSetChanged();//注意这一句必须加上，否则checkbox无法正常更新状
                    }
                });
                // 这点语句必须在设置监听器之后，
                holder.optSeq.setChecked(isSelectedMap.get(groupPosition).get(childPosition));
                holder.optContent.setText(item.getOptContent());
                //holder.quantity.setText(String.valueOf(item.getQuantity()));
                holder.quantity.setText("100.01");
                break ;
            }

            QuestionOrder order = (QuestionOrder) getGroup(groupPosition);

            // 判断是否是header
            if (childPosition == 0) {
                convertView = inflater.inflate(R.layout.common_view_order_header,null) ;
                TextView header = (TextView) convertView.findViewById(R.id.tvHeader);
                header.setText(order.getQuestTitle());
                //header.setText("共" + order.getItems().size() + "份美食");
                break ;
            }

            if (childPosition == getChildrenCount(groupPosition) - 1) {
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
                // 单选题隐藏按钮，直接显示结果
                if("单选题".equals(order.getQuestionType())){
                    order.setFootVisibility("GONE,VISIBLE");
                }else if("多选题".equals(order.getQuestionType())){
                    order.setFootVisibility("VISIBLE,VISIBLE");
                }
                // 一定要设置该语句
                footerHolder.submitFooter.setFocusable(false);
                int childCount = this.getChildrenCount(groupPosition);
                footerHolder.submitFooter.setOnClickListener(new submitBtnClick(
                        convertView,groupPosition,childPosition,childCount,this
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
                footerHolder.correctAnswerFooter.setText("正确答案:  " + order.getCorrectAnswer());
                break ;
            }
        } while(false) ;

        convertView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //setSelectedPosition(groupPosition, childPosition);
                notifyDataSetChanged();
            }
        });
        Log.d("getChildView end ","************************************************");
        return convertView;
    }

    // 对每一个试题进行批卷打分，将错误生成字符串(wrong_quest_ids)以及成绩(score)返回。
    public QuestionOrder markingQuestions(int groupPosition,int childCount){
        QuestionOrder order = (QuestionOrder) getGroup(groupPosition);
        Log.d("childCount",childCount+"");
        String strChildPosition = "";
        for (int i = 1; i < childCount - 1; i++) {
            if(isSelectedMap.get(groupPosition).get(i)){
                strChildPosition = strChildPosition + i + ",";
            }
        }
        if(strChildPosition.endsWith(",")) {
            strChildPosition = strChildPosition.substring(0, strChildPosition.length() - 1);
        }
        Log.d("strChildPosition",strChildPosition);
        Log.d("order:",order.getCorrectPostions()+"");
        // 1.2 判断单选题用户答案是否正确
        if(strChildPosition.equals(order.getCorrectPostions())){
            // 回答正确,则直接收起该group的item内容,或是整体隐藏
            //order.setFootVisibility(View.VISIBLE);
            order.setAnswerResult("回答正确");
            order.setFootVisibility("GONE,VISIBLE");
        }else{
            // 若错误在footer中显示“回答错误”
            order.setAnswerResult("回答错误");
        }
        return order;
    }

    private class submitBtnClick implements Button.OnClickListener{
        View view = null;
        int groupPosition=0;
        int childPosition=0;
        int childCount = 0;
        QuestionAdapter adapter;
        public submitBtnClick(View view,int groupPosition,int childPosition,int childCount,QuestionAdapter adapter){
            this.view = view;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
            this.childCount = childCount;
            this.adapter = adapter;
        }
        @Override
        public void onClick(View view) {
            markingQuestions(groupPosition,childCount);
            adapter.notifyDataSetChanged();
        }
    }

    private class CheckedChange implements CompoundButton.OnCheckedChangeListener{
        View view = null;
        int groupPosition=0;
        int childPosition=0;
        int childCount =0;
        QuestionAdapter  adapter;
        public CheckedChange(View view,int groupPosition,int childPosition,QuestionAdapter adapter,int childCount){
            this.view = view;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
            this.adapter = adapter;
            this.childCount = childCount;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            // TODO Auto-generated method stub
            //setOnCheckedChanged(view,groupPosition,childPosition,b);
            Log.d("CheckedChange","end----------------------------------------------");
        }
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        boolean isHeadOrFoot = childPosition == 0 || (childPosition == getChildrenCount(groupPosition) - 1) ;
        //如果是Header或者Footer,返回一个负数,我们返回ListView默认值
        if (isHeadOrFoot) {
            return ListView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER ;
        }

        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
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


    /*
    public Map getCheckedChildPosition(final int groupPosition){
        Map checkedChildPostionMap = new HashMap();
        Map<Integer,Boolean> subMap = new HashMap<Integer,Boolean>();
        // 获得当前group的child account
        int childCount = this.getChildrenCount(groupPosition);
        for(int i=0;i<childCount;i++){
            if(isSelectedMap.get(groupPosition).get(i)){
                checkedChildPostionMap.put(i,"");
            }
        }
        return null;
    }*/
/*
    public void checkIfAnswerCorrect(View view, final int groupPosition,final int childPosition){
        // 获得当前group的所有数据
        QuestionOrder order = (QuestionOrder) getGroup(groupPosition);
        Log.d("QuesAdapter",order.getQuestionType());
        // 1.单选题逻辑
        if("单选题".equals(order.getQuestionType())){
            String strChildPosition = childPosition + "";
        }
    }*/
}
