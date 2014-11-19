package com.medikart.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Object;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.UserDictionary.Words;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Register extends Activity implements  OnTouchListener {

	EditText username,password,name,confirmpassword;
	Button register;
	ProgressDialog pDialog;
	public static String urlBase = "http://192.168.1.145/arbi/";
	String url = urlBase+"register.php";
	String userRealName;
	List<String> list;
	Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		
		name = (EditText)findViewById(R.id.register_et_name);
		username = (EditText)findViewById(R.id.register_et_username);
		password = (EditText)findViewById(R.id.register_et_password);
		confirmpassword = (EditText)findViewById(R.id.register_et_confirmpass);
		register = (Button)findViewById(R.id.register_bt_register);
		spinner = (Spinner) findViewById(R.id.spinner);
		register.setOnTouchListener(this);
		
		list = new ArrayList<String>();
        Bundle typeUser = getIntent().getExtras();
        for(int i=0;i<typeUser.getInt("length");i++){
        	list.add(typeUser.getString(typeUser.getString("id"+i)));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list);
		
        dataAdapter.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);
         
        spinner.setAdapter(dataAdapter);


		
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
			case R.id.register_bt_register:
			  if(validateEmail(username.getText().toString())){
				  Toast.makeText(this, "Provide valid email", Toast.LENGTH_SHORT).show();
			  }
			  else if(password.length()<8 ){ 
					  //|| password.length()<4){
				  Toast.makeText(this, "Password length must be greater than 8", Toast.LENGTH_SHORT).show();
			  }else if(!password.getText().toString().equals(confirmpassword.getText().toString())){
				  Toast.makeText(this, "Please check confirm password", Toast.LENGTH_SHORT).show();
			  }
			  else{
				  doVolley(name.getText().toString(),username.getText().toString(),password.getText().toString(),
						  spinner.getSelectedItem().toString());
			  }
				
			break;
		}
		return true;
		 }
		 
		 return true;
	}
	public synchronized void doVolley(final String name,final String username,final String password,final String type){
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
 
        
           
        StringRequest doLogin = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                               
                    	response = response.replace("[", "");
                    	response = response.replace("]", "");
                      try{
                      JSONObject mainObject = new JSONObject(response);
                      JSONObject uniObject = mainObject.getJSONObject("result");
                      userRealName = uniObject.getString("status");
                      }catch(Exception e){
                    	  
                      }
                      messageHandler.sendEmptyMessage(0);
                        
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
                    params.put("name",name);
                    params.put("email",username);
                    params.put("password",password);
                    params.put("type",""+(spinner.getSelectedItemPosition()+1));
                     
                    return params; 
            }
        	
        };
        
 
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(doLogin);
        
  }//volley
	
	 Handler messageHandler = new Handler() {
	        public void handleMessage(Message msg) {
	        	super.handleMessage(msg);
	        	pDialog.dismiss();
	        	switch (msg.what) {
				case 0:
			        Toast.makeText(getApplicationContext(), userRealName, Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
	        	
	        }
	    };
	    @Override
		public void onBackPressed() {
			   finish();
			   overridePendingTransition(R.anim.r2l_rev, R.anim.l2r_rev);
		}
public boolean validateEmail(String email){
	 		  String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		      Boolean b = email.matches(EMAIL_REGEX);
		      return b;
}
}
