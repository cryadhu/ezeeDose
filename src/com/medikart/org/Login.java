package com.medikart.org;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Login extends Activity implements OnClickListener, OnTouchListener {

	EditText username,password;
	Button login,guest;
	TextView forgetPassword,register;
	ProgressDialog pDialog;
	public static String urlBase = "http://192.168.1.145/arbi/";
	String url = urlBase+"1.php";
	String urlUser = urlBase+"obtain-user-type";
	String userRealName;
	Bundle typeUser;
	@Override
		protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
	    username = (EditText)findViewById(R.id.login_et_username);
		password = (EditText)findViewById(R.id.login_et_password);
		login    = (Button)findViewById(R.id.login_bt_login);
		guest    = (Button)findViewById(R.id.login_bt_guest);
		forgetPassword = (TextView)findViewById(R.id.login_tv_forget);
		register = (TextView)findViewById(R.id.login_tv_register);
		
		typeUser = new Bundle();
		
		login.setOnTouchListener(this);
		guest.setOnTouchListener(this);
		forgetPassword.setOnClickListener(this);
		register.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.login_tv_forget:
			//ArcTranslate animation = new ArcTranslate(500,Animation.ABSOLUTE,v.getX()-v.getWidth()/5,Animation.ABSOLUTE,register.getX()-v.getWidth()/4,Animation.ABSOLUTE,50f);
			//v.startAnimation(animation);
			//v.setAnimation(animation);
			Intent forgot = new Intent(getBaseContext(),forgot.class);
			startActivity(forgot);
			break;
		case R.id.login_tv_register:
			 getTypeUser();
			break;

		default:
			break;
		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		 switch (event.getAction()) {
         case MotionEvent.ACTION_DOWN:
        	 
        	 v.setBackgroundColor(Color.rgb(45,130,200));
             return true;
         case MotionEvent.ACTION_UP:
        	 v.setBackgroundColor(Color.rgb(75,160,230));
		switch(v.getId()){
			case R.id.login_bt_guest:
				Intent mainScreen = new Intent(getBaseContext(),MainScreen.class);
				startActivity(mainScreen);
				overridePendingTransition(R.anim.l2r, R.anim.r2l);
				finish();
			break;
			case R.id.login_bt_login:
			  if(username.length()<4 || password.length()<4){
					  //
				  Toast.makeText(this, "Username & Password length must be greater than 4", Toast.LENGTH_SHORT).show();
			  }else{
				  doVolley(username.getText().toString(),password.getText().toString());
			  }
				
			break;
		}
		return true;
		 }
		 
		 return true;
	}
	public synchronized void doVolley(final String username,final String password){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
 
        
           
        StringRequest doLogin = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                               
                    
                      try{
                      JSONArray mainResponse = new JSONArray(response);
                      JSONObject mainObject = mainResponse.getJSONObject(0);
                      JSONObject uniObject = mainObject.getJSONObject("result");
                      userRealName = uniObject.getString("status");
                   /*   JSONObject uniObject1 =uniObject.getJSONObject("msg");
                      userRealName =""+ uniObject1.getString("1");*/
                      messageHandler.sendEmptyMessage(1);
                      }catch(Exception e){
                    	  messageHandler.sendEmptyMessage(99);
                      }
                      
                      
                      
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
 
                    }
                    
                }){
        	@Override
            protected Map<String, String> getParams()
            { 
                    Map<String, String>  params = new HashMap<String, String>(); 
                    params.put("username", username); 
                    params.put("password", password);
                     
                    return params; 
            }
        	
        };
        
        AppController.getInstance().addToRequestQueue(doLogin);
        
  }//volley
	public synchronized void getTypeUser(){
		
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
 
        
           
        StringRequest doLogin = new StringRequest(Request.Method.POST,urlUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        
                      try{
                    	  JSONArray mainResponse = new JSONArray(response);
                    	  JSONObject mainObject = mainResponse.getJSONObject(0);
                    	  JSONObject resultObject = mainObject.getJSONObject("result");
                    	  JSONArray msgArray = resultObject.getJSONArray("msg");
                      int i;
                      String localID;
                      for(i = 0;i<msgArray.length();i++){
                     	  //typeUser.putString("id"+i,""+ msgArray.getJSONObject(i).getString("id"));
                    	  localID=msgArray.getJSONObject(i).getString("id");
                    	  typeUser.putString("id"+i,localID);
                    	  typeUser.putString(localID,msgArray.getJSONObject(i).getString("user_type"));
                       }
                      typeUser.putInt("length", i);
                      messageHandler.sendEmptyMessage(3);
                      }catch(Exception e){
                    	  
                    	  messageHandler.sendEmptyMessage(99);
                      }
                      
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
 
                    }
                    
                }){
        	
        };
        
        AppController.getInstance().addToRequestQueue(doLogin);
        
  }//volley
	 Handler messageHandler = new Handler() {
	        public void handleMessage(Message msg) {
	        	super.handleMessage(msg);
	        	switch (msg.what) {
				case 0:
					if(!userRealName.equals("failure")){
						Intent mainScreen = new Intent(getBaseContext(),MainScreen.class);
						startActivity(mainScreen);
						overridePendingTransition(R.anim.l2r, R.anim.r2l);
						Toast.makeText(getApplicationContext(), userRealName, Toast.LENGTH_SHORT).show();
						finish();
					}else{
						Toast.makeText(getApplicationContext(), "Wrong Username/Password", Toast.LENGTH_SHORT).show();
					}
						
					break;
				case 1:
					pDialog.dismiss();
					sleepForSomeTime(500,0);
					
					break;
				case 2:
					 Intent register = new Intent(getApplicationContext(),Register.class);
					 register.putExtras(typeUser);
					 startActivity(register);
					 overridePendingTransition(R.anim.l2r, R.anim.r2l);
					break;
				case 3:
					pDialog.dismiss();
				//	Toast.makeText(getApplicationContext(), userRealName, Toast.LENGTH_SHORT).show();
					sleepForSomeTime(500,2);
					break;
				default:
					Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
					break;
				}
	        	
	        }
	    };

	public void sleepForSomeTime(final int time,final int typeMessage){
		Thread sleepThread = new Thread() {
		    @Override
		    public void run() {
		        try {
		        	sleep(time);
		        } catch (InterruptedException e) {
		    }finally{
		    	messageHandler.sendEmptyMessage(typeMessage);
		    }
		  };
    	};
    	sleepThread.start();
	}
}
