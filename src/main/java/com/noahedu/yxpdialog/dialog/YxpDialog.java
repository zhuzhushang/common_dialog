package com.noahedu.yxpdialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class YxpDialog extends Dialog {

    public YxpDialog(@NonNull Context context) {
        super(context);
    }

    public YxpDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void setOnCancelListener(@Nullable OnCancelListener listener) {
        super.setOnCancelListener(new YxpWeakOnCancelListener(listener));
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(new YxpWeakOnDismissListener(listener));
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(new YxpWeakOnShowListener(listener));
    }
}
