package com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_holder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.listener.OptionOnClickListener;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_model.OptionViewModel;

public class OptionViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private View line;
    private OptionOnClickListener optionOnClickListener;

    public OptionViewHolder(View itemView, OptionOnClickListener optionOnClickListener) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.optionTitleTextView);
        line = itemView.findViewById(R.id.horizontal_line);
        this.optionOnClickListener = optionOnClickListener;
    }

    public void onBindViewModel(final OptionViewModel model) {
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionOnClickListener.onOptionClicked(model.getIconResID(), view);
            }
        });
        if (model.getType() == OptionViewModel.OPTION_TYPE) {
            Drawable leftDrawable = itemView.getResources().getDrawable(model.getIconResID());
            titleTextView.setText(model.getTitle());
            titleTextView.setCompoundDrawables(leftDrawable, null, null, null);
            if (model.isLastOptionItem()) {
                line.setVisibility(View.GONE);
            } else {
                line.setVisibility(View.VISIBLE);
            }
        }
    }
}
