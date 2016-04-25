package com.svs.myprojects.bollywoodquiz.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.svs.myprojects.bollywoodquiz.MainActivity;
import com.svs.myprojects.bollywoodquiz.R;

public class LoginActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener,
        LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener,
        View.OnClickListener {

    private ImageView ic_back_arrow;
    private CoordinatorLayout mCoordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        setupViews();
        Firebase.setAndroidContext(this);
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
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

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
            case R.id.register_here:
                replaceFragment(RegisterFragment.newInstance(), true);
                break;
            case R.id.register_button:
            case R.id.login_button:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    public void onError(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
