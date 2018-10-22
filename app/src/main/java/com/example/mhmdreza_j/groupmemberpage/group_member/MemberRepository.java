package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.app.Activity;
import android.arch.lifecycle.LiveData;

import com.example.mhmdreza_j.groupmemberpage.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private GroupMemberDAO groupMemberDAO;
    private boolean isLastQueryInsert = true;

    public MemberRepository() {
        final GroupMemberDatabase database = GroupMemberDatabase.getInstance(MyApplication.getContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                groupMemberDAO = database.groupMemberDAO();
                if (groupMemberDAO.getCount() == 0) {
                    ArrayList<MemberViewModel> memberList = new ArrayList<>();
                    for (int i = 0; i < 300; i++) {
                        switch (i % 3) {
                            case 0:
                                memberList.add(new MemberViewModel("ali " + i, "online", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9S1cddQSI1jovslj7jvSg6EFjOsn0d8O6QHsMOxKr3iOHPrrV", true));
                                break;
                            case 1:
                                memberList.add(new MemberViewModel("mohammadrezaaa " + i, "7 minutes ago", "https://www.skymetweather.com//themes/skymet/images/satellite/insat/thumb-web.jpg?x=1524377804", false));
                                break;
                            case 2:
                            default:
                                memberList.add(new MemberViewModel("seyed ali alavi " + i, "today 17:45", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuGU91BKoHbBa9bry8Go-TTW9t263vcG9aqzaIwrBfNtWanOeq9Q", false));
                        }
                    }
                    groupMemberDAO.insertAll(memberList);
                }
            }
        }).start();
    }

    public LiveData<List<MemberViewModel>> getMembers(final int fromIndex, final int toIndex) {
        isLastQueryInsert = true;
        return groupMemberDAO.getMembers(fromIndex, toIndex);
    }

    public void removeFromGroup(final MemberViewModel model) {
        isLastQueryInsert = false;
        groupMemberDAO.deleteMember(model);
    }

    public void updateUserStatus(final MemberViewModel model) {
        isLastQueryInsert = false;
        groupMemberDAO.updateUserStatus(model);
    }

    public LiveData<List<MemberViewModel>> getSearchResult(String searchWord) {
        isLastQueryInsert = false;
        return groupMemberDAO.getSearchResult(searchWord);
    }

    public boolean isLastQueryInsert() {
        return isLastQueryInsert;
    }

    public LiveData<Integer> getLiveCount(){
        return groupMemberDAO.getLiveCount();
    }
}