package com.vexonelite.dialogdemo.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.view.Window;
import android.view.WindowManager;



public class BaseDialogFragment extends DialogFragment {

    private boolean mPendingDialog;
    private SpannableString mPendingTitle;
    private SpannableString mPendingMessage;
    private OnDecisionMadeListener mPendingCallback;


    public enum DialogAction {
        CONFIRM,
        CANCEL
    }
    public interface OnDecisionMadeListener {
        void onNotification (DialogFragment dialogFrag, DialogAction dialogAction);
    }

    protected String getLogTag () {
        return this.getClass().getSimpleName();
    }


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NO_TITLE, theme = 0;
        //int style = DialogFragment.STYLE_NO_FRAME, theme = 0;
        //int style = DialogFragment.STYLE_NO_FRAME, theme = android.R.style.Theme_Dialog;

        /*
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        
        */
        setStyle(style, theme);
    }
	
	@Override
	public void onStart() {
		super.onStart();
		setWindowStyle();
	}

    @Override
    public void onResume() {
        super.onResume();

        if (mPendingDialog) {
            mPendingDialog = false;
            boolean titleFlag = false;
            if ( (null != mPendingTitle) && (mPendingTitle.length() > 0) ) {
                titleFlag = true;
            }
            showPendingDialog(mPendingMessage, titleFlag, mPendingTitle, mPendingCallback);
        }
    }

    protected void showPendingDialog (SpannableString message,
                                      boolean titleFlag,
                                      SpannableString title,
                                      OnDecisionMadeListener listener) {

    }


	
	/** 
	 * Set desired looks of the dialog, including background image/ color,
	 * window size, margin, etc.
	 * 
	 * Refs:
	 * https://gist.github.com/ishitcno1/9408188
	 */
	protected void setWindowStyle () {
		
		Window window = getDialog().getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		
		// Use dimAmount to control the amount of dim
		params.dimAmount = 0.6f;
		window.setAttributes(params);
		window.setBackgroundDrawableResource(android.R.color.transparent);
	}
	
	/*
	private void setDialogPosition() {
		Window window = getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM | Gravity.LEFT);
		WindowManager.LayoutParams params = window.getAttributes();
		params.y = CodeUtils.dipToPixel (mContext, 60);
		window.setAttributes(params);
	}
	*/
	
	/*
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

		LayoutInflater inflater = LayoutInflater.from(mContext);
		View containerView = inflater.inflate(R.layout.edit_image_dialog, null);
		
		mCameraButton = (ImageView) containerView.findViewById(R.id.by_camera_button);
		mCameraButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCallback != null) {
					mCallback.onNotification (EditImageDialogFragment.this, 
							SignUpFragment.BY_CAMERA);
				}
			}
		});
		
		mGalleryButton = (ImageView) containerView.findViewById(R.id.by_gallery_button);
		mGalleryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCallback != null) {
					mCallback.onNotification (EditImageDialogFragment.this, 
							SignUpFragment.BY_GALLERY);
				}
			}
		});
		
		builder.setView(containerView);
		
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		/*
		dialog.getWindow().setLayout( (int)(CodeUtils.getScreenDensity(mContext) * 220f), 
				(int)(CodeUtils.getScreenDensity(mContext) * 115f));
		//dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		*/
	
	/*
		return dialog;
	}
	*/

    protected void enablePendingDialog (final SpannableString pendingTitle,
                                        final SpannableString pendingMessage,
                                        final OnDecisionMadeListener pendingCallback) {
        mPendingDialog = true;
        mPendingTitle = pendingTitle;
        mPendingMessage = pendingMessage;
        mPendingCallback = pendingCallback;
    }
}


