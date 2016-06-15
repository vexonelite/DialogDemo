package com.vexonelite.dialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vexonelite.dialogdemo.dialogs.BaseDialogFragment;
import com.vexonelite.dialogdemo.dialogs.ConfirmDialog;
import com.vexonelite.dialogdemo.dialogs.ProgressDialog;
import com.vexonelite.dialogdemo.dialogs.ReclaimPwdDialog;


public class MainActivity extends AppCompatActivity {

    private static String getLogTag() {
        return MainActivity.class.getSimpleName();
    }

    private int mCountBackPress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.confirm_dialog_test);
        if (null != view) {
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ConfirmDialog dialog = new ConfirmDialog();
                    dialog.setCallbacks(
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Log.i(getLogTag(), "Positive");
                                }
                            },
                            new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Log.i(getLogTag(), "Negative");
                                }
                            });
                    dialog.setMessage("Ha Ha Ha");
                    dialog.show(getSupportFragmentManager(), "confirm_dialog");
                }
            });
        }

        view = findViewById(R.id.custom_dialog_test);
        if (null != view) {
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    showReclaimPwdDialog();
                }
            });
        }

        view = findViewById(R.id.progress_dialog_test);
        if (null != view) {
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this, "Loading...");
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            });
        }
    }

    @Override
    public void onResume () {
        super.onResume();
        mCountBackPress = 0;
    }

    private void showReclaimPwdDialog() {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing dialog,
        // so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("ReclaimPwdDialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        ReclaimPwdDialog newFragment = new ReclaimPwdDialog();
        newFragment.setReclaimPwdListener(
                new ReclaimPwdDialog.ReclaimPwdListener() {
                    @Override
                    public void onNotification(DialogFragment dialogFrag, BaseDialogFragment.DialogAction dialogAction) {
                        dialogFrag.dismiss();
                        Log.i(getLogTag(), "DialogAction: " + dialogAction);
                    }

                    @Override
                    public void onSubmitButtonPressed(DialogFragment dialogFrag, BaseDialogFragment.DialogAction dialogAction, String email) {
                        dialogFrag.dismiss();
                        Log.i(getLogTag(), "DialogAction: " + dialogAction + ", Email: " + email);
                    }
                });

        newFragment.show(ft, "ReclaimPwdDialog");
    }



    @Override
    public void onBackPressed() {
        Log.w(getLogTag(), "onBackPressed!");

        if (mCountBackPress == 0) {
            mCountBackPress++;
            Toast.makeText(
                    this,
                    getResources().getString(R.string.hint_to_quit_app),
                    Toast.LENGTH_LONG).show();
        }
        else if (mCountBackPress == 1) {
            setResult(RESULT_CANCELED);
            finish();
        }

        //super.onBackPressed();
    }





}
