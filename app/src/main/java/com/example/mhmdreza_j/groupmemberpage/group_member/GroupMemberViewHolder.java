package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.bottom_sheet.BottomDialogFragment;
import com.example.mhmdreza_j.groupmemberpage.listener.BottomSheetCloseListener;
import com.example.mhmdreza_j.groupmemberpage.listener.DataSetChangeListener;
import com.example.mhmdreza_j.groupmemberpage.listener.LoadMoreGroupMemberListener;

public class GroupMemberViewHolder extends RecyclerView.ViewHolder implements BottomSheetCloseListener{
    private ImageView groupMemberImageView;
    private ImageView adminImageView;
    private TextView nameTextView;
    private TextView lastSeenTextView;
    private static int indexOfLoadData = 40;
    private BottomDialogFragment bottomSheetDialog;
    private LoadMoreGroupMemberListener loadMoreListener;
    private DataSetChangeListener dataSetChangeListener;

    public GroupMemberViewHolder(@NonNull final View itemView, LoadMoreGroupMemberListener loadMoreListener, DataSetChangeListener dataSetChangeListener) {
        super(itemView);
        this.dataSetChangeListener = dataSetChangeListener;
        this.loadMoreListener = loadMoreListener;
        groupMemberImageView = itemView.findViewById(R.id.image_view_group_member_image);
        adminImageView = itemView.findViewById(R.id.image_view_group_member_admin);
        nameTextView = itemView.findViewById(R.id.text_view_group_member_name);
        lastSeenTextView = itemView.findViewById(R.id.text_view_group_member_last_seen);
        groupMemberImageView.setBackgroundResource(R.drawable.group_member_profile_background);
    }

    public void onBindViewModel(final GroupMemberViewModel model) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(), nameTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                bottomSheetDialog = new BottomDialogFragment(model, dataSetChangeListener, getThisViewHolder());
                bottomSheetDialog.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(), "Custom Bottom Sheet");
                return false;
            }
        });
        if (getAdapterPosition() == indexOfLoadData) {
            loadMoreListener.getData();
            Toast.makeText(itemView.getContext(), String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            GroupMemberViewHolder.indexOfLoadData += 50;
        }
        if (model.isAdmin()) {
            adminImageView.setVisibility(View.VISIBLE);
        } else {
            adminImageView.setVisibility(View.GONE);
        }
        nameTextView.setText(model.getName());
        String lastSeen = model.getLastSeen();
        lastSeenTextView.setText(lastSeen);
        if(lastSeen.equals("online")){
            lastSeenTextView.setTextColor(itemView.getResources().getColor(R.color.online_user_text_color));
        }
        else {
            lastSeenTextView.setTextColor(itemView.getResources().getColor(R.color.regular));
        }
        Glide.with(itemView.getContext())
                .load(model.getProfilePictureLink())
                .apply(new RequestOptions().circleCrop())
                .into(groupMemberImageView);
    }

    @Override
    public void closeBottomSheet() {
        bottomSheetDialog.dismiss();
    }

    private BottomSheetCloseListener getThisViewHolder() {
        return this;
    }

    public static void resetIndexOfLoadData(){
        indexOfLoadData = 40;
    }
}
