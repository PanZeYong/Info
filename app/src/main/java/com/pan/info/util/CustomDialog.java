package com.pan.info.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.pan.info.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author : PZY
 * Date : 2016.9.13
 */
public class CustomDialog {

    private static CustomDialog sInstance;

    private Dialog mDialog;

    private OnDialogListener mListener;

    private View mView;

    private CustomDialog() {
    }

    public static CustomDialog getInstance() {
        if (null == sInstance) {
            synchronized (CustomDialog.class) {
                if (null == sInstance) {
                    sInstance = new CustomDialog();
                }
            }
        }

        return sInstance;
    }

    public void createDialog(Context context, int layoutId, boolean flag) {
        mView = LayoutInflater.from(context).inflate(layoutId, null);

        mDialog = new Dialog(context, R.style.CommonDialog);
        mDialog.setCancelable(false);

        if (flag) {
            mDialog.setCanceledOnTouchOutside(true);
        } else {
            ButterKnife.bind(this, mView);
            mDialog.setCanceledOnTouchOutside(false);
        }

        int height = context.getResources().getDisplayMetrics().heightPixels;
        int width = context.getResources().getDisplayMetrics().widthPixels;

        mDialog.getWindow().setLayout(width, height);
        mDialog.getWindow().setContentView(mView);
        mDialog.show();

    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

    public void dismissDialog() {
        ButterKnife.bind(this, mView).unbind();

        if (mDialog.isShowing() && null != mDialog) {
            mDialog.dismiss();
            mDialog = null;
            mListener = null;
            mView = null;
        } else if (null != mDialog) {
            mDialog = null;
            mListener = null;
            mView = null;
        }
    }

//    @OnClick({R.id.btn_sure, R.id.btn_cancel})
//    public void click(View view) {
//        switch (view.getId()) {
//            case R.id.btn_sure:
//                if (null != mListener) {
//                    mListener.sure();
//                }
//                break;
//
//            case R.id.btn_cancel:
//                dismissDialog();
//                break;
//
//            default:
//                break;
//        }
//    }

    public interface OnDialogListener {
        void sure();
    }
}
