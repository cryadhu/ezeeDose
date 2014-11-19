package com.medikart.org;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;



public class Details extends FragmentActivity implements OnClickListener{

	ImageView bback;
	String[] titles = { "one", "two", "three" };
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.details);    
	        
	        
	        bback=(ImageView)findViewById(R.id.iv_backbutton);
	        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
	        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	        
	        bback.setOnClickListener(this);
	       
	        PagerTabStrip strip = PagerTabStrip.class.cast(findViewById(R.id.pager_tab_strip));
	        strip.setDrawFullUnderline(false);
	        strip.setTabIndicatorColor(Color.WHITE);
	        strip.setNonPrimaryAlpha(0.5f);
	        strip.setTextSpacing(25);
	        strip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
	    }

	    private class MyPagerAdapter extends FragmentPagerAdapter {

	        public MyPagerAdapter(FragmentManager fm) {
	            super(fm);
	        }				

	        @Override
	        public Fragment getItem(int pos) {
	            switch(pos) {
		            case 0:
		            	  return FirstFragment.newInstance("FirstFragment");
		            case 1: 
		            	  return SecondFragment.newInstance("SecondFragment");
		            case 2: 
		            	  return ThirdFragment.newInstance("ThirdFragment");
	                default: 
	            	      return ThirdFragment.newInstance("ThirdFragment");
	            }
	        }

	        @Override
	        public int getCount() {
	            return 3;
	        }     
	        @Override
	        public CharSequence getPageTitle(int position) {
	            return titles[position];
	        }
	 }
	    
	    @Override
		public void onBackPressed() {
			   finish();
			   overridePendingTransition(R.anim.r2l_rev, R.anim.l2r_rev);
		}

		@Override
		public void onClick(View v) {
			
			//FragmentTransaction ft=getFragmentManager().beginTransaction();
	        //Fragment home  = new Home();
	 	    //ft.replace(R.id.fragment_container, home);
	        //ft.commit();
			
			
		}

		
		
}
