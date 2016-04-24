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

import com.svs.myprojects.bollywoodquiz.R;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private Button mButton;
    private View mRootView;
    private TextView mRegisterHere;
    private AutoCompleteTextView mUserId;
    private AutoCompleteTextView mPassword;

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
        if (getArguments() != null) {

        }
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
            case R.id.login_button:
                if (allFieldsFilled()) {
                    onButtonPressed(v.getId());
                } else {
                    onError(getResources().getString(R.string.string_fill_all_fields));
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int viewId);
        void onError(String message);
    }
}
