package com.diqisuperking.diqiking;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


import com.gc.materialdesign.views.ButtonRectangle;
import com.github.florent37.materialviewpager.MaterialViewPager;
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
    private MaterialViewPager mViewPager;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    
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

       setTitle("");
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
       toolbar = mViewPager.getToolbar();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            int oldPosition = -1;

            @Override
            public Fragment getItem(int position) {
                return RecyclerViewFragment.newInstance();
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);

                //only if position changed
                if (position == oldPosition)
                    return;
                oldPosition = position;

                int color = 0;
                String imageUrl = "";
                switch (position % 4) {
                    case 0:
                        imageUrl = "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg";
                        color = getResources().getColor(R.color.blue);
                        break;
                    case 1:
                        imageUrl = "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg";
                        color = getResources().getColor(R.color.purple);
                        break;
                    case 2:
                        imageUrl = "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg";
                        color = getResources().getColor(R.color.cyan);
                        break;
                }

                final int fadeDuration = 400;
                mViewPager.setImageUrl(imageUrl, fadeDuration);
                mViewPager.setColor(color, fadeDuration);

            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Store";
                    case 1:
                        return "Wallet";
                    case 2:
                        return "Profile";
                }
                return "";
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        //mViewPager.getViewPager().setCurrentItem(0);
//        ButtonRectangle testButton = (ButtonRectangle)findViewById(R.id.tbutton);
//        //final Button testButton = (Button)findViewById(R.id.tbutton);
//        testButton.setOnClickListener(new Button.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.e("REQUEST:","Start");
//                // TODO Auto-generated method stub
//                HashMap<String, Object> params = new HashMap<String, Object>();
//                params.put("username", "diqiking");
//                params.put("password", "dqk");
//                ParseCloud.callFunctionInBackground("diqi_get_token", params, new FunctionCallback<Map<String,Object>>() {
//                    @Override
//                    public void done(Map<String,Object> o, com.parse.ParseException e) {
//                        Log.e("REQUEST:","Done");
//                        if (e == null) {
//                            Log.e("Result form cloud: ",(String)o.get("token"));
//
//                        } else {
//                            Log.d("App", "Error: " + e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
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
//        if (id == R.id.action_settings) {
//            return true;
//        }else 
        if(id == R.id.camButton){
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
        }else if(id == R.id.mapButton){
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            intent.putExtra("la",locationTracker.getLatitude());
            intent.putExtra("lo",locationTracker.getLongitude());
            startActivity(intent);
            //finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
