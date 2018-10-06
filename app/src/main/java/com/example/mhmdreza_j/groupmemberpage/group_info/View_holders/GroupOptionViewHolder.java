package com.example.mhmdreza_j.groupmemberpage.group_info.View_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.group_info.GroupOptionViewModel;

public class GroupOptionViewHolder extends RecyclerView.ViewHolder {
    private TextView optionTextView;
    private ImageView optionImageView;

    public GroupOptionViewHolder(@NonNull View itemView) {
        super(itemView);
        optionTextView = itemView.findViewById(R.id.text_view_option);
        optionImageView = itemView.findViewById(R.id.image_view_option);
    }

    public void onBindViewHolder(GroupOptionViewModel model){
        optionTextView.setText(model.getTitle());
        optionImageView.setBackgroundResource(model.getOptionImageResID());
    }

}
