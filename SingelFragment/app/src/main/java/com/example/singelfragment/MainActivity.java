package com.example.singelfragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.singelfragment.data.Example;
import com.example.singelfragment.network.RetrofitClient;
import com.example.singelfragment.network.RetrofitInterFace;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TabAdapter mTabAdapter;

    public ArrayList<Example> mPreviousList;
    public ArrayList<Example> mCurrentList;
    public ArrayList<Example> mUpComingList;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        ViewPager mViewPager = findViewById(R.id.viewPager);
        TabLayout mTabLayout = findViewById(R.id.tabLayout);

        mTabAdapter = new TabAdapter(getSupportFragmentManager());

        mTabAdapter.addFragment(new BaseFragment(), "Previous");
        mTabAdapter.addFragment(new BaseFragment(), "Current");
        mTabAdapter.addFragment(new BaseFragment(), "Up Coming");

        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mPreviousList = new ArrayList<>();
        mCurrentList = new ArrayList<>();
        mUpComingList = new ArrayList<>();

//        Call Event Data Previous Current UpComing
        getPrevious();
        getCurrent();
        getUpComing();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        ((BaseFragment) mTabAdapter.getItem(position)).loadData(mPreviousList);
                        break;
                    case 1:
                        ((BaseFragment) mTabAdapter.getItem(position)).loadData(mCurrentList);
                        break;
                    case 2:
                        ((BaseFragment) mTabAdapter.getItem(position)).loadData(mUpComingList);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mViewPager.setCurrentItem(1);
    }

    public void getPrevious() {
        RetrofitInterFace interFace = RetrofitClient.getApi();
        Call<ArrayList<Example>> listCall = interFace.getPreviousData();
        listCall.enqueue(new Callback<ArrayList<Example>>() {
            @Override
            public void onResponse(Call<ArrayList<Example>> call, Response<ArrayList<Example>> response) {
                mPreviousList = response.body();
                ((BaseFragment) mTabAdapter.getItem(0)).loadData(mPreviousList);
            }

            @Override
            public void onFailure(Call<ArrayList<Example>> call, Throwable t) {
                Log.e("Previous", t.getLocalizedMessage());
            }
        });
    }

    public void getCurrent() {
        RetrofitInterFace interFace = RetrofitClient.getApi();
        Call<ArrayList<Example>> listCall = interFace.getCurrentData();
        listCall.enqueue(new Callback<ArrayList<Example>>() {
            @Override
            public void onResponse(Call<ArrayList<Example>> call, Response<ArrayList<Example>> response) {
                mCurrentList = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Example>> call, Throwable t) {
                Log.e("Current", t.getLocalizedMessage());
            }
        });
    }

    public void getUpComing() {
        RetrofitInterFace interFace = RetrofitClient.getApi();
        Call<ArrayList<Example>> listCall = interFace.getUpComingData();
        listCall.enqueue(new Callback<ArrayList<Example>>() {
            @Override
            public void onResponse(Call<ArrayList<Example>> call, Response<ArrayList<Example>> response) {
                mUpComingList = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Example>> call, Throwable t) {
                Log.e("UpComing", t.getLocalizedMessage());
            }
        });
    }
}