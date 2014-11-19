package com.medikart.org;

import java.io.File;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;



public class Home extends Fragment implements OnClickListener, OnTouchListener {
	
	
	ImageView islide,prspic,temp_image;;
	public static SlidingMenu slidingMenu ;
	RelativeLayout bar1,searchMed,bar2,updprs,bar3,reminder,bar4,another;
	Integer colorFrom ;
	Integer colorTo ;
	ValueAnimator colorAnimation ;
	View localView;
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v= inflater.inflate(R.layout.activity_main, container, false);
            
            
                islide = (ImageView) v.findViewById(R.id.iv_slidebutton);
	            islide.setOnClickListener(this);
	            bar1=(RelativeLayout)v.findViewById(R.id.home_bar1_rl);
	            searchMed=(RelativeLayout)v.findViewById(R.id.home_searchmed_rl);
	            bar2=(RelativeLayout)v.findViewById(R.id.home_bar2_rl);
	            updprs=(RelativeLayout)v.findViewById(R.id.home_updprs_rl);
	            bar3=(RelativeLayout)v.findViewById(R.id.home_bar3_rl);
	            reminder=(RelativeLayout)v.findViewById(R.id.home_reminder_rl);
	            bar4=(RelativeLayout)v.findViewById(R.id.home_bar4_rl);
	            another=(RelativeLayout)v.findViewById(R.id.home_another_rl);
	            
	            
	            ((MainScreen)getActivity()).slidingMenu.setSlidingEnabled(false);
	    		slidingMenu = ((MainScreen)getActivity()).slidingMenu;
	    		searchMed.setOnTouchListener(this);
	    		reminder.setOnTouchListener(this);
	    		updprs.setOnTouchListener(this);
	    		another.setOnTouchListener(this);
	    		
	    		colorFrom = Color.rgb(1, 223, 116);
	    		colorTo  = Color.rgb(223, 1, 1);
	    		
	    		
				return v;
	

	 }

	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_slidebutton:
			slidingMenu.toggle();
		}
	}

	@SuppressLint("NewApi")
	@Override  
	public boolean onTouch(final View v, MotionEvent event) {
		
		switch(v.getId()){
		case R.id.home_searchmed_rl:
			localView = bar1;
			
			break;
		case R.id.home_updprs_rl:
			localView = bar2;
			break;
		case R.id.home_reminder_rl:
			localView = bar3;
			break;
		case R.id.home_another_rl:
			localView = bar4;
			break;
		}
		switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
        	colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        	colorAnimation.addUpdateListener(new AnimatorUpdateListener() {
        	    @Override
        	    public void onAnimationUpdate(ValueAnimator animator) {
        	        localView.setBackgroundColor((Integer)animator.getAnimatedValue());
        	    }

        	});
        	colorAnimation.start();
            return true;
        case MotionEvent.ACTION_UP:
        	colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorTo, colorFrom);
        	colorAnimation.addUpdateListener(new AnimatorUpdateListener() {
        	    @Override
        	    public void onAnimationUpdate(ValueAnimator animator) {
        	    	localView.setBackgroundColor((Integer)animator.getAnimatedValue());
        	    }

        	});
        	colorAnimation.start();
		switch(v.getId()){
			case R.id.home_searchmed_rl:
				Intent intent = new Intent(getActivity(), Details.class);
		        startActivity(intent);
		        getActivity().overridePendingTransition(R.anim.l2r, R.anim.r2l);
			break;
			case R.id.home_updprs_rl:
				Intent i = new Intent(getActivity(), Prescription.class);
		        startActivity(i);
		        getActivity().overridePendingTransition(R.anim.l2r, R.anim.r2l);
				
				break;
		}
		return true;
		 }
		 
		 return true;
	}

	   
	
}
