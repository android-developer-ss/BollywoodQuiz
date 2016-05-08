package com.svs.myprojects.bollywoodquiz.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Snehal on 5/7/16.
 */
public class AlertDialogFragment extends DialogFragment {

    //region Member Variables
    private String mAlertMessage;
    private String mAlertTitle;
    private String mPositiveButtonTitle;
    private String mNegativeButtonTitle;
    private static final String ALERT_TITLE_KEY = "alertTitle";
    private static final String ALERT_MESSAGE_KEY = "alertMessage";
    private static final String POSITIVE_BUTTON_TITLE = "positiveButtonTitle";
    private static final String NEGATIVE_BUTTON_TITLE = "negativeButtonTitle";
    //endregion

    //region PositiveButton click Listener
    private OnPositiveButtonClickListener mOnPositiveButtonClickListener;

    public interface OnPositiveButtonClickListener {
        public void onPositiveButtonListener();
    }

    public void setOnPositiveButtonClickListener(OnPositiveButtonClickListener onPositiveButtonClickListener) {
        mOnPositiveButtonClickListener = onPositiveButtonClickListener;
    }
    //endregion

    //region NegativeButton Click Listener
    private OnNegativeButtonClickListener mOnNegativeButtonClickListener;

    public interface OnNegativeButtonClickListener {
        public void onNegativeButtonListener();
    }

    public void setOnNegativeButtonClickListener(OnNegativeButtonClickListener onNegativeButtonClickListener) {
        mOnNegativeButtonClickListener = onNegativeButtonClickListener;
    }
    //endregion

    //region Static Method for initiating AlertDialog fragment
    public static AlertDialogFragment newInstance(String alertTitle, String alertMessage, String positiveButtonTitle,
                                                  String negativeButtonTitle) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(ALERT_TITLE_KEY, alertTitle);
        args.putString(ALERT_MESSAGE_KEY, alertMessage);
        args.putString(POSITIVE_BUTTON_TITLE, positiveButtonTitle);
        args.putString(NEGATIVE_BUTTON_TITLE, negativeButtonTitle);
        alertDialogFragment.setArguments(args);

        return alertDialogFragment;
    }
    //endregion

    //region Fragment Life Cycle
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        loadArguments();

        builder.setTitle(mAlertTitle != null ? mAlertTitle : "");
        builder.setMessage(mAlertMessage != null ? mAlertMessage : "");

        if (mNegativeButtonTitle != null) {
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mOnNegativeButtonClickListener != null) {
                        mOnNegativeButtonClickListener.onNegativeButtonListener();
                    } else {
                        dismiss();
                    }
                }
            });
        }

        if (mPositiveButtonTitle != null) {
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mOnPositiveButtonClickListener != null) {
                        mOnPositiveButtonClickListener.onPositiveButtonListener();
                    } else {
                        dismiss();
                    }
                }
            });
        }

        return builder.create();
    }
    //endregion

    //region Loading Bundle arguments
    private void loadArguments() {
        mAlertTitle = getArguments().getString(ALERT_TITLE_KEY);
        mAlertMessage = getArguments().getString(ALERT_MESSAGE_KEY);
        mPositiveButtonTitle = getArguments().getString(POSITIVE_BUTTON_TITLE);
        mNegativeButtonTitle = getArguments().getString(NEGATIVE_BUTTON_TITLE);
    }
    //endregion
}

