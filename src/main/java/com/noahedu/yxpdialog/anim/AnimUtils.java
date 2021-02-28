package com.noahedu.yxpdialog.anim;

import android.os.Handler;
import android.os.Message;
import android.view.View;

public class AnimUtils {
    private int[] resIdArr;
    private int index;
    private View view;
    private int period;//动画间隔
    boolean isRepeatFlag = false;
    private long delayMillis;//延时毫秒
    private volatile boolean isStopped = true;

    public interface OnAnimEndListener {
        public void onAnimEnd();

        //stop的时候调用
        public void onAnimFinish();

    }

    private OnAnimEndListener listener;

    public void setOnAnimEndListener(OnAnimEndListener listener) {
        this.listener = listener;
    }

    private final static int PLAYING_ANIM_MSG = 1234;
    private Handler handler;
    Handler.Callback mHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case PLAYING_ANIM_MSG: {
                    if (index >= resIdArr.length) {
                        if (isRepeatFlag) {
                            index = 0;
                        } else {
                            if (listener != null) {
                                listener.onAnimEnd();
                                listener.onAnimFinish();
                            }
                            return true;
                        }
                    }
                    if (!isStopped) {
                        handler.sendEmptyMessageDelayed(PLAYING_ANIM_MSG, period);
                    }
                    view.setBackgroundResource(resIdArr[index]);
                    index++;
                    break;
                }
            }
            return true;
        }
    };

    public AnimUtils() {
        index = 0;
        handler = new Handler(mHandlerCallback);
    }

    public void setAnimInfo(View view, int[] resIdArr, boolean isRepeatFlag) {
        this.view = view;
        this.resIdArr = resIdArr;
        this.isRepeatFlag = isRepeatFlag;
    }

    public void startAnim(long delayMillis, int period) {
        if (!isStopped) {
            return;
        }
        isStopped = false;
        this.period = period;
        index = 0;
        handler.sendEmptyMessageDelayed(PLAYING_ANIM_MSG, delayMillis);
    }

    public boolean isAniming() {
        return !isStopped;
    }

    public void stopAnim() {
        debugAnim("stopAnim");
        isStopped = true;
        handler.removeMessages(PLAYING_ANIM_MSG);
//		view.setBackgroundResource(resIdArr[0]);
        onAnimFinish();
    }

    public void stopAnim(boolean isSetZeroResource) {
        isStopped = true;
        handler.removeMessages(PLAYING_ANIM_MSG);
        if (isSetZeroResource) {

            view.setBackgroundResource(resIdArr[0]);
        }
        onAnimFinish();
    }

    private void debugAnim(String action) {

    }


    private void onAnimFinish() {

        debugAnim("onAnimFinish");

        if (listener != null) {

            listener.onAnimFinish();
        }
    }


    public AnimUtils(View view, int[] resIdArr, boolean isRepeatFlag, long delayMillis, int period) {

        index = 0;
        this.view = view;
        this.resIdArr = resIdArr;
        this.isRepeatFlag = isRepeatFlag;
        this.period = period;
        this.delayMillis = delayMillis;
        handler = new Handler(mHandlerCallback);
    }

    public AnimUtils(View view, int[] resIdArr, boolean isRepeatFlag, long delayMillis, int period, OnAnimEndListener listener) {

        index = 0;
        this.view = view;
        this.resIdArr = resIdArr;
        this.isRepeatFlag = isRepeatFlag;
        this.period = period;
        this.delayMillis = delayMillis;
        this.listener = listener;
        handler = new Handler(mHandlerCallback);
    }

    public void startAnim() {

        debugAnim("startAnim");
        if (!isStopped) {
            return;
        }
        isStopped = false;
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
            }
        });
        index = 0;
        handler.sendEmptyMessageDelayed(PLAYING_ANIM_MSG, delayMillis);
    }

    public void clear() {
        stopAnim();
        view.setBackground(null);
    }

    public boolean isStopped() {
        return isStopped;
    }
}
