package com.cloud.design.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloud.customviews.ColoredToast;
import com.cloud.design.R;
import com.cloud.design.databinding.ActivityMainBinding;
import com.cloud.customviews.InfoDialog;
import com.cloud.customviews.WarningDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Info Dialog
        mBinding.buttonInfoDialog.setOnClickListener(v -> {
            InfoDialog infoDialog = new InfoDialog.Builder(this)
                    .setTitle("Done")
                    .setMessage("Something done")
                    .setButton("OK", view ->
                            new ColoredToast.Maker(this)
                                    .setColor(R.color.colorWhite, R.color.colorGreen)
                                    .makeToast("Clicked OK", ColoredToast.LENGTH_SHORT)
                                    .show()
                    ).create();
            infoDialog.show();
        });

        //Warning Dialog
        mBinding.buttonWarningDialog.setOnClickListener(v -> {
            WarningDialog warningDialog = new WarningDialog.Builder(this)
                    .setTitle("Warning")
                    .setMessage("This is a warning dialog.\nClick any button to close the dialog.")
                    .setPositiveButton("Confirm", view ->
                        new ColoredToast.Maker(this)
                                .setColor(R.color.colorWhite, R.color.colorBlue)
                                .makeToast("Confirm clicked", ColoredToast.LENGTH_SHORT)
                                .show()
                    )
                    .create();
            warningDialog.show();
        });

        //RecycleView
        mBinding.buttonShowRecycleView.setOnClickListener(v ->
                startActivity(new Intent(this, RecycleActivity.class))
        );
    }
}
