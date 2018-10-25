package com.cloud.design.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.cloud.customviews.ColoredToast;
import com.cloud.customviews.ProgressButton;
import com.cloud.design.R;
import com.cloud.design.databinding.ActivityMainBinding;
import com.cloud.customviews.InfoDialog;
import com.cloud.customviews.WarningDialog;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private int mProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Colored Toast
        mBinding.buttonToast.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toast, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.blue_toast:
                        new ColoredToast.Maker(this)
                                .setColor(R.color.colorWhite, R.color.colorBlue)
                                .makeToast("This is a blue toast!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.green_toast:
                        new ColoredToast.Maker(this)
                                .setColor(R.color.colorWhite, R.color.colorGreen)
                                .makeToast("This is a green toast!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.yellow_toast:
                        new ColoredToast.Maker(this)
                                .setColor(R.color.colorWhite, R.color.colorYellow)
                                .makeToast("This is a yellow toast!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                return true;
            });
            popupMenu.show();
        });

        //Info Dialog
        mBinding.buttonInfoDialog.setOnClickListener(v -> {
            InfoDialog infoDialog = new InfoDialog.Builder(this)
                    .setTitle("Done")
                    .setMessage("Something done")
                    .setButton("OK", view ->
                            new ColoredToast.Maker(this)
                                    .setColor(R.color.colorWhite, R.color.colorGreen)
                                    .makeToast("Clicked OK", Toast.LENGTH_SHORT)
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
                                    .makeToast("Confirm clicked", Toast.LENGTH_SHORT)
                                    .show()
                    )
                    .create();
            warningDialog.show();
        });

        //RecycleView
        mBinding.buttonShowRecycleView.setOnClickListener(v ->
                startActivity(new Intent(this, RecycleActivity.class))
        );

        mBinding.buttonProgress.setMaxProgress(100);
        mBinding.buttonProgress.setOnClickListener(v -> {
            v.setClickable(false);
            ((ProgressButton) v).reset();
            mProgress = 0;
            new Thread(() -> {
                while (mProgress <= 100) {
                    ((ProgressButton) v).setProgress(mProgress);
                    ((ProgressButton) v).setText(MessageFormat.format("{0}%", mProgress));
                    try {
                        Thread.sleep(100);
                        mProgress += 5;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(() -> v.setClickable(true));
            }).start();
        });
    }
}
