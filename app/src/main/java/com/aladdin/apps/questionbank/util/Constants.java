package com.aladdin.apps.questionbank.util;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by AndySun on 2016/9/19.
 */
public class Constants {
    //public static final String restfulEndpoints = "http://192.168.2.104:8081/MajorBank/api/";
    public static final String restfulEndpoints = "http://10.10.8.102:8081/MajorBank/api/";
    public static final String  getCustomersById= "questionBanks";
    public static final String  getLoginByAccountNumberUrl= "login/user/validate";
    public static final String  createNewUserUrl = "login/user";
    public static final String  getAllPkgsUrl = "login/package";
    public static final String  crudPackagePartialUrl = "login/package";
    public static final String  getAllOrderssUrl = "me/orders";
    public static final String  crudOrderPartialUrl = "me/orders";
    // login/package/{jobId}/auto
    public static final String getAutoPkgsByJobIdOneUrl = "login/package/";
    public static final String getAutoPkgsByJobIdTwoUrl = "/auto";
    // 获得定购的题库
    public static final String getOrderQuestionBankUrl = "orderQuestionBank";
    // 根据题库ID来获得题库内容
    public static final String getQuestionsByBankIDPartOneUrl = "questions/";

    // 根据错题questionIDs来获得题库内容
    public static final String getQuestionsByQuestionIdsUrl = "questions/qids";

    // 插入新的Answer记录
    public static final String postBankAnswersUrl = "answers/";
    // 更新订单状态
    public static final String  updateOrderStatusUrl = "me/orders";

    // 根据用户ID获得订单信息
    public static final String getOrderInfoByUserIdUrl = "me/orders";

    // 根据用户输入条件获得职位信息列表
    public static final String getPositionsUrl = "positions/";
}