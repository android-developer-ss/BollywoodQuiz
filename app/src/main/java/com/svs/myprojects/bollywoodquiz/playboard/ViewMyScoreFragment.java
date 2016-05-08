package com.svs.myprojects.bollywoodquiz.playboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.utils.Utility;
import com.svs.myprojects.bollywoodquiz.views.BackButtonView;


public class ViewMyScoreFragment extends Fragment implements View.OnClickListener {

    public static String TAG = ViewMyScoreFragment.class.getSimpleName();

    private BackButtonView mBackArrow;

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
        ((TextView) getView().findViewById(R.id.info_text)).setText(Utility.getUserModelFromSharedPreferences(getActivity()).toString());
        mBackArrow = (BackButtonView) getView().findViewById(R.id.back_button);
        mBackArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button) {
            getFragmentManager().popBackStack();
        }
    }
}
