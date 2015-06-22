package com.diqisuperking.diqiking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek on 6/22/2015.
 */
public class StoreFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LocationTracker locationTracker;
    private double la;
    private double lo;

    private List<ParseObject> mContentItems;

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        la = getArguments().getDouble("la");
        lo = getArguments().getDouble("lo");
        
        Log.e("onCreateView","here");
        ParseGeoPoint point = new ParseGeoPoint(la, lo);
        Log.e("onCreateView","here2 "+"la "+la+" lo "+lo);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Stores");
        query.whereNear("location", point);
        //query.setLimit(10);
        try {
            Log.e("onCreateView","here3");
            mContentItems = query.find();
        }catch (Exception e){
            Log.e("onCreateView","here4");
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        Log.e("onViewCreated","here");
        if(mContentItems==null){
            Log.e("onViewCreated","here 2");
        }
        mAdapter = new RecyclerViewMaterialAdapter(new StoreAdapter(mContentItems));
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }
}
