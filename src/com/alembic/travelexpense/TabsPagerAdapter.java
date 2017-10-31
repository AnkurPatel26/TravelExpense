package com.alembic.travelexpense;

import com.alembic.travelexpense.ThoFragment;
import com.alembic.travelexpense.TtkFragment;
import com.alembic.travelexpense.TcvFragment;
import com.alembic.travelexpense.TfdFragment;
import com.alembic.travelexpense.TprFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            
            return new ThoFragment();
        case 1:
            
            return new TtkFragment();
        case 2:
            
            return new TcvFragment();
        
        case 3:
            
            return new TfdFragment();
        case 4:
            
            return new TprFragment();
            
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }
 
}