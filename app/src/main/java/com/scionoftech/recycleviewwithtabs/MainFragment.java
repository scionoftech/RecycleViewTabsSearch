package com.scionoftech.recycleviewwithtabs;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    EditText edit_text_search;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        // tabLayout.setupWithViewPager(viewPager);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        //search item in list
        edit_text_search = (EditText) view.findViewById(R.id.edit_text_search);
        edit_text_search.setCursorVisible(false);
        edit_text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (viewPager.getCurrentItem() == 0) {
                    //pass search query to adapter
                    Android fragmentB = (Android) getChildFragmentManager()
                            .findFragmentByTag(getTabAndroid());

                    fragmentB.Search(String.valueOf(s));
                } else {

                    Ios fragmentB = (Ios) getChildFragmentManager()
                            .findFragmentByTag(getTabIos());

                    fragmentB.Search(String.valueOf(s));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        CharSequence Titles[] = {"ANDROID", "IOS"};

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    Android tab1 = new Android();
                    f = tab1;
                    break;
                case 1:
                    Ios tab2 = new Ios();
                    f = tab2;
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Titles[position];
        }
    }

    //get tags of fragments of view pager
    String Tab_android, Tab_ios;

    public void setTabAndroid(String t) {
        Tab_android = t;
    }

    public void setTabIos(String t) {
        Tab_ios = t;
    }

    public String getTabAndroid() {
        return Tab_android;
    }

    public String getTabIos() {
        return Tab_ios;
    }
}
