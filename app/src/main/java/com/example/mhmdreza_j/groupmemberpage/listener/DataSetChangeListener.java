package com.example.mhmdreza_j.groupmemberpage.listener;

import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberViewModel;

public interface DataSetChangeListener {
    void memberStatusChanged(GroupMemberViewModel groupMemberViewModel);

    void removeFromGroup(GroupMemberViewModel groupMemberViewModel);
}
