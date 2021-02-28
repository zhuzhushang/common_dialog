package com.noahedu.yxpdialog.constants;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class DialogConstants {


    //主页请求网络错误后弹dialog
    public final static int DIALOG_TYPE_MAIN_PAGE_NET_ERROR = 0x1001;

    //选择书本界面 没有选择书本时 点击完成，弹出
    public final static int DIALOG_TYPE_NO_BOOK_ON_COMPLITE_CLICK = 0x2001;


    //加载框使用到的dialog

    /**
     * 表示不用加载框
     */
    public static int CALLBACK_DIALOG_TYPE_NONE = 0;
    /**经典加载框1*/
    public static int CALLBACK_DIALOG_TYPE_INTELLIGENT_SCHEME = 1;
    /**熊猫加载框*/
    public static int CALLBACK_DIALOG_TYPE_PANDA = 2;
    /**经典加载框1*/
    public static int CALLBACK_DIALOG_TYPE_INTELLIGENT_SCHEME_OUT_CANCLE = 3;

}
