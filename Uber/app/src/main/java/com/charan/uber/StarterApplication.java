package com.charan.uber;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StarterApplication extends Application {

    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("765eba59dd9d54d63f8badd23766e00df814d943")
                .clientKey("285566b1f4cab24346dad9e64cd1164a358159b4")
                .server("http://3.132.212.246:80/parse/")
                .build()
        );

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
