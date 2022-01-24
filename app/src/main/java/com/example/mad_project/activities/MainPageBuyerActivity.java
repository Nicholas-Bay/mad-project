package com.example.mad_project.activities;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.mad_project.R;
import com.example.mad_project.fragments.BuyerCalendarFragment;
import com.example.mad_project.fragments.BuyerHomeFragment;
import com.example.mad_project.fragments.BuyerMeFragment;
import com.example.mad_project.fragments.BuyerReputationFragment;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainPageBuyerActivity extends AppCompatActivity {
    private static final String TAG = MainPageBuyerActivity.class.getSimpleName();
    private AnimatedBottomBar animatedBottomBar;
    private FragmentManager fragmentManager;
    //bundle to pass activty to fragment
    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_mainpage);
        //getting data from loginActivity and storing into bundle
        //so that we can pass bundle to the framgnets
        Intent intent=getIntent();
        bundle.putString("username",intent.getStringExtra("username"));
        bundle.putString("password",intent.getStringExtra("password"));
        bundle.putString("name",intent.getStringExtra("name"));
        bundle.putString("email",intent.getStringExtra("email"));
        bundle.putString("phoneNo",intent.getStringExtra("phoneNo"));

        getSupportActionBar().hide();
        animatedBottomBar = findViewById(R.id.animatedBuyerBottomBar);
        if (savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.home_buyer, true);
            fragmentManager = getSupportFragmentManager();
            BuyerHomeFragment homeBuyerFragment = new BuyerHomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_buyer_container, homeBuyerFragment).commit();
        }
        animatedBottomBar.setOnTabSelectListener((lastIndex, lastTab, newIndex, newTab) -> {
            Fragment fragment = null;
            switch (newTab.getId()) {
                case R.id.home_buyer:
                    fragment = new BuyerHomeFragment();
                    break;
                case R.id.reputation_buyer:
                    fragment = new BuyerReputationFragment();
                    break;
                case R.id.calendar_buyer:
                    fragment = new BuyerCalendarFragment();
                    break;
                case R.id.me_buyer:
                    fragment = new BuyerMeFragment();
                    fragment.setArguments(bundle);
                    break;
            }
            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_buyer_container, fragment).commit();
            }
            else Log.e(TAG, "Error in creating Fragment");
        });
    }
}
