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

import com.svs.myprojects.bollywoodquiz.R;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = RegisterFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private Button mButton;
    private View mRootView;
    private AutoCompleteTextView mUserId;
    private AutoCompleteTextView mUserName;
    private AutoCompleteTextView mPassword;
    private AutoCompleteTextView mConfirmPassword;

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
    }

    public void onButtonPressed(int viewId) {
        if (mListener != null) {
            mListener.onFragmentInteraction(viewId);
        }
    }

    public void onError(String message) {
        if (mListener != null) {
            mListener.onError(message);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                if (allFieldsFilled()) {
                    if (passwordsMatch())
                        onButtonPressed(v.getId());
                    else
                        onError(getResources().getString(R.string.password_do_not_match));
                } else {
                    onError(getResources().getString(R.string.string_fill_all_fields));
                }
                break;
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
//            mUserId.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            return false;
        } else if (mPassword.getText().toString().length() < 1) {
//            mPassword.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            return false;
        } else if (mUserName.getText().toString().length() < 1) {
            return false;
        } else if (mConfirmPassword.getText().toString().length() < 1) {
            return false;
        }
        return true;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int viewId);

        void onError(String message);
    }
}
