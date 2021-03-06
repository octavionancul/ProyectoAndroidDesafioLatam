package cl.octavionancul.proyectoandroiddesafiolatam.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).


        switch(position){
            case 0:

                return SetsFragment.newInstance();
            case 1:
                return PreviusSetsFragment.newInstance();
            case 2:
                return SetHistoryFragment.newInstance();
            default:
                return SetsFragment.newInstance();

        }
      //  return SetsTabsActivity.PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
