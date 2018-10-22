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

public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder>{
    private ArrayList<MemberViewModel> memberViewModels;
    private LoadMoreGroupMemberListener loadMoreListener;
    private DataSetChangeListener dataSetChangeListener;


    public MemberAdapter(ArrayList<MemberViewModel> memberViewModels, LoadMoreGroupMemberListener loadMoreListener, DataSetChangeListener dataSetChangeListener) {
        this.memberViewModels = memberViewModels;
        this.loadMoreListener = loadMoreListener;
        this.dataSetChangeListener = dataSetChangeListener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_member_card, null);
        return new MemberViewHolder(view, loadMoreListener, dataSetChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, int i) {
        MemberViewModel model = memberViewModels.get(i);
        memberViewHolder.onBindViewModel(model);
    }

    @Override
    public int getItemCount() {
        if (memberViewModels == null) {
            return 0;
        }
        return memberViewModels.size();
    }
}
