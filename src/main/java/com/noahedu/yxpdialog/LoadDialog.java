package com.noahedu.yxpdialog;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.noahedu.yxpdialog.dialog.BaseCommonDialog;
import com.noahedu.yxpdialog.dialog.ViewHolder;


/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class LoadDialog extends BaseCommonDialog {

    private ImageView loadImg;
    private AnimationDrawable mAnimationDrawable;


    public static LoadDialog getInstance() {

        LoadDialog loadDialog = new LoadDialog();

        return loadDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDimAmount(0.2f);
    }


    @Override
    public int intLayoutId() {
        return R.layout.dialog_load;
    }

    @Override
    public void convertView(ViewHolder holder, BaseCommonDialog dialog) {

/*        getDialog().setOnShowListener(this);
        getDialog().setOnCancelListener(this);
        getDialog().setOnDismissListener(this);*/
        loadImg = (ImageView) holder.getConvertView().findViewById(R.id.load_img);
        mAnimationDrawable = (AnimationDrawable) loadImg.getBackground();
        if (mAnimationDrawable != null) {

            mAnimationDrawable.start();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mAnimationDrawable != null) {

            mAnimationDrawable.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private static final String TAG = "LoadDialog";

}
