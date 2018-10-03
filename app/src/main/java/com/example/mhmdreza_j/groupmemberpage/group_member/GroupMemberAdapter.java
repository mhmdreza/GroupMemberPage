package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmdreza_j.groupmemberpage.R;

import java.util.ArrayList;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberViewHolder>{
    private ArrayList<GroupMemberViewModel> groupMemberViewModels;

    public GroupMemberAdapter(ArrayList<GroupMemberViewModel> groupMemberViewModels) {
        this.groupMemberViewModels = groupMemberViewModels;
    }

    @NonNull
    @Override
    public GroupMemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_member_card, null);
        return new GroupMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMemberViewHolder groupMemberViewHolder, int i) {
        GroupMemberViewModel model = groupMemberViewModels.get(i);
        groupMemberViewHolder.onBindViewModel(model);
    }

    @Override
    public int getItemCount() {
        return groupMemberViewModels.size();
    }
}
