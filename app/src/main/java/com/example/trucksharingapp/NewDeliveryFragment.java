package com.example.trucksharingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewDeliveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewDeliveryFragment extends Fragment {



    public NewDeliveryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewDeliveryFragment newInstance() {
        NewDeliveryFragment fragment = new NewDeliveryFragment();
        Bundle args = new Bundle();

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
        View view = inflater.inflate(R.layout.fragment_new_delivery, container, false);


        Button nextButton = view.findViewById(R.id.nextbutton);
        EditText editTextPickupTime = view.findViewById(R.id.editTextPickupTime);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        EditText editTextReceiverName = view.findViewById(R.id.editTextReceiverName);
        EditText editTextPickupLocation = view.findViewById(R.id.editTextPickupLocation);

        long selectedDateInMillis = calendarView.getDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(selectedDateInMillis);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-based, so add 1
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String selectedDateString = year + "-" + month + "-" + dayOfMonth;
        String pickupTime = editTextPickupTime.getText().toString();
        String pickupDate = selectedDateString;
        String receiverName = editTextReceiverName.getText().toString();
        String pickupLocation = editTextPickupLocation.getText().toString();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = NewDeliveryNextFragment.newInstance(pickupLocation,pickupDate,pickupTime,receiverName);
                // Start new transaction to replace current fragment with SignupFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainActivityLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }
}