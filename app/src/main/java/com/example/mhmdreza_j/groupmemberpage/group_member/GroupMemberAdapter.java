package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.listener.DataSetChangeListener;
import com.example.mhmdreza_j.groupmemberpage.listener.LoadMoreGroupMemberListener;

import java.util.ArrayList;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberViewHolder>{
    private ArrayList<GroupMemberViewModel> groupMemberViewModels;
    private LoadMoreGroupMemberListener loadMoreListener;
    private DataSetChangeListener dataSetChangeListener;


    public GroupMemberAdapter(ArrayList<GroupMemberViewModel> groupMemberViewModels, LoadMoreGroupMemberListener loadMoreListener, DataSetChangeListener dataSetChangeListener) {
        this.groupMemberViewModels = groupMemberViewModels;
        this.loadMoreListener = loadMoreListener;
        this.dataSetChangeListener = dataSetChangeListener;
    }

    @NonNull
    @Override
    public GroupMemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_member_card, null);
        return new GroupMemberViewHolder(view, loadMoreListener, dataSetChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMemberViewHolder groupMemberViewHolder, int i) {
        GroupMemberViewModel model = groupMemberViewModels.get(i);
        groupMemberViewHolder.onBindViewModel(model);
    }

    @Override
    public int getItemCount() {
        if (groupMemberViewModels == null) {
            return 0;
        }
        return groupMemberViewModels.size();
    }
}
