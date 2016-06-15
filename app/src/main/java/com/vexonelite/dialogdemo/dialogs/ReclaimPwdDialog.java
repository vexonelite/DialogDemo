package com.vexonelite.dialogdemo.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vexonelite.dialogdemo.R;


public class ReclaimPwdDialog extends BaseDialogFragment {

	private EditText mUserInput;

	private ReclaimPwdListener mCallback;

	public interface ReclaimPwdListener extends OnDecisionMadeListener {
		void onSubmitButtonPressed (DialogFragment dialogFrag, DialogAction dialogAction, String email);
	}

	public void setReclaimPwdListener (ReclaimPwdListener listener) {
		this.mCallback = listener;
	}

	@Override
    public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.dialog_reclaim_pwd, container);
    }

	@Override
	public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
		//Log.d(getMyIdentifiedTag(), "onViewCreated");
		super.onViewCreated(rootView, savedInstanceState);

		mUserInput = (EditText) rootView.findViewById(R.id.email_input);

		View view = rootView.findViewById(R.id.submit_button);
		if (null != view) {
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					view.setEnabled(false);
					if (null != mCallback) {
						mCallback.onSubmitButtonPressed(
								ReclaimPwdDialog.this,
								DialogAction.CONFIRM,
								mUserInput.getText().toString().trim());
					}
					view.setEnabled(true);
				}
			});
		}

		view = rootView.findViewById(R.id.cancel_button);
		if (null != view) {
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					view.setEnabled(false);
					if (null != mCallback) {
						mCallback.onNotification(ReclaimPwdDialog.this, DialogAction.CANCEL);
					}
					view.setEnabled(true);
				}
			});
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		hideSoftKeyboard();
	}

	private void hideSoftKeyboard () {

	}
}
