package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "GroupMember")
public class GroupMemberViewModel{

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "lastSeen")
    private String lastSeen;

    @ColumnInfo(name = "profile")
    private String profilePictureLink;

    @ColumnInfo(name = "isAdmin")
    private boolean isAdmin;

    public GroupMemberViewModel() {
    }

    @Ignore
    public GroupMemberViewModel(String name, String lastSeen, String profilePictureLink, boolean isAdmin) {
        this.name = name;
        this.lastSeen = lastSeen;
        this.profilePictureLink = profilePictureLink;
        this.isAdmin = isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
