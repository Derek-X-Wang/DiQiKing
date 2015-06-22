package com.diqisuperking.diqiking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Derek on 6/21/2015.
 */
public class WalletAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public WalletAdapter(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_wallet_big, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_wallet_small, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
//                TextView storeName = (TextView)holder.itemView.findViewById(R.id.storenamebig);
//                storeName.setText(contents.get(position).getString("brandName"));
//                TextView storeAddress = (TextView)holder.itemView.findViewById(R.id.storeaddressbig);
//                storeAddress.setText(contents.get(position).getString("address"));
                break;
            case TYPE_CELL:
//                TextView storeNameSmall = (TextView)holder.itemView.findViewById(R.id.storenamesmall);
//                storeNameSmall.setText(contents.get(position).getString("brandName"));
//                TextView storeAddressSmall = (TextView)holder.itemView.findViewById(R.id.storeaddresssmall);
//                storeAddressSmall.setText(contents.get(position).getString("address"));
                break;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View.OnClickListener mListener;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            //imgViewIcon.setOnClickListener(this);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), WalletExchange.class);
            v.getContext().startActivity(intent);
        }

//        public static interface IMyViewHolderClicks {
//            public void onPotato(View caller);
//            public void onTomato(ImageView callerImage);
//        }

    }


}
        
//        extends QuickAdapter {
//    public WalletAdapter(Context context, int layoutResId) {
//        super(context, layoutResId);
//    }
//
//    public WalletAdapter(Context context, int layoutResId, List data) {
//        super(context, layoutResId, data);
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }
//
//    @Override
//    protected void convert(BaseAdapterHelper helper, Object item) {
//
//    }
//}
