package com.cloud.design.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cloud.design.R;
import com.cloud.design.databinding.ActivityMainBinding;
import com.cloud.design.dialog.InfoDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.buttonShowDialog.setOnClickListener(v -> {
            InfoDialog infoDialog = new InfoDialog.Builder(this)
                    .setTitle("Done")
                    .setMessage("Something done")
                    .setButton("OK", view ->
                            Toast.makeText(this, "OK Clicked.", Toast.LENGTH_SHORT).show()
                    ).create();
            infoDialog.show();
        });

        mBinding.buttonShowRecycleView.setOnClickListener(v ->
                startActivity(new Intent(this, RecycleActivity.class))
        );
    }
}
