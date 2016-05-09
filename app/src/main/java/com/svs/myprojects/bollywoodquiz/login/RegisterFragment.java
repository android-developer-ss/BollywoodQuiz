package com.svs.myprojects.bollywoodquiz.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.listeners.OnFragmentInteractionListener;
import com.svs.myprojects.bollywoodquiz.models.UserModel;
import com.svs.myprojects.bollywoodquiz.utils.Constants;
import com.svs.myprojects.bollywoodquiz.utils.Utility;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = RegisterFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private Button mButton;
    private View mRootView;
    private AutoCompleteTextView mUserId;
    private AutoCompleteTextView mUserName;
    private AutoCompleteTextView mPassword;
    private AutoCompleteTextView mConfirmPassword;
    private AutoCompleteTextView mEmailId;
    Firebase mRef;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mRef = new Firebase(Constants.FIREBASE_USERS_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_register, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButton = (Button) mRootView.findViewById(R.id.register_button);
        mButton.setText(R.string.register);
        mButton.setOnClickListener(this);
        mUserId = (AutoCompleteTextView) mRootView.findViewById(R.id.user_id);
        mUserName = (AutoCompleteTextView) mRootView.findViewById(R.id.username);
        mPassword = (AutoCompleteTextView) mRootView.findViewById(R.id.password);
        mConfirmPassword = (AutoCompleteTextView) mRootView.findViewById(R.id.confirm_password);
        mEmailId = (AutoCompleteTextView) mRootView.findViewById(R.id.email_id);
    }

    public void onButtonPressed(int viewId) {
        if (mListener != null) {
            mListener.onFragmentInteraction(viewId);
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
        if (v.getId() == R.id.register_button) {
            if (!allFieldsFilled()) {
                displayMessage(getResources().getString(R.string.string_fill_all_fields));
                return;
            }
            if (!passwordsMatch()) {
                displayMessage(getResources().getString(R.string.password_do_not_match));
                return;
            }
            if (!isValidEmail(mEmailId.getText().toString())) {
                displayMessage(getResources().getString(R.string.enter_valid_email_id));
                return;
            }

            Query queryRef = mRef.orderByKey();
            queryRef.limitToFirst(1);
            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(mUserId.getText().toString())) {
                        displayMessage("User Id already exists.. Please use different user Id...");
                    } else {
                        UserModel newUser = new UserModel(mUserId.getText().toString(),
                                mUserName.getText().toString(), mPassword.getText().toString(), mPassword.getText().toString());
                        mRef.child(mUserId.getText().toString()).setValue(newUser);
                        Utility.storeUserModelToSharedPreferences(getActivity(), newUser);
                        displayMessage("Registration Successful");
                        onButtonPressed(v.getId());
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

    private boolean passwordsMatch() {
        if (mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            return true;
        }
        return false;
    }

    private boolean allFieldsFilled() {
        if (mUserId.getText().toString().length() < 1) {
            return false;
        } else if (mPassword.getText().toString().length() < 1) {
            return false;
        } else if (mUserName.getText().toString().length() < 1) {
            return false;
        } else if (mConfirmPassword.getText().toString().length() < 1) {
            return false;
        } else if (mEmailId.getText().toString().length() < 1) {
            return false;
        }
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
