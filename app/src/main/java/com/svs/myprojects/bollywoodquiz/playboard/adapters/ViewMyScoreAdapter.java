package com.svs.myprojects.bollywoodquiz.playboard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.listeners.OnItemClickListener;
import com.svs.myprojects.bollywoodquiz.models.UserModel;

import java.util.List;

/**
 * Created by Snehal on 5/4/16.
 */
public class ViewMyScoreAdapter extends RecyclerView.Adapter<ViewMyScoreAdapter.ViewHolder> {

    private Context mContext;
    private List<UserModel> mUserModelList;
    private final OnItemClickListener mListener;

    public ViewMyScoreAdapter(Context context, List<UserModel> msaNoteList,
                               OnItemClickListener listener) {
        this.mContext = context;
        this.mUserModelList = msaNoteList;
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
        holder.bind(mUserModelList.get(position), mListener, position);
        UserModel userModel = mUserModelList.get(position);
//        holder.name.setText(userModel.createdByName);
//        holder.content.setText(userModel.noteContent);
//        if (!LongUtil.isEmpty(msaNote.updatedDate)) {
//            holder.createdDate.setText(String.format(mContext.getResources().getString(R.string.notes_updated),
//                    DateUtil.convertTimeStampToDate(msaNote.updatedDate, Constants.PSDateFormatter.MONTH_NAME_DAY_YEAR_AT_TIME)));
//        } else {
//            holder.createdDate.setText(String.format(mContext.getResources().getString(R.string.notes_created),
//                    DateUtil.convertTimeStampToDate(msaNote.createdDate, Constants.PSDateFormatter.MONTH_NAME_DAY_YEAR_AT_TIME)));
//        }
    }

    @Override
    public int getItemCount() {
        return mUserModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, content, createdDate;

        public ViewHolder(View view) {
            super(view);
//            name = (TextView) view.findViewById(R.id.personal_shopper_name);
//            content = (TextView) view.findViewById(R.id.note_content);
//            createdDate = (TextView) view.findViewById(R.id.created_date);
        }

        public void bind(final UserModel item, final OnItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, position);
                }
            });
        }
    }
}
