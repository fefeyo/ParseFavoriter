package com.fefeyo.parsefavoriter;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by USER on 2015/11/05.
 */
public class ParseFavoriterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Favorite.class);
        Parse.initialize(
                this,
                "OQVj6BXRLn4IRKiwSwAk97PFPSeCChIewBoHvpfr",
                "97rLSe0HxaYZrIyGuNX0VMBexPlSK4zmGqJUwZlg"
        );
        ParseTwitterUtils.initialize(
                "96mS1CzIBJfu4E6wpPOOCkYsz",
                "p0yidSM7BvSqXOpVfYzm4i5fPJMLowAj3bjJMCSLQM4eAiV6tx"
        );
        ParseFacebookUtils.initialize(this);
    }
}
