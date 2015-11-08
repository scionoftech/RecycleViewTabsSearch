package com.scionoftech.recycleviewwithtabs.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by sky on 25/10/15.
 */
public class AnimationUtils {


    public AnimationUtils() {
    }

    public static void StartAnimation(RecyclerView.ViewHolder viewHolder)
    {
       /*ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(viewHolder.itemView,"translationY",-100,0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();*/
       YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(viewHolder.itemView);
    }
}
