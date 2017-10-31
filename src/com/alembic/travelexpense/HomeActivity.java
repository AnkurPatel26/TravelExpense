package com.alembic.travelexpense;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity implements OnItemClickListener {

	ListView lv;
	
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
	String[] from = {"head", "sub"};
	int[] to = {android.R.id.text1, android.R.id.text2};
	SimpleAdapter sa;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		lv = (ListView) findViewById(R.id.listView1);
		
		lv.setOnItemClickListener(this);
		
		String s=SessionManager.getuid();
		Toast.makeText(getApplicationContext(), s , Toast.LENGTH_LONG).show();
		
		
		
		WebTask wt = new WebTask();
		wt.execute("http://10.0.2.2:1737/TravelExpense1/AspSupport.aspx?type=travellist&uid="+s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	
        
    	switch (item.getItemId()) {
        
    	case R.id.edit:
        	
            
    		AddExpense();
            
            return true;
        
        default:
        	
            return super.onOptionsItemSelected(item);
        }
     }
    
    private void AddExpense() {
        Intent i = new Intent(HomeActivity.this, AddExpenseActivity.class);
        startActivity(i);
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
		//	Toast.makeText(SecondActivity.this, result, 3000).show();
		
			String[] arr = result.split("#");
			for(String s : arr){
				if(s.length()<5){
					continue;
				}
				String[] arr2 = s.split("-");
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("head",arr2[1]);
				map.put("sub", arr2[2]);
				map.put("extra", arr2[0]);
				
				data.add(map);
			}
		
			sa = new SimpleAdapter(HomeActivity.this, data, android.R.layout.simple_list_item_2, from, to);
			lv.setAdapter(sa);
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub
		String tid = data.get(pos).get("extra");
		SessionManager.setTravelId(this, Integer.parseInt( tid));
		AddExpense();
	}
	

}
