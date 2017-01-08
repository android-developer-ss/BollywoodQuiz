package com.svs.myprojects.bollywoodquiz.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.listeners.OnFragmentInteractionListener;
import com.svs.myprojects.bollywoodquiz.utils.Constants;
import com.svs.myprojects.bollywoodquiz.utils.Helper;
import com.svs.myprojects.bollywoodquiz.utils.Utility;

import java.util.HashMap;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private Button mLoginButton;
    private View mRootView;
    private TextView mRegisterHere;
    private AutoCompleteTextView mUserId;
    private AutoCompleteTextView mPassword;
    Firebase mRef;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRef = new Firebase(Constants.FIREBASE_USERS_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginButton = (Button) mRootView.findViewById(R.id.login_button);
        mLoginButton.setText(R.string.login);
        mLoginButton.setOnClickListener(this);
        mRegisterHere = (TextView) mRootView.findViewById(R.id.register_here);
        mRegisterHere.setOnClickListener(this);
        mUserId = (AutoCompleteTextView) mRootView.findViewById(R.id.user_id);
        mPassword = (AutoCompleteTextView) mRootView.findViewById(R.id.password);
    }

    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    public void displayMessage(String message) {
        if (mListener != null) {
            mListener.displayMessage(message);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.login_button:
                Helper.hideKeyboard(getContext(), mLoginButton);
                if (allFieldsFilled()) {
                    Query queryRef = mRef.orderByKey();
                    queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(mUserId.getText().toString())) {
                                dataSnapshot = dataSnapshot.child(mUserId.getText().toString());
                                HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
                                if (hashMap.get("userID").equals(mUserId.getText().toString()) && hashMap.get("password").equals(mPassword.getText().toString())) {
                                    displayMessage("Login successful");
                                    Utility.storeUserModelToSharedPreferences(getActivity(), Utility.convertStringToUserModel(hashMap));
                                    onButtonPressed(v.getId());
                                } else {
                                    displayMessage("User ID / Password did not match...");
                                }
                            } else {
                                displayMessage("User ID / Password did not match...");
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                break;
            case R.id.register_here:
                onButtonPressed(v.getId());
                break;
        }
    }

    private boolean allFieldsValid() {
        if (Utility.containsSpecialCharacter(mUserId.getText().toString())) {
            displayMessage(getResources().getString(R.string.avoid_special_characters));
            mUserId.setText("");
            return false;
        }
        return true;
    }

    private boolean allFieldsFilled() {
        if (mUserId.getText().toString().length() < 1) {
            return false;
        } else if (mPassword.getText().toString().length() < 1) {
            return false;
        } else if (!allFieldsValid()) {
            return false;
        }
        return true;
    }
}
