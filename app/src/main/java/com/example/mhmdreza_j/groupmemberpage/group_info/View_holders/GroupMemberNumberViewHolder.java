package com.example.mhmdreza_j.groupmemberpage.group_info.View_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.group_info.GroupOptionViewModel;

public class GroupMemberNumberViewHolder extends RecyclerView.ViewHolder {
    private TextView groupMemberInfoTextView;
    public GroupMemberNumberViewHolder(@NonNull View itemView) {
        super(itemView);
        groupMemberInfoTextView = itemView.findViewById(R.id.text_view_group_member_info);
    }

    public void onBindViewHolder(GroupOptionViewModel model){
        groupMemberInfoTextView.setText(model.getTitle());
    }
}
