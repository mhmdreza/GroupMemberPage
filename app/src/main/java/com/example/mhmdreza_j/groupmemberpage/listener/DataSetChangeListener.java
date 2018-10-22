package com.example.mhmdreza_j.groupmemberpage.listener;

import com.example.mhmdreza_j.groupmemberpage.group_member.MemberViewModel;

public interface DataSetChangeListener {
    void memberStatusChanged(MemberViewModel memberViewModel);

    void removeFromGroup(MemberViewModel memberViewModel);
}
