package com.noahedu.yxpdialog.dialog;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

public class YxpWeakOnShowListener implements DialogInterface.OnShowListener {

    private WeakReference<DialogInterface.OnShowListener> mWeakOnShowListener;

    public YxpWeakOnShowListener(DialogInterface.OnShowListener onShowListener) {

        mWeakOnShowListener = new WeakReference<>(onShowListener);
    }

    @Override
    public void onShow(DialogInterface dialog) {

        DialogInterface.OnShowListener onShowListener = mWeakOnShowListener.get();
        if(onShowListener != null){

            onShowListener.onShow(dialog);
        }
    }
}
