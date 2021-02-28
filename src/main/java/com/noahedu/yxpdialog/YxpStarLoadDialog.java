package com.noahedu.yxpdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.noahedu.yxpdialog.anim.YxpAnimStarControl;
import com.noahedu.yxpdialog.dialog.BaseCommonDialog;
import com.noahedu.yxpdialog.dialog.CommonDialog;
import com.noahedu.yxpdialog.dialog.ViewHolder;


/**
 * @author shaoqw
 *
 * @decirption 熊猫加载框
 */
public class YxpStarLoadDialog extends CommonDialog implements DialogInterface.OnKeyListener{

    private ImageView content;
    private YxpAnimStarControl mYxpAnimStarControl;

    public static YxpStarLoadDialog getInstance() {

        YxpStarLoadDialog loadDialog = new YxpStarLoadDialog();
        loadDialog.setDimAmount(0);
        loadDialog.setOutCancel(false);
        loadDialog.setCancelable(false);
        return loadDialog;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //此处捕获back操作，如果不希望所在的Activity监听到back键，需要返回true，消费掉。
            dismissAllowingStateLoss();
            return true;
        }else {
            //这里注意当不是返回键时需将事件扩散，否则无法处理其他点击事件
            return false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_yxp_star_load;
    }

    @Override
    public void convertView(ViewHolder holder, BaseCommonDialog dialog) {
        super.convertView(holder, dialog);
        content = (ImageView) holder.getConvertView().findViewById(R.id.content);
        mYxpAnimStarControl = new YxpAnimStarControl(getContext(),content);
        show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hide();
    }

    private void show(){
        if(mYxpAnimStarControl != null){
            mYxpAnimStarControl.start();
        }
    }

    private void hide(){
        if(mYxpAnimStarControl != null){
            mYxpAnimStarControl.stop();
        }

        AlertDialog dialog;
    }

}
