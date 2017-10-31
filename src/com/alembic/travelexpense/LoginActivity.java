package com.alembic.travelexpense;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	EditText et1,et2;
	Button b1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		et1= (EditText)findViewById(R.id.editText1);
		et2= (EditText)findViewById(R.id.editText2);
		b1= (Button) findViewById(R.id.button1);
		
		b1.setOnClickListener(this);
		
		
		
		
	}

	
	String getResponseFromWeb(String data){
		String ans="";
		
		try {
			URL u = new URL(data);
			URLConnection uc = u.openConnection();
			
			uc.connect();
		    InputStream is = uc.getInputStream();
			int i = 0;
			
			StringBuffer sb = new StringBuffer();
			
			while(true){
				i = is.read();
				if(i==-1){
					break;
				}
				
				sb.append((char)i);
			}
			
			return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ans;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String username = et1.getText().toString();
		String password = et2.getText().toString();
		
		SessionManager.SessionManager_Start(username);
		
		String url = "http://10.0.2.2:1737/TravelExpense1/AspSupport.aspx?type=login&un="+username+"&pw="+password;
		
		WebTask wt = this.new WebTask();
		wt.execute(url);
		
	}

	
	class WebTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String resp = getResponseFromWeb(params[0]);
			return resp;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(result);
			if(id>0){
				Intent i = new Intent(LoginActivity.this, HomeActivity.class);
				startActivity(i);
			
			Toast.makeText(LoginActivity.this, "Welcome", 3000).show();
				
			}
			else{
				
				Toast.makeText(LoginActivity.this, "Invalid username or password", 3000).show();
			}
		}
		
		
		
	}
}



