package com.openclassrooms.myamazingviewpager.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

import com.openclassrooms.myamazingviewpager.Controllers.Fragments.PageFragment;

/**
 * Created by Philippe on 17/11/2017.
 */


public class PageAdapter extends FragmentPagerAdapter {

    //Array of colors that will be passed to PageFragment
    private int[] colors;

    //Default Constructor
    public PageAdapter(FragmentManager mgr, int[] colors) {
        super(mgr);
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return(5); //Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        //Item to return
        return(PageFragment.newInstance(position, this.colors[position]));
    }
}
