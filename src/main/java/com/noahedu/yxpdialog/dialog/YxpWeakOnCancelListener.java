package com.noahedu.yxpdialog.dialog;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

public class YxpWeakOnCancelListener implements DialogInterface.OnCancelListener {

    private WeakReference<DialogInterface.OnCancelListener> mWeakOnCancelListener;

    public YxpWeakOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mWeakOnCancelListener = new WeakReference<>(onCancelListener);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

        DialogInterface.OnCancelListener onCancelListener = mWeakOnCancelListener.get();
        if(onCancelListener != null){

            onCancelListener.onCancel(dialog);
        }
    }
}
