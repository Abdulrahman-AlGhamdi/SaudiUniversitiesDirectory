package com.ss.universitiesdirectory.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.ss.universitiesdirectory.R;

public class DetailsFragment extends Fragment {

    private View view;
    private Bundle bundle;
    private ImageView mLogo;
    private TextView mAbout;
    private Fragment fragment;
    private LinearLayout mCommunication;
    private ImageView Twitter, Facebook;
    private Button mCollage, mWebsite, mRSS, mLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_details, container, false);
        
        init();
        ShowData();

        return view;
    }

    private void init() {
        bundle = this.getArguments();
        mRSS = view.findViewById(R.id.RSS);
        mLogo = view.findViewById(R.id.Logo);
        Twitter = view.findViewById(R.id.Twitter);
        mAbout = view.findViewById(R.id.AboutText);
        mCollage = view.findViewById(R.id.College);
        mWebsite = view.findViewById(R.id.Website);
        Facebook = view.findViewById(R.id.Facebook);
        mLocation = view.findViewById(R.id.Location);
        mCommunication = view.findViewById(R.id.Communication);
    }

    private void ShowData() {
        if(bundle.getString("Logo") != null){
            Picasso.get().load(bundle.getString("Logo")).into(mLogo);
        }

        if(bundle.getString("About") != null){
            mAbout.setText(bundle.getString("About"));
        }

        if(bundle.getString("Website") != null){
            mWebsite.setVisibility(View.VISIBLE);
            mWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = new WebsiteFragment();
                    bundle.putString("WebsiteShow", bundle.getString("Website"));
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                    transaction.replace(R.id.Container, fragment).addToBackStack(null).commit();
                }
            });
        }

        if(bundle.getString("College") != null){
            mCollage.setVisibility(View.VISIBLE);
            mCollage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = new WebsiteFragment();
                    bundle.putString("CollegeShow", bundle.getString("College"));
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                    transaction.replace(R.id.Container, fragment).addToBackStack(null).commit();
                }
            });
        }

        if(bundle.getString("News") != null){
            mRSS.setVisibility(View.VISIBLE);
            mRSS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = new NewsFragment();
                    bundle.putString("News", bundle.getString("News"));
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                    transaction.replace(R.id.Container, fragment).addToBackStack(null).commit();
                }
            });
        }

        if(bundle.getString("Location") != null){
            mLocation.setVisibility(View.VISIBLE);
            mLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("Location"))));
                }
            });
        }

        if(bundle.getString("Twitter") != null){
            mCommunication.setVisibility(View.VISIBLE);
            Twitter.setVisibility(View.VISIBLE);
            Twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(bundle.getString("Twitter"))));
                }
            });
        }

        if(bundle.getString("Facebook") != null){
            mCommunication.setVisibility(View.VISIBLE);
            Facebook.setVisibility(View.VISIBLE);
            Facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(bundle.getString("Facebook"))));
                }
            });

        }
    }
}
