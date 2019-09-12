package ng.com.tjah.bpchecker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ng.com.tjah.bpchecker.Hypertension.FragmentFive;
import ng.com.tjah.bpchecker.Hypertension.FragmentFour;
import ng.com.tjah.bpchecker.Hypertension.FragmentOne;
import ng.com.tjah.bpchecker.Hypertension.FragmentThree;
import ng.com.tjah.bpchecker.Hypertension.FragmentTwo;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragmentOne();
        } else if (position == 1) {
            fragment = new FragmentTwo();
        } else if(position == 2){
            fragment = new FragmentThree();
        }else if(position == 3){
            fragment = new FragmentFour();
        }else if(position == 4){
            fragment = new FragmentFive();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Tab-1";
        } else if (position == 1) {
            title = "Tab-2";
        }
        return title;
    }
}