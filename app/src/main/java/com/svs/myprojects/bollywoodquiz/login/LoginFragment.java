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
import com.svs.myprojects.bollywoodquiz.utils.Utility;

import java.util.HashMap;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private Button mButton;
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
        mButton = (Button) mRootView.findViewById(R.id.login_button);
        mButton.setText(R.string.login);
        mButton.setOnClickListener(this);
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
                if (allFieldsFilled()) {
//                    final Firebase alanRef = mRef.child(mUserId.getText().toString());
//                    Query queryRef = mRef.orderByKey();
//                    queryRef.addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                            if (dataSnapshot.getKey().equals(mUserId.getText().toString())) {
//                                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
//                                if (hashMap.containsKey("userID") && hashMap.containsKey("password")) {
//                                    if (hashMap.get("userID").equals(mUserId.getText().toString()) &&
//                                            hashMap.get("password").equals(mPassword.getText().toString())) {
//                                        userExists[0] = true;
//                                        displayMessage("Login successful");
//                                        onButtonPressed(v.getId());
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                        }
//
//                        @Override
//                        public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                        }
//
//                        @Override
//                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(FirebaseError firebaseError) {
//
//                        }
//                    });
//
//                    if (!userExists[0]) {
//                        displayMessage("User ID / Password did not match...");
//                    }

                    Query queryRef = mRef.orderByKey();
                    queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(mUserId.getText().toString())) {
                                dataSnapshot = dataSnapshot.child(mUserId.getText().toString());
                                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                                if (hashMap.get("userID").equals(mUserId.getText().toString()) && hashMap.get("password").equals(mPassword.getText().toString())) {
                                    displayMessage("Login successful");
                                    Utility.storeUserModelToSharedPreferences(getActivity(), Utility.convertStringToUserModel(dataSnapshot.getValue().toString()));
                                    onButtonPressed(v.getId());
                                } else {
                                    displayMessage("User ID / Password did not match...");
                                }
//                                displayMessage("User Id already exists.. Please use different user Id...");
                            } else {
                                displayMessage("User ID / Password did not match...");
//                                UserModel newUser = new UserModel(mUserId.getText().toString(),
//                                        mUserName.getText().toString(), mPassword.getText().toString(), new ScoreModel());
//                                mRef.child(mUserId.getText().toString()).setValue(newUser);
//                                displayMessage("Registration Successful");
//                                onButtonPressed(v.getId());
                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
//                    onButtonPressed(v.getId());
                } else {
                    displayMessage(getResources().getString(R.string.string_fill_all_fields));
                }
                break;
            case R.id.register_here:
                onButtonPressed(v.getId());
                break;
        }
    }

    private boolean allFieldsFilled() {
        if (mUserId.getText().toString().length() < 1) {
//            mUserId.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            return false;
        } else if (mPassword.getText().toString().length() < 1) {
//            mPassword.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            return false;
        }
        return true;
    }
}
