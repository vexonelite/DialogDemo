package com.vexonelite.dialogdemo.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.vexonelite.dialogdemo.R;


public class ProgressDialog extends android.app.ProgressDialog {

	private String mTitle;


	public ProgressDialog(Context context, String title) {
		super(context);
		this.mTitle = title;
		this.setIndeterminate(true);
		this.setCancelable(false);
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);

		TextView description = (TextView) findViewById(R.id.description);
		if (null != mTitle) {
			description.setText(mTitle);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		setWindowStyle();
	}

	/**
	 * Set desired looks of the dialog, including background image/ color,
	 * window size, margin, etc.
	 *
	 * Refs:
	 * https://gist.github.com/ishitcno1/9408188
	 */
	private void setWindowStyle () {
		Window window = getWindow();
		window.setBackgroundDrawableResource(android.R.color.transparent);
		WindowManager.LayoutParams params = window.getAttributes();
		// Use dimAmount to control the amount of dim
		params.dimAmount = 0.6f;
		window.setAttributes(params);
	}
}
