package com.scionoftech.recycleviewwithtabs.util;

/**
 * Created by kshediv on 5/11/15.
 */

import android.content.Context;
import android.content.res.TypedArray;

import com.scionoftech.recycleviewwithtabs.R;


public class CordinateUtils {


    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();


        return toolbarHeight;
    }


    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }
}