package com.example.trucksharingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sqllitehelper.MyOrderData;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {

    // Define variables for the context and list of order data
    private Context mContext;
    private ArrayList<MyOrderData> orderList;

    // Constructor for MyOrderAdapter class
    public MyOrderAdapter(Context mContext, ArrayList<MyOrderData> orderList) {
        // Initialize context and list of order data
        this.mContext = mContext;
        this.orderList = orderList;
    }

    // Override onCreateViewHolder method to inflate layout for each item in RecyclerView
    @NonNull
    @Override
    public MyOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each item in RecyclerView
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_layout, parent, false);
        // Return ViewHolder with inflated view
        return new MyViewHolder(view);
    }

    // Override onBindViewHolder method to bind data to layout views
    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.MyViewHolder holder, int position) {
        // Bind data to views in layout
        holder.mTextView.setText(orderList.get(position).getTruckName());
        holder.mTextViewDescription.setText(orderList.get(position).getDescription());
        holder.imageView.setImageResource(orderList.get(position).getImage());
    }

    // Override getItemCount method to return the size of the list of order data
    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // Define MyViewHolder class to hold layout views for each item in RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Define layout views
        private TextView mTextView, mTextViewDescription;
        private ImageView imageView, shareImageView;

        // Constructor for MyViewHolder class
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find views by their IDs in layout
            mTextView = itemView.findViewById(R.id.nameTextView);
            mTextViewDescription = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            // Set click listener for item view
            itemView.setOnClickListener(this);
            shareImageView = itemView.findViewById(R.id.imageView3);
            // Set click listener for share image view
            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MyOrderData order = orderList.get(position);
                        // Perform the share action for the clicked order
                        performShareAction(order);
                    }
                }
            });
        }

        private void performShareAction(MyOrderData order) {
            String shareTitle = "Title";
            String shareDescription = "Description";

            String shareText = shareTitle + "\n" + shareDescription;

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            Intent chooserIntent = Intent.createChooser(shareIntent, "Share via");
            if (shareIntent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(chooserIntent);
            } else {
                Toast.makeText(mContext, "No app available to share", Toast.LENGTH_SHORT
                ).show();
            }
        }

        // Override onClick method to handle item click events
        @Override
        public void onClick(View v) {
            // Create new OrderDetailsFragment with position and list of order data as arguments
            Fragment fragment = OrderDetailsFragment.newInstance();
            // Get FragmentManager from the current activity
            FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
            // Start a new transaction to replace the current fragment with OrderDetailsFragment
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainActivityLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}