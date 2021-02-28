package com.noahedu.yxpdialog.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.noahedu.yxpdialog.R;


public abstract class BaseCommonDialog extends YxpDialogFragment  implements DialogInterface.OnCancelListener,DialogInterface.OnDismissListener,DialogInterface.OnShowListener{
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String BOTTOM = "show_bottom";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    private int margin;//左右边距
    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private boolean showBottom;//是否底部显示
    private boolean outCancel = true;//是否点击外部取消
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    private int gravity;

    public abstract int intLayoutId();

    public abstract void convertView(ViewHolder holder, BaseCommonDialog dialog);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.core_aistudy_NiceDialog);
        layoutId = intLayoutId();

        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            showBottom = savedInstanceState.getBoolean(BOTTOM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
//        {
//            boolean isShow = this.getShowsDialog();
//            this.setShowsDialog(false);
//            super.onActivityCreated(savedInstanceState);
//            this.setShowsDialog(isShow);
//
//            View view = getView();
//            if (view != null)
//            {
//                if (view.getParent() != null)
//                {
//                    throw new IllegalStateException(
//                            "DialogFragment can not be attached to a container view");
//                }
//                this.getDialog().setContentView(view);
//            }
//            final Activity activity = getActivity();
//            if (activity != null)
//            {
//                this.getDialog().setOwnerActivity(activity);
//            }
//            this.getDialog().setCancelable(this.isCancelable());
//            if (savedInstanceState != null)
//            {
//                Bundle dialogState = savedInstanceState.getBundle("android:savedDialogState");
//                if (dialogState != null)
//                {
//                    this.getDialog().onRestoreInstanceState(dialogState);
//                }
//            }
//        }
//        else
//        {
//            super.onActivityCreated(savedInstanceState);
//        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);
        convertView(ViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        initParams();
        super.onStart();
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(BOTTOM, showBottom);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            //是否在底部显示
            if (showBottom) {
                lp.gravity = Gravity.BOTTOM;
                if (animStyle == 0) {
                    animStyle = R.style.core_aistudy_DefaultAnimation;
                }
            } else {

                lp.gravity = gravity;
            }

            //设置dialog宽度
            if (width == 0) {
                lp.width = getScreenWidth(getContext()) - 2 * dp2px(getContext(), margin);
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.width = dp2px(getContext(), width);
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = dp2px(getContext(), height);
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            window.setAttributes(lp);
        }
        setCancelable(outCancel);
        if(getDialog() != null){

            getDialog().setCanceledOnTouchOutside(outCancel);
        }
    }

    public BaseCommonDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseCommonDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseCommonDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BaseCommonDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BaseCommonDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public BaseCommonDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public BaseCommonDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BaseCommonDialog show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commitNow();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
//        if (this.isAdded()) {
//            ft.show(this);
//            ft.commitNow();
//        }
//        else
//        {
//            ft.add(this, String.valueOf(System.currentTimeMillis()));
//            ft.show(this);
//            ft.commitNow();
//        }
        return this;
    }

    public int getGravity() {
        return gravity;

    }

    public BaseCommonDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    private static final String TAG = "BaseCommonDialog";

    @Override
    public void onShow(DialogInterface dialog) {

//        YxpLogUtils.e(TAG, "sqw  onShow: ");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

//        YxpLogUtils.e(TAG, "sqw  onCancel: ");
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

//        YxpLogUtils.e(TAG, "sqw  onDismiss: ");
    }
}
