package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhmdreza_j.groupmemberpage.R;

public class GroupMemberViewHolder extends RecyclerView.ViewHolder{
    private ImageView groupMemberImageView;
    private ImageView adminImageView;
    private TextView nameTextView;
    private TextView lastSeenTextView;

    public GroupMemberViewHolder(@NonNull View itemView) {
        super(itemView);
        groupMemberImageView = itemView.findViewById(R.id.image_view_group_member_image);
        adminImageView = itemView.findViewById(R.id.image_view_group_member_admin);
        nameTextView = itemView.findViewById(R.id.text_view_group_member_name);
        lastSeenTextView = itemView.findViewById(R.id.text_view_group_member_last_seen);
        groupMemberImageView.setBackgroundResource(R.drawable.group_member_profile_background);
    }

    public void onBindViewModel(GroupMemberViewModel model){
        if (model.isAdmin()) {
            adminImageView.setVisibility(View.VISIBLE);
        }
        else {
            adminImageView.setVisibility(View.GONE);
        }
        nameTextView.setText(model.getName());
        lastSeenTextView.setText(model.getLastSeen());
        Glide.with(itemView.getContext())
                .load(model.getProfilePicture().getUrl())
                .apply(new RequestOptions().circleCrop())
                .into(groupMemberImageView);
    }
}
