package com.example.firebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    ArrayList<TravelDeal> deals ;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;


    public DealAdapter() {
        FirebaseUtil.openFbReference("traveldeals");
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;

        deals = FirebaseUtil.travelDeals;


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
                Log.d("Deal",td.getTitle());
                td.setId(dataSnapshot.getKey());
                deals.add(td);
                notifyItemChanged(deals.size()-1);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        databaseReference.addChildEventListener(childEventListener);




    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false);
        return new DealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        TravelDeal deal = deals.get(position);

    }

    @Override
    public int getItemCount() {
        return deals.size();
    }



    public class DealViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public DealViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
        }

        public void bind(TravelDeal travelDeal){
            textView.setText(travelDeal.getTitle());
        }
    }
}
