package com.example.mhmdreza_j.groupmemberpage.options_recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.listener.OptionOnClickListener;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_holder.OptionViewHolder;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_holder.TitleViewHolder;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_model.OptionViewModel;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class OptionAdapter extends RecyclerView.Adapter {

    private ArrayList<OptionViewModel> dataSet;
    private OptionOnClickListener optionOnClickListener;

    public OptionAdapter(ArrayList<OptionViewModel> data, OptionOnClickListener optionOnClickListener) {
        this.dataSet = data;
        this.optionOnClickListener = optionOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case OptionViewModel.OPTION_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_option_layout, parent, false);
                return new OptionViewHolder(view, optionOnClickListener);
            case OptionViewModel.VIEW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_counter_layout, parent, false);
                return new TitleViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).getType()) {
            case 0:
                return OptionViewModel.OPTION_TYPE;
            case 1:
                return OptionViewModel.VIEW_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        OptionViewModel object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.getType()) {
                case OptionViewModel.OPTION_TYPE:
                    ((OptionViewHolder) holder).onBindViewModel(object);

                    break;
                case OptionViewModel.VIEW_TYPE:
                    ((TitleViewHolder) holder).onBindViewModel(object);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
