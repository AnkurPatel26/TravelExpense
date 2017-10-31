package com.alembic.travelexpense;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.alembic.travelexpense.ThoFragment.WebTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



 
public class ThoFragment extends Fragment implements OnClickListener {
	
	Button b1;
	EditText et1,et2,et3;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_tho, container, false);
        
        et1= (EditText)rootView.findViewById(R.id.etHotelname);
		et2= (EditText)rootView.findViewById(R.id.etDays);
		et3= (EditText)rootView.findViewById(R.id.etAmount);
		
        b1= (Button) rootView.findViewById(R.id.button1);
        
        b1.setOnClickListener(this);
		
         
        return rootView;
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String hotelname = et1.getText().toString();
		String days = et2.getText().toString();
		String amount = et3.getText().toString();
		
		
		
		String url = "http://10.0.2.2:1737/TravelExpense1/AspSupport.aspx?type=THO&p1="+hotelname+"&p2="+days+"&p3="+amount;
		
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
			
		}
	}
}