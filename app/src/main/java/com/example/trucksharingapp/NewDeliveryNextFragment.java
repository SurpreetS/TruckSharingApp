package com.example.trucksharingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import sqllitehelper.DatabaseHelper;
import sqllitehelper.MyOrderData;
import sqllitehelper.MyOrderDatabaseHelper;
import sqllitehelper.RecyclerviewDatabaseHelper;
import sqllitehelper.UserData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewDeliveryNextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewDeliveryNextFragment extends Fragment {



    MyOrderDatabaseHelper databaseHelper;
    public NewDeliveryNextFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewDeliveryNextFragment newInstance(String pickupLocation,String pickupDate,String pickupTime,String receiverName) {
        NewDeliveryNextFragment fragment = new NewDeliveryNextFragment();
        Bundle args = new Bundle();
        args.putString("pickupLocation",pickupLocation);
        args.putString("pickupDate",pickupDate);
        args.putString("pickupTime",pickupTime);
        args.putString("receiverName",receiverName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_delivery_next, container, false);


        EditText lengthEditText;
        EditText heightEditText;
        EditText weightEditText;
        EditText widthEditText;
        Button createOrderButton;
        String[] goodTypes = {"Furniture", "Dry Goods", "Food","Building Material", "Other"};
        String[] vehicleTypes = {"Truck", "Van", "Refrigerated Truck","Mini-Truck", "Other"};
        RadioGroup radioGroupGoods = view.findViewById(R.id.radioGroupGoodtype);
        RadioGroup radioGroupVehicles = view.findViewById(R.id.radioGroupVehicletype);

        for(int i=0; i<goodTypes.length;i++){
            RadioButton radioButtonGood = new RadioButton(getActivity());
            RadioButton radioButtonVehicle = new RadioButton(getActivity());

            radioButtonGood.setId(i);
            radioButtonGood.setText(goodTypes[i]);

            radioButtonVehicle.setId(i);
            radioButtonVehicle.setText(vehicleTypes[i]);

            radioGroupGoods.addView(radioButtonGood);
            radioGroupVehicles.addView(radioButtonVehicle);

        }

        lengthEditText = view.findViewById(R.id.editTextTextPersonName9);
        heightEditText = view.findViewById(R.id.editTextTextPersonName10);
        weightEditText = view.findViewById(R.id.editTextTextPersonName7);
        widthEditText = view.findViewById(R.id.editTextTextPersonName8);
        createOrderButton = view.findViewById(R.id.buttonCreateOrder);
        createOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vehicleTypeId = radioGroupVehicles.getCheckedRadioButtonId();
                int goodTypeId = radioGroupGoods.getCheckedRadioButtonId();
                double length = Double.parseDouble(lengthEditText.getText().toString());
                double height = Double.parseDouble(heightEditText.getText().toString());
                double weight = Double.parseDouble(weightEditText.getText().toString());
                double width = Double.parseDouble(widthEditText.getText().toString());
                RadioButton goodSelectedRadioButton = view.findViewById(goodTypeId);
                RadioButton vehicleSelectedRadioButton = view.findViewById(vehicleTypeId);
                String goodType = goodSelectedRadioButton.getText().toString();;
                String vehicleType =vehicleSelectedRadioButton.getText().toString();;


                databaseHelper = new MyOrderDatabaseHelper(getActivity());
                MyOrderData myOrderData = new MyOrderData(getArguments().getString("pickupTime"),getArguments().getString("pickupDate"),getArguments().getString("receiverName"),getArguments().getString("pickupLocation"),vehicleType,goodType,weight,length,height,width,"2 ton truck for hire","Lion Truck",R.drawable.truck1);
                long result = databaseHelper.insertDelivery(myOrderData);

                // Check if the insertion was successful
                if (result != -1) {
                    RecyclerviewDatabaseHelper databaseHelper1;
                    databaseHelper1= new RecyclerviewDatabaseHelper(getContext());
                    databaseHelper1.insertTruck(new MyDataModel("Lion Truck", "2 ton truck for hire",R.drawable.truck1));
                    Toast.makeText(getActivity(), "Order created successfully", Toast.LENGTH_SHORT).show();

                    Fragment fragment = MyOrdersFragment.newInstance();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStackImmediate(); // Remove the current fragment from the back stack (optional)
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainActivityLayout, fragment);
                    transaction.addToBackStack("MyOrdersFragment"); // Add the transaction to the back stack with a unique name
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Failed to create order", Toast.LENGTH_SHORT).show();
                }


            }
        });





        return view;
    }
}