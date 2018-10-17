package com.example.mhmdreza_j.groupmemberpage.bottom_sheet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mhmdreza_j.groupmemberpage.R;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberViewModel;
import com.example.mhmdreza_j.groupmemberpage.listener.BottomSheetCloseListener;
import com.example.mhmdreza_j.groupmemberpage.listener.DataSetChangeListener;

@SuppressLint("ValidFragment")
public class BottomDialogFragment extends BottomSheetDialogFragment {

    private GroupMemberViewModel groupMemberViewModel;
    private DataSetChangeListener dataSetChangeListener;
    private BottomSheetCloseListener bottomSheetCloseListener;
    private int chosenItem = -1;

    public BottomDialogFragment(GroupMemberViewModel groupMemberViewModel,
                                DataSetChangeListener dataSetChangeListener,
                                BottomSheetCloseListener bottomSheetCloseListener) {
        this.groupMemberViewModel = groupMemberViewModel;
        this.dataSetChangeListener = dataSetChangeListener;
        this.bottomSheetCloseListener = bottomSheetCloseListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.custom_bottom_sheet, container, false);
        view.findViewById(R.id.text_view_group_remove_from_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRemoveFromGroupClicked();
                bottomSheetCloseListener.closeBottomSheet();
            }
        });

        view.findViewById(R.id.text_view_change_user_access).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeUserAccessClicked();
                bottomSheetCloseListener.closeBottomSheet();
            }
        });
        return view;
    }

    private void onChangeUserAccessClicked() {
        Toast.makeText(getContext(), "onChangeUserAccessClicked", Toast.LENGTH_SHORT).show();
        String[] items = {"admin", "group user"};
        final int checkedItem = (groupMemberViewModel.isAdmin()) ? 0 : 1;
        setChosenItem(checkedItem);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select user access level")
                .setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setChosenItem(i);
                    }
                })
                .setPositiveButton("CHANGE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (getChosenItem()) {
                            case 0:
                                Toast.makeText(getContext(), "admin", Toast.LENGTH_SHORT).show();
                                groupMemberViewModel.setAdmin(true);
                                break;
                            case 1:
                                Toast.makeText(getContext(), "user", Toast.LENGTH_SHORT).show();
                                groupMemberViewModel.setAdmin(false);
                        }
                        if (checkedItem != getChosenItem()) {
                            dataSetChangeListener.memberStatusChanged(groupMemberViewModel);
                        }

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "onCancelClicked", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();

    }

    private void onRemoveFromGroupClicked() {
        Toast.makeText(getContext(), "onRemoveFromGroupClicked", Toast.LENGTH_SHORT).show();
        dataSetChangeListener.removeFromGroup(groupMemberViewModel);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                FrameLayout bottomSheet = dialog.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior behavior;
                if (bottomSheet != null) {
                    behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    behavior.setPeekHeight(0);
                }
            }
        });
    }

    public void setChosenItem(int chosenItem) {
        this.chosenItem = chosenItem;
    }

    public int getChosenItem() {
        return chosenItem;
    }
}
