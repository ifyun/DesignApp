package com.cloud.design.viewmodel;

import android.view.View;
import android.widget.Toast;

public class ItemTitleBarVM {
    public void onMoreClick(View v) {
        Toast.makeText(v.getContext(), "Clicked More", Toast.LENGTH_SHORT).show();
    }
}
