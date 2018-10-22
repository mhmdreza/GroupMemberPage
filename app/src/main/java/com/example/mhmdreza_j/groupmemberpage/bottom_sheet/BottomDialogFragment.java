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
import com.example.mhmdreza_j.groupmemberpage.group_member.MemberViewModel;
import com.example.mhmdreza_j.groupmemberpage.listener.BottomSheetCloseListener;
import com.example.mhmdreza_j.groupmemberpage.listener.DataSetChangeListener;

@SuppressLint("ValidFragment")
public class BottomDialogFragment extends BottomSheetDialogFragment {

    private MemberViewModel memberViewModel;
    private DataSetChangeListener dataSetChangeListener;
    private BottomSheetCloseListener bottomSheetCloseListener;
    private int chosenItem = -1;

    public BottomDialogFragment(MemberViewModel memberViewModel,
                                DataSetChangeListener dataSetChangeListener,
                                BottomSheetCloseListener bottomSheetCloseListener) {
        this.memberViewModel = memberViewModel;
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
        final int checkedItem = (memberViewModel.isAdmin()) ? 0 : 1;
        setChosenItem(checkedItem);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.changeUserAccess)
                .setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setChosenItem(i);
                    }
                })
                .setPositiveButton(R.string.changeAccessLevel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (getChosenItem()) {
                            case 0:
                                memberViewModel.setAdmin(true);
                                break;
                            case 1:
                                memberViewModel.setAdmin(false);
                        }
                        if (checkedItem != getChosenItem()) {
                            dataSetChangeListener.memberStatusChanged(memberViewModel);
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        builder.create().show();

    }

    private void onRemoveFromGroupClicked() {
        Toast.makeText(getContext(), "onRemoveFromGroupClicked", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setMessage(String.format(getString(R.string.removeMemberMessage), memberViewModel.getName()))
                .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataSetChangeListener.removeFromGroup(memberViewModel);
                    }
                })
                .setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        builder.create().show();
        bottomSheetCloseListener.closeBottomSheet();
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
