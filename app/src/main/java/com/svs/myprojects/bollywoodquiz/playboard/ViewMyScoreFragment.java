package com.svs.myprojects.bollywoodquiz.playboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svs.myprojects.bollywoodquiz.R;


public class ViewMyScoreFragment extends Fragment {

    public static String TAG = ViewMyScoreFragment.class.getSimpleName();

    public ViewMyScoreFragment() {
        // Required empty public constructor
    }

    public static ViewMyScoreFragment newInstance() {
        ViewMyScoreFragment fragment = new ViewMyScoreFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
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
}
