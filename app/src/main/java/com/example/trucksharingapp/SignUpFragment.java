package com.example.trucksharingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sqllitehelper.DatabaseHelper;
import sqllitehelper.UserData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {



    private EditText fullNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText phoneNumberEditText;
    private Button createAccountButton;
    private DatabaseHelper databaseHelper;

    public SignUpFragment() {
        // Required empty public constructor
    }


    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);



        fullNameEditText = view.findViewById(R.id.editTextPassword);
        usernameEditText = view.findViewById(R.id.editTextTextPersonName3);
        passwordEditText = view.findViewById(R.id.editTextTextPersonName4);
        confirmPasswordEditText = view.findViewById(R.id.editTextTextPersonName5);
        phoneNumberEditText = view.findViewById(R.id.editTextPhone);
        createAccountButton = view.findViewById(R.id.button3);
        databaseHelper = new DatabaseHelper(getActivity());

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String fullName = fullNameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                long result = databaseHelper.insertUser(new UserData(fullName,username,password,phoneNumber));

                if(result == -1){
                    Toast.makeText(getContext(), "Failed to create an account", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                    // Switch to the home fragment
                    Fragment fragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainActivityLayout, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }





            }
        });

        return view;
    }
}