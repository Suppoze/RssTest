package hu.sokizoltan.rsstest;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ButterKnife.bind(this);

        initializeViewPager();
        initializeTabLayout();
    }

    private void initializeTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initializeViewPager() {
        viewPager.setAdapter(new MainActivityViewPagerAdapter(getSupportFragmentManager()));
    }
}
