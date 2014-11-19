package com.medikart.org;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Intent login = new Intent(this,Login.class);
        startActivity(login);
        
        finish();
    }



}
