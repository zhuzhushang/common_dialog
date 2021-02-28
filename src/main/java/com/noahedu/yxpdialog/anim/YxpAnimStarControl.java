package com.noahedu.yxpdialog.anim;

import android.content.Context;
import android.view.View;

import com.noahedu.yxpdialog.R;
import com.noahedu.yxpdialog.constants.DdsAnimationConstants;


/**
 *
 */
public class YxpAnimStarControl {

    private int starArray[] = {R.drawable.core_aistudy_youyou_loading01, R.drawable.core_aistudy_youyou_loading02,
            R.drawable.core_aistudy_youyou_loading03, R.drawable.core_aistudy_youyou_loading04, R.drawable.core_aistudy_youyou_loading05,
            R.drawable.core_aistudy_youyou_loading06, R.drawable.core_aistudy_youyou_loading07, R.drawable.core_aistudy_youyou_loading08,

    };

    private View load;
    private Context mContext;
    private AnimUtils starAnimUtils;


    public YxpAnimStarControl(Context context,View load) {
        this.mContext = context;
        this.load = load;
        starAnimUtils = new AnimUtils(load,starArray,true,0, DdsAnimationConstants.DDS_ANIM_DELAY_TIME);

    }


    public void start(){

        if(starAnimUtils != null){

            starAnimUtils.startAnim();
        }
    }


    public void stop(){

        if(starAnimUtils != null){

            starAnimUtils.stopAnim();
        }
    }


}
