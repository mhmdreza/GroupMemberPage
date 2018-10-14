package com.example.mhmdreza_j.groupmemberpage.group_member;

import android.arch.lifecycle.LiveData;

import com.example.mhmdreza_j.groupmemberpage.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class GroupMemberRepository {
    private GroupMemberDAO groupMemberDAO;
    private boolean isLastQueryInsert = true;

    public GroupMemberRepository() {
        final GroupMemberDatabase database = GroupMemberDatabase.getInstance(MyApplication.getContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                groupMemberDAO = database.groupMemberDAO();
                if (groupMemberDAO.getCount() == 0) {
                    ArrayList<GroupMemberViewModel> groupMemberList = new ArrayList<>();
                    for (int i = 0; i < 300; i++) {
                        switch (i % 3) {
                            case 0:
                                groupMemberList.add(new GroupMemberViewModel("ali " + i, "online", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9S1cddQSI1jovslj7jvSg6EFjOsn0d8O6QHsMOxKr3iOHPrrV", true));
                                break;
                            case 1:
                                groupMemberList.add(new GroupMemberViewModel("mohammadrezaaa " + i, "7 minutes ago", "https://www.skymetweather.com//themes/skymet/images/satellite/insat/thumb-web.jpg?x=1524377804", false));
                                break;
                            case 2:
                            default:
                                groupMemberList.add(new GroupMemberViewModel("seyed ali alavi " + i, "today 17:45", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuGU91BKoHbBa9bry8Go-TTW9t263vcG9aqzaIwrBfNtWanOeq9Q", false));
                        }
                    }
                    groupMemberDAO.insertAll(groupMemberList);
                }
            }
        }).start();
    }

    public LiveData<List<GroupMemberViewModel>> getMembers(final int fromIndex, final int toIndex) {
        isLastQueryInsert = true;
        return groupMemberDAO.getMembers(fromIndex, toIndex);
    }

    public void removeFromGroup(final GroupMemberViewModel model) {
        isLastQueryInsert = false;
        groupMemberDAO.deleteMember(model);
    }

    public void updateUserStatus(final GroupMemberViewModel model) {
        isLastQueryInsert = false;
        groupMemberDAO.updateUserStatus(model);
    }

    public LiveData<List<GroupMemberViewModel>> getSearchResult(String searchWord) {
        isLastQueryInsert = false;
        return groupMemberDAO.getSearchResult(searchWord);
    }

    public boolean isLastQueryInsert() {
        return isLastQueryInsert;
    }
}