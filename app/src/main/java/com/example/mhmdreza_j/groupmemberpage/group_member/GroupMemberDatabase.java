package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {GroupMemberViewModel.class}, version = 1, exportSchema = false)
public abstract class GroupMemberDatabase extends RoomDatabase {
    private static GroupMemberDatabase INSTANCE;
    public abstract GroupMemberDAO groupMemberDAO();

    public static GroupMemberDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, GroupMemberDatabase.class, "groupMemberDB")
                    .build();
        }
        return INSTANCE;
    }


}
