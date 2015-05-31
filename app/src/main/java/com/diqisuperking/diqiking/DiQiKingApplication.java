package com.diqisuperking.diqiking;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Derek on 5/31/2015.
 */
public class DiQiKingApplication extends Application {

    public void onCreate() {
        super.onCreate();
        
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "4AnAi457OKpx5nyj1cZlH1FdZD58uSlIBePOJDYk", "bf3TypKajGrJ3WgaC2rWsi4sio6hE7n9mZ1qL9VA");
    }
}
