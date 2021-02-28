package com.noahedu.yxpdialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;

import com.noahedu.yxpdialog.dialog.BaseCommonDialog;
import com.noahedu.yxpdialog.dialog.ViewHolder;
import com.noahedu.yxpdialog.utils.NetworkUtils;


public class SetNetworkDialog extends BaseCommonDialog implements View.OnClickListener {

    private View.OnClickListener mOnClickListener;

    @Override
    public int intLayoutId() {
        return R.layout.dialog_set_network2;
    }

    @Override
    public void convertView(ViewHolder holder, BaseCommonDialog dialog) {

        holder.setOnClickListener(R.id.selector_set_network_button, this);
        holder.setOnClickListener(R.id.selector_set_network_exit, this);

        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener()
        {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    return true;
                }else if(keyCode == KeyEvent.KEYCODE_MENU) {

                    return false;
                }
                return false;
            }
        });
    }

    public void setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.selector_set_network_button) {
            startActivityToWifiNetSetting();
            Activity activity = getActivity();
            if(mOnClickListener != null){

                mOnClickListener.onClick(v);
            }
        }
    }

    public void startActivityToWifiNetSetting() {

        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtils.isConnected(getContext().getApplicationContext())) {
            dismiss();
        }
    }

    public interface OnNetworkListener{



    }

}
