package com.svs.myprojects.bollywoodquiz.playboard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.listeners.OnItemClickListener;
import com.svs.myprojects.bollywoodquiz.utils.Constants;

import java.util.HashMap;

/**
 * Created by Snehal on 5/4/16.
 */
public class ViewMyScoreAdapter extends RecyclerView.Adapter<ViewMyScoreAdapter.ViewHolder> {

    private Context mContext;
    private HashMap<Integer, Integer> mScoreHashMap;
    private final OnItemClickListener mListener;

    public ViewMyScoreAdapter(Context context, HashMap<Integer, Integer> scoreModel,
                              OnItemClickListener listener) {
        this.mContext = context;
        this.mScoreHashMap = scoreModel;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view_my_score, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mScoreHashMap.get(position), mListener, position);
//        ScoreModel scoreModel = mScoreHashMap.get(position);
        holder.name.setText("Level: " + (position + 1));
        holder.content.setText(Constants.getLevelContent(position));
        holder.description.setText(Constants.getLevelDescription(position));

        if (mScoreHashMap.containsKey(position + 1)) {
            holder.score.setText("Highest Score: " + mScoreHashMap.get(position + 1));
        } else {
            holder.score.setText("Not yet played");
        }
    }

    @Override
    public int getItemCount() {
        return Constants.MAX_LEVEL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, content, description, score;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.vms_level_name);
            content = (TextView) view.findViewById(R.id.vms_level_content);
            description = (TextView) view.findViewById(R.id.vms_level_description);
            score = (TextView) view.findViewById(R.id.vms_score);
        }

        public void bind(final Integer item, final OnItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, position);
                }
            });
        }
    }
}
