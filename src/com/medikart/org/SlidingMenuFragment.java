package com.medikart.org;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class SlidingMenuFragment extends Fragment implements ExpandableListView.OnChildClickListener {
   
    private ExpandableListView sectionListView;
    ViewPager pager;
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
       
        //List<Section> sectionList = createMenu();
               
        View view = inflater.inflate(R.layout.slidingmenu_sectionitem, container, false);
       // this.sectionListView = (ExpandableListView) view.findViewById(R.id.slidingmenu_view);
      //  this.sectionListView.setGroupIndicator(null);
       
        /*SectionListAdapter sectionListAdapter = new SectionListAdapter(this.getActivity(), sectionList);
     //   this.sectionListView.setAdapter(sectionListAdapter);
       
       this.sectionListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
              @Override
              public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
              }
            });
       
        this.sectionListView.setOnChildClickListener(this);
       
        int count = sectionListAdapter.getGroupCount();
        for (int position = 0; position < count; position++) {
            this.sectionListView.expandGroup(position);
        }
        
       // pager = Cluster.pager;
       */
        return view;
    }

    private List<Section> createMenu() {
        List<Section> sectionList = new ArrayList<Section>();

        Section oDemoSection = new Section("mainlogo");
        oDemoSection.addSectionItem(1,"Home", "homemenu");
        oDemoSection.addSectionItem(2, "About Us", "aboutmenu");
       
     //   Section oGeneralSection = new Section("General");
        oDemoSection.addSectionItem(3, "Services", "servicesmenu");
        oDemoSection.addSectionItem(4, "Let's Talk", "talkmenu");
        oDemoSection.addSectionItem(5, "Get a quote", "quotemenu");
       
        sectionList.add(oDemoSection);
       // sectionList.add(oGeneralSection);
        return sectionList;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
            int groupPosition, int childPosition, long id) {
    	
    	//Main.slidingMenu.toggle();
        switch ((int)id) {
        case 1:
            pager.setCurrentItem(0);
            
            break;
       
        }
        return false;
    }
    
}
