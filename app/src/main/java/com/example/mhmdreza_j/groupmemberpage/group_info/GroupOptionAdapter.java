package com.example.mhmdreza_j.groupmemberpage.group_info;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.group_info.View_holders.GroupMemberNumberViewHolder;
import com.example.mhmdreza_j.groupmemberpage.group_info.View_holders.GroupOptionViewHolder;

import java.util.ArrayList;

public class GroupOptionAdapter extends RecyclerView.Adapter {
    private ArrayList<GroupOptionViewModel> list;

    public GroupOptionAdapter(ArrayList<GroupOptionViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_option_layout, null);
                return new RecyclerView.ViewHolder(view) {
                };
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_option_layout2, null);
                return new RecyclerView.ViewHolder(view) {
                };
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GroupOptionViewModel model = list.get(i);
        switch (i) {
            case 0:
                ((GroupOptionViewHolder) viewHolder).onBindViewHolder(model);
                break;
            case 1:
                ((GroupMemberNumberViewHolder) viewHolder).onBindViewHolder(model);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()){
            case 0:
                return GroupOptionViewModel.OPTION_TYPE;
            case 1:
                return GroupOptionViewModel.TITLE_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
