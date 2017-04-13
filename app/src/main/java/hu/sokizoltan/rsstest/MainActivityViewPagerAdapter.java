package hu.sokizoltan.rsstest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class MainActivityViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final int API_TEST_LIST = 0;
    private static final int JSON_VIEWER = 1;

    public MainActivityViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2; // TODO remove hardcoded count
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case API_TEST_LIST:
                return new ApiTestListView();
            case JSON_VIEWER:
                return new JsonViewerView();
            default:
                throw new IndexOutOfBoundsException(String.format("Fragment position %d is out of bounds", position));
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // TODO: get titles from resources
        switch (position) {
            case API_TEST_LIST:
                return "APITEST";
            case JSON_VIEWER:
                return "JSON";
            default:
                throw new IndexOutOfBoundsException(String.format("Fragment position %d is out of bounds", position));
        }
    }
}
