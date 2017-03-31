package com.pan.info.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pan.info.R;

/**
 * Author : Pan
 * Date : 20/03/2017
 */

public class CommonDialog extends Dialog{
    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public interface OnDialogButtonClickListener {
        void onClick(View view);
    }

    public static class Builder {

        private Context mContext;

        private int mTheme;
        private int layoutId;
        private CharSequence title;
        private CharSequence message;
        private CharSequence positiveButtonText;
        private CharSequence negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonListener;
        private DialogInterface.OnClickListener negativeButtonListener;

        private OnDialogButtonClickListener listener;

        private View contentView;

        private CommonDialog dialog;

        public Builder(@NonNull Context context) {
            this(context, R.style.CommonDialog);
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            this.mContext = context;
            this.mTheme = themeResId;
        }

        public Builder setLayoutId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.title = mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.message = mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            this.positiveButtonText = mContext.getText(textId);
            this.positiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(String text) {
            this.positiveButtonText = text;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            this.negativeButtonText = mContext.getText(textId);
            this.negativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(String text) {
            this.negativeButtonText = text;
            return this;
        }

        public Builder setOnDialogButtonClickListener(OnDialogButtonClickListener listener) {
            this.listener = listener;
            return this;
        }

        public CommonDialog create() {
            dialog = new CommonDialog(mContext, mTheme);
            contentView = LayoutInflater.from(mContext).inflate(layoutId, null);

            int height = mContext.getResources().getDisplayMetrics().heightPixels;
            int width = mContext.getResources().getDisplayMetrics().widthPixels;

            dialog.addContentView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView titleText = (TextView) contentView.findViewById(R.id.tv_title);
            titleText.setText(title);

            TextView messageText = (TextView) contentView.findViewById(R.id.tv_message);
            messageText.setText(message);

            Button negativeButton = (Button) contentView.findViewById(R.id.btn_negative);
            Button positiveButton = (Button) contentView.findViewById(R.id.btn_positive);

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != positiveButtonListener) {
                        listener.onClick(v);
                    }
                }
            });

            dialog.setContentView(contentView);
            dialog.setCanceledOnTouchOutside(false);

            return dialog;
        }

        public CommonDialog show() {
            if (null == dialog) {
                dialog = create();
            }
            dialog.show();
            return dialog;
        }

        public void dismiss() {
            if (null != dialog) {
                dialog.dismiss();
            }
        }
    }
}
