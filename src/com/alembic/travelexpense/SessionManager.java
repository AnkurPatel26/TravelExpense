package com.alembic.travelexpense;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SessionManager {
	
	private static String uid ;
    
    
    
    // Constructor
    public SessionManager()
    {
    	uid=new String();
     }
    
     
   static void  SessionManager_Start(String s)
   {
	   uid=s;
   }
   
   static void SessionManager_End()
   {
	   uid=null;
   }
   static String getuid()
   {
	   return uid;
   }
   static void setTravelId(Context c, int tid){
	   PreferenceManager.getDefaultSharedPreferences(c).edit().putInt("tid", tid).commit();
   }
   static int getTravelId(Context c){
	   return PreferenceManager.getDefaultSharedPreferences(c).getInt("tid",0);
   }
   
   
  
   
   
}

