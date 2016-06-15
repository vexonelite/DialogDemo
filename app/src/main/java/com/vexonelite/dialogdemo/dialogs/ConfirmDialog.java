package com.vexonelite.dialogdemo.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ConfirmDialog extends DialogFragment {

    private DialogInterface.OnClickListener mPositiveCallback;
    private DialogInterface.OnClickListener mNegativeCallback;
    public boolean isSingleOption;

    private CharSequence mMessage;

    public ConfirmDialog () {
        this.setCancelable(false);
    }

    public void setCallbacks (DialogInterface.OnClickListener positiveCallback,
                              DialogInterface.OnClickListener negativeCallback) {
        mPositiveCallback = positiveCallback;
        mNegativeCallback = negativeCallback;
    }

    public void setMessage (CharSequence message) {
        mMessage = message;
    }

    public void setSingleOption (boolean flag) {
        isSingleOption = flag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity activity = getActivity();
        if (null == mMessage) {
            mMessage = "";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(mMessage);
        builder.setPositiveButton(android.R.string.ok, mPositiveCallback);
        if (!isSingleOption) {
            builder.setNegativeButton(android.R.string.no, mNegativeCallback);
        }
        return builder.create();
    }
}
