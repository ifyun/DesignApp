package com.cloud.design.viewmodel;

import android.view.View;
import android.widget.Toast;

public class ItemLargeVM {
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Item", Toast.LENGTH_SHORT).show();
    }
}
