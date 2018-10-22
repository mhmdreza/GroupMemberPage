package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface GroupMemberDAO {

    @Query("select * from groupMember where ID <= :maxID and :minID < ID")
    LiveData<List<MemberViewModel>> getMembers(int minID, int maxID);

    @Insert(onConflict = REPLACE)
    void insertAll(List<MemberViewModel> groupMembers);

    @Query("select count(*) from GroupMember")
    int getCount();

    @Query("select * from groupmember where name like '%'||:searchWord||'%'")
    LiveData<List<MemberViewModel>> getSearchResult(String searchWord);

    @Delete
    void deleteMember(MemberViewModel model);

    @Update
    void updateUserStatus(MemberViewModel memberViewModel);

    @Query("select count(*) from GroupMember")
    LiveData<Integer> getLiveCount();
}
