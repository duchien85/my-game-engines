package com.gsn.engine;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gsn.poker.R;

public class LoginDlg extends Dialog implements IChatInput{

	public String user, pass, session;
	Activity context;

	public LoginDlg(final Activity context, int layout) {
		super(context);
		this.context = context;
		this.setContentView(layout);
		this.setTitle("Login");				
	}

	@Override
	public void chatInput(final IChatListener listener) {		
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {														
				final EditText chatText = (EditText) LoginDlg.this.findViewById(R.id.editTextChat);
				chatText.setText("");
				show();							
				final Button loginBtn = (Button) LoginDlg.this.findViewById(R.id.okBtn);
				loginBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						listener.onFinish(chatText.getText().toString());
						hide();
					}
				});
			}
		});
	}
	
}
