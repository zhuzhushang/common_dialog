package com.noahedu.yxpdialog.dialog;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

public class YxpWeakOnDismissListener implements DialogInterface.OnDismissListener {

    private WeakReference<DialogInterface.OnDismissListener> mWeakOnDismissListener;

    public YxpWeakOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mWeakOnDismissListener = new WeakReference<>(onDismissListener);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

        DialogInterface.OnDismissListener onDismissListener = mWeakOnDismissListener.get();
        if(onDismissListener != null){

            onDismissListener.onDismiss(dialog);
        }
    }
}
