package com.medikart.org;



import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainScreen extends FragmentActivity {

	public  FragmentManager fm;
	public static SlidingMenu slidingMenu ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment);
		
		slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
       // slidingMenu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
      //  slidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setSelectorDrawable(R.drawable.slidingmenu_list_selector_background);
     //   slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        Fragment sm = new SlidingMenuFragment();
        t.add(R.id.slidingmenu, sm).commit();
        
        slidingMenu.setMenu(R.layout.slidingmenu);
		  Fragment home  = new Home();
		  fm = getSupportFragmentManager();
          fm.beginTransaction()
			.replace(R.id.fragment_container, home,"home").commit();
          
	}
	@Override
	public void onBackPressed() {
	   if(slidingMenu.isMenuShowing())
		   slidingMenu.toggle();
	   else{
		   finish();
		   overridePendingTransition(R.anim.r2l_rev, R.anim.l2r_rev);
	   }
		   
	}
}
