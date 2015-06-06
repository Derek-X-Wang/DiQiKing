package com.diqisuperking.diqiking;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


import com.gc.materialdesign.views.ButtonRectangle;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private LocationTracker locationTracker;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();
        
        locationTracker = new LocationTracker(this);
        if(locationTracker.getLocationStatus()){
            
        }else{
            locationTracker.showSettingsAlert();
        }
        

        ButtonRectangle testButton = (ButtonRectangle)findViewById(R.id.tbutton);
        //final Button testButton = (Button)findViewById(R.id.tbutton);
        testButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("REQUEST:","Start");
                // TODO Auto-generated method stub
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("username", "diqiking");
                params.put("password", "dqk");
                ParseCloud.callFunctionInBackground("diqi_get_token", params, new FunctionCallback<Map<String,Object>>() {
                    @Override
                    public void done(Map<String,Object> o, com.parse.ParseException e) {
                        Log.e("REQUEST:","Done");
                        if (e == null) {
                            Log.e("Result form cloud: ",(String)o.get("token"));

                        } else {
                            Log.d("App", "Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.mapButton){
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);
            finish();
        }else if(id == R.id.walletButton){

        }

        return super.onOptionsItemSelected(item);
    }
}
