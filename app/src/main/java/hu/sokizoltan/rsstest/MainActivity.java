package hu.sokizoltan.rsstest;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.main_activity_tablayout)
    TabLayout tabLayout;

    @BindView(R.id.activity_main_viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActionBar();
        initializeTabLayout();
        initializeViewPager();
    }

    private void initializeActionBar() {

    }

    private void initializeTabLayout() {

    }

    private void initializeViewPager() {

    }
}
