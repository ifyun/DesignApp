package com.cloud.design.viewmodel;

import android.view.View;

import com.cloud.customviews.ColoredToast;
import com.cloud.design.R;

public class ItemTitleBarVM {
    public void onMoreClick(View v) {
        new ColoredToast.Maker(v.getContext())
                .setColor(R.color.colorWhite, R.color.colorGreen)
                .makeToast("Clicked More", ColoredToast.LENGTH_SHORT)
                .show();
    }
}
