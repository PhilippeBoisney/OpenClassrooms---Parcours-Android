package com.openclassrooms.myamazingviewpager.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

import com.openclassrooms.myamazingviewpager.Controllers.Fragments.NewsPageFragment;
import com.openclassrooms.myamazingviewpager.Controllers.Fragments.ParamPageFragment;
import com.openclassrooms.myamazingviewpager.Controllers.Fragments.ProfilePageFragment;

/**
 * Created by Philippe on 17/11/2017.
 */


public class PageAdapter extends FragmentPagerAdapter {


    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NewsPageFragment.newInstance();
            case 1:
                return ProfilePageFragment.newInstance();
            case 2:
                return ParamPageFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Fil d'actualité";
            case 1:
                return "Profil";
            case 2:
                return "Paramètre";
            default:
                return null;
        }
    }
}
