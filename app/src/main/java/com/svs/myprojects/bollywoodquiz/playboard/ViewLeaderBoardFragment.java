package com.svs.myprojects.bollywoodquiz.playboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svs.myprojects.bollywoodquiz.R;


public class ViewLeaderBoardFragment extends Fragment {

    public static String TAG = ViewLeaderBoardFragment.class.getSimpleName();

    public ViewLeaderBoardFragment() {
        // Required empty public constructor
    }

    public static ViewLeaderBoardFragment newInstance() {
        ViewLeaderBoardFragment fragment = new ViewLeaderBoardFragment();
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
        return inflater.inflate(R.layout.fragment_view_leader_board, container, false);
    }
}
