package com.example.news;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.news.Fragments.BusinessFragment;
import com.example.news.Fragments.EntertainmentFragment;
import com.example.news.Fragments.HeadLinesFragment;
import com.example.news.Fragments.HealthFragment;
import com.example.news.Fragments.ScienceFragment;
import com.example.news.Fragments.SportsFragment;
import com.example.news.Fragments.TechnologyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager viewPager;
private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();


        adapter=new MainAdapter(getSupportFragmentManager());
        adapter.AddFragment(new HeadLinesFragment(),"HeadLines");
        adapter.AddFragment(new EntertainmentFragment(),"Entertainment");
        adapter.AddFragment(new BusinessFragment(),"Business");
        adapter.AddFragment(new HealthFragment(),"Health");
        adapter.AddFragment(new ScienceFragment(),"Science");
        adapter.AddFragment(new SportsFragment(),"Sports");
        adapter.AddFragment(new TechnologyFragment(),"Technology");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initComponents() {
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
    }

    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList =new ArrayList<>();
        ArrayList<String> stringArrayList=new ArrayList<>();

        public void AddFragment(Fragment fragment,String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }
}