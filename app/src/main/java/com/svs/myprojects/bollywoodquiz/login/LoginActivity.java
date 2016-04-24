package com.svs.myprojects.bollywoodquiz.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.svs.myprojects.bollywoodquiz.R;

public class LoginActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener,
        LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener,
        View.OnClickListener {

    private static final String FIREBASE_URL = "https://android-chat.firebaseio-demo.com";
    private ImageView ic_back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        setupViews();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //Listen for changes in the back stack
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        //Handle when activity is recreated like on orientation Change
//        shouldDisplayHomeUp();
        replaceFragment(LoginFragment.newInstance(), false);
    }

    private void setupViews() {
        ic_back_arrow = (ImageView) findViewById(R.id.ic_back_arrow);
        ic_back_arrow.setOnClickListener(this);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
        if (canback) {
            ic_back_arrow.setVisibility(View.VISIBLE);
        } else {
            ic_back_arrow.setVisibility(View.GONE);
        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        //This method is called when the up button is pressed. Just the pop back stack.
//        getSupportFragmentManager().popBackStack();
//        return true;
//    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(int viewId) {
        switch (viewId) {
            case R.id.login_button:
                break;
            case R.id.register_here:
                replaceFragment(RegisterFragment.newInstance(), true);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_back_arrow:
                getSupportFragmentManager().popBackStack();
                break;
        }
    }
}