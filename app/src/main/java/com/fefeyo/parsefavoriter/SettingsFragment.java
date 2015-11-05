package com.fefeyo.parsefavoriter;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    @InjectView(R.id.twitter)
    Button twitter;
    @InjectView(R.id.facebook)
     Button facebook;
    @InjectView(R.id.google)
     Button google;
    @InjectView(R.id.logout)
     Button logout;
    private View v;

    public SettingsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.inject(this, v);
        sync();
        return v;
    }

    @OnClick({
            R.id.twitter,
            R.id.facebook,
            R.id.google}
    )
    void onClickButton(Button button){
        switch (button.getId()){
            case R.id.twitter:
                loginWithTwitter();
                break;
            case R.id.facebook:
                loginWithFacebook();
                break;
            case R.id.google:
                Toast.makeText(getActivity().getApplicationContext(), "だめです", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void sync() {
        Log.i("sync", "sync");
        if (null != ParseUser.getCurrentUser()) {
            if (ParseTwitterUtils.isLinked(ParseUser.getCurrentUser())) disable();
            if (ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())) disable();
        }
    }

    private void disable(){
        Log.i("disable", "disable");
        twitter.setEnabled(false);
        facebook.setEnabled(false);
        google.setEnabled(false);
        logout.setVisibility(View.VISIBLE);
    }

    private void loginWithTwitter(){
        ParseTwitterUtils.logIn(
                getActivity(),
                new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        String result = "";
                        if(null == parseUser){
                            result = e.toString();
                        }else{
                            result = "成功";
                        }
                        Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void loginWithFacebook(){
        ParseFacebookUtils.logInWithPublishPermissionsInBackground(
                getActivity(),
                null,
                new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        Log.d("done", "done");
                        String result = "";
                        if (parseUser == null) {
                            result = e.toString();
                        } else {
                            result = "成功";
                            sync();
                        }
                        Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}
