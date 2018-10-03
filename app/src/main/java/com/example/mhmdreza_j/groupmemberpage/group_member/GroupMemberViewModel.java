package com.example.mhmdreza_j.groupmemberpage.group_member;


import com.example.mhmdreza_j.groupmemberpage.MediaViewModel;

public class GroupMemberViewModel {
    private String name;
    private String lastSeen;
    private MediaViewModel profilePicture;
    private boolean isAdmin;

    public GroupMemberViewModel(String name, String lastSeen, MediaViewModel profilePicture, boolean isAdmin) {
        this.name = name;
        this.lastSeen = lastSeen;
        this.profilePicture = profilePicture;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public MediaViewModel getProfilePicture() {
        return profilePicture;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
