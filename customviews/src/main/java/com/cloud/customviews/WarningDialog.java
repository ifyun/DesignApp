package com.cloud.customviews;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WarningDialog extends Dialog {

    private WarningDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private View mLayout;

        private TextView mTitle;
        private TextView mMessage;
        private Button mPositiveButton;
        private Button mNegativeButton;

        private View.OnClickListener mPositiveListener;
        private View.OnClickListener mNegativeListener;

        private WarningDialog mDialog;

        public Builder(Context context) {
            mDialog = new WarningDialog(context, R.style.WarningDialogTheme);
            LayoutInflater inflater = LayoutInflater.from(context);
            mLayout = inflater.inflate(R.layout.dialog_warning, null);

            mTitle = mLayout.findViewById(R.id.dialog_title);
            mMessage = mLayout.findViewById(R.id.dialog_message);
            mNegativeButton = mLayout.findViewById(R.id.dialog_button_negative);
            mPositiveButton = mLayout.findViewById(R.id.dialog_button_positive);
        }

        public Builder setTitle(int resId) {
            mTitle.setText(resId);
            return this;
        }

        public Builder setTitle(@NonNull String text) {
            mTitle.setText(text);
            return this;
        }

        public Builder setMessage(int resId) {
            mMessage.setText(resId);
            return this;
        }

        public Builder setMessage(@NonNull String text) {
            mMessage.setText(text);
            return this;
        }

        public Builder setPositiveButton(int resId, View.OnClickListener listener) {
            mPositiveButton.setText(resId);
            mPositiveListener = listener;
            return this;
        }

        public Builder setPositiveButton(@NonNull String text, View.OnClickListener listener) {
            mPositiveButton.setText(text);
            mPositiveListener = listener;
            return this;
        }

        public Builder setNegativeButton(int resId, View.OnClickListener listener) {
            mNegativeButton.setText(resId);
            mNegativeListener = listener;
            return this;
        }

        public Builder setNegativeButton(@NonNull String text, View.OnClickListener listener) {
            mNegativeButton.setText(text);
            mNegativeListener = listener;
            return this;
        }

        public WarningDialog create() {
            mDialog.setContentView(mLayout);
            mPositiveButton.setOnClickListener(v -> {
                if (mPositiveListener != null)
                    mPositiveListener.onClick(v);
                mDialog.dismiss();
            });
            mNegativeButton.setOnClickListener(v -> {
                if (mNegativeListener != null)
                    mNegativeListener.onClick(v);
                mDialog.dismiss();
            });
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(true);
            return mDialog;
        }
    }
}
