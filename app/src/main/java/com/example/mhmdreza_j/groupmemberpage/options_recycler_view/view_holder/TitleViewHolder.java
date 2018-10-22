package com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_model.OptionViewModel;

public class TitleViewHolder extends RecyclerView.ViewHolder {
    private TextView memberCounterTextView;

    public TitleViewHolder(View itemView) {
        super(itemView);
        this.memberCounterTextView = itemView.findViewById(R.id.memberCounterTextView);
    }

    public void onBindViewModel(OptionViewModel model){
        memberCounterTextView.setText(model.getTitle());
    }
}
