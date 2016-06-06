package com.svs.myprojects.bollywoodquiz.playboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.listeners.OnItemClickListener;
import com.svs.myprojects.bollywoodquiz.login.RegisterFragment;
import com.svs.myprojects.bollywoodquiz.models.UserModel;
import com.svs.myprojects.bollywoodquiz.playboard.adapters.ViewMyScoreAdapter;
import com.svs.myprojects.bollywoodquiz.utils.Utility;
import com.svs.myprojects.bollywoodquiz.views.BackButtonView;


public class ViewMyScoreFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    public static String TAG = ViewMyScoreFragment.class.getSimpleName();

    private BackButtonView mBackArrow;
    private TextView mName, mUserId, mEmailId;
    private UserModel mUserModel;
    private RecyclerView mRecyclerView;
    private ImageView mEditProfile;

    public ViewMyScoreFragment() {
        // Required empty public constructor
    }

    public static ViewMyScoreFragment newInstance() {
        ViewMyScoreFragment fragment = new ViewMyScoreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_my_score, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mBackArrow = (BackButtonView) getView().findViewById(R.id.back_button);
//        mBackArrow.setOnClickListener(this);
        mName = (TextView) getView().findViewById(R.id.my_name);
        mUserModel = Utility.getUserModelFromSharedPreferences(getActivity());
        mName.setText(mUserModel.fullName);
        mUserId = (TextView) getView().findViewById(R.id.user_id_text);
        mUserId.setText("User id: " + mUserModel.userID);
        mEmailId = (TextView) getView().findViewById(R.id.email_id_text);
        if (mUserModel.emailID != null)
            mEmailId.setText("Email id: " + mUserModel.emailID);
        mEditProfile = (ImageView) getView().findViewById(R.id.edit_profile);
        mEditProfile.setOnClickListener(this);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.view_my_score_recycler_view);

        ViewMyScoreAdapter viewMyScoreAdapter = new ViewMyScoreAdapter(getActivity(), mUserModel.scoreHashMap, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(viewMyScoreAdapter);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) getView().findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setExpandedTitleGravity(Gravity.TOP | Gravity.RIGHT | Gravity.END);
        collapsingToolbar.setExpandedTitleMarginTop(0);
        collapsingToolbar.setExpandedTitleMarginEnd(30);
        collapsingToolbar.setTitle("SCOREBOARD");

        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.anim_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button) {
            getFragmentManager().popBackStack();
        }
        if (v.getId() == R.id.edit_profile) {
            ((PlayBoardActivity) getActivity()).replaceFragment(RegisterFragment.newInstance(), RegisterFragment.TAG);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Object item, int position) {

    }
}
