package com.example.mhmdreza_j.groupmemberpage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhmdreza_j.groupmemberpage.group_info.GroupOptionAdapter;
import com.example.mhmdreza_j.groupmemberpage.group_info.GroupOptionViewModel;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberAdapter;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupMemberPageFragment extends Fragment {

    public GroupMemberPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_member_page, container, false);
        initializeGroupMember(view);
        setGroupInfo(view);
        return view;
    }

    private void setGroupInfo(View view) {
        ImageView groupImageView = view.findViewById(R.id.image_view_group_image);
        TextView groupNameTextView = view.findViewById(R.id.text_view_group_name);
        TextView groupMemberNumberTextView = view.findViewById(R.id.text_view_group_member_number);
        groupNameTextView.setText("نام گروه");
        groupMemberNumberTextView.setText("تعداد اعضای گروه");
        Glide.with(view)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9S1cddQSI1jovslj7jvSg6EFjOsn0d8O6QHsMOxKr3iOHPrrV")
                .apply(new RequestOptions().circleCrop())
                .into(groupImageView);
    }

    private void initializeGroupMember(View view) {
        RecyclerView groupMemberRecyclerView = view.findViewById(R.id.recycler_view_group_member);
        ArrayList<GroupMemberViewModel> groupMemberList = getGroupMemberList();
        GroupMemberAdapter memberAdapter = new GroupMemberAdapter(groupMemberList);
        groupMemberRecyclerView.setAdapter(memberAdapter);

        RecyclerView groupInfoRecyclerView = view.findViewById(R.id.recycler_view_group_option);
        ArrayList<GroupOptionViewModel> groupOptionViewModels = new ArrayList<>();
//        groupOptionViewModels.add(new GroupOptionViewModel(getString(R.string.create_link), R.drawable.ic_create_link));
        groupOptionViewModels.add(new GroupOptionViewModel(GroupOptionViewModel.OPTION_TYPE
                , getString(R.string.add_member)
                , R.drawable.ic_add_member));
        groupOptionViewModels.add(new GroupOptionViewModel(GroupOptionViewModel.OPTION_TYPE
                , getString(R.string.create_link)
                , R.drawable.ic_create_link));
        groupOptionViewModels.add(new GroupOptionViewModel(GroupOptionViewModel.TITLE_TYPE
                , "تعداد اعضا"
                , 0));
        GroupOptionAdapter optionAdapter = new GroupOptionAdapter(groupOptionViewModels);
        groupInfoRecyclerView.setAdapter(optionAdapter);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = view.findViewById(R.id.toolbar_group_member_page);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_group_member_page, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public ArrayList<GroupMemberViewModel> getGroupMemberList() {
        ArrayList<GroupMemberViewModel> groupMemberList = new ArrayList<>();
        ArrayList<MediaViewModel> mediaArrayList = new ArrayList<>();
        mediaArrayList.add(new MediaViewModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9S1cddQSI1jovslj7jvSg6EFjOsn0d8O6QHsMOxKr3iOHPrrV"));
        mediaArrayList.add(new MediaViewModel("https://www.skymetweather.com//themes/skymet/images/satellite/insat/thumb-web.jpg?x=1524377804"));
        mediaArrayList.add(new MediaViewModel("https://www.zimbabweflora.co.zw/speciesdata/images/11/112960-2.jpg"));
        mediaArrayList.add(new MediaViewModel("https://www.expertafrica.com/images/area/868_l.jpg"));
        mediaArrayList.add(new MediaViewModel("https://www.malawi-music.com/images/albums/pangono.jpg"));
        mediaArrayList.add(new MediaViewModel("http://www.diveworldwide.com/images/products/shark_diving_species_whale_shark.jpg"));
        mediaArrayList.add(new MediaViewModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHWsnPuWFJJglzJOCdqz4IB-LfkPlad79rE_A8NuhRVWpL76bC"));
        mediaArrayList.add(new MediaViewModel("https://image-cdn.neatoshop.com/styleimg/67183/none/kiwigreen/default/371936-19;1512965831i.jpg"));
        mediaArrayList.add(new MediaViewModel("https://dqgroc0ic5iei.cloudfront.net/images/GoestaReiland_DSC01125-CMSTemplate.2e16d0ba.fill-400x400_6Wso0qQ.jpg"));
        groupMemberList.add(new GroupMemberViewModel("ali", "online", mediaArrayList.get(0), true));
        groupMemberList.add(new GroupMemberViewModel("ali zdmdgfxcv", "7 minutes ago", mediaArrayList.get(1), false));
        groupMemberList.add(new GroupMemberViewModel("ali sdt", "today 17:45", mediaArrayList.get(2), false));
        groupMemberList.add(new GroupMemberViewModel("alixvfb f ", "yesterday 12:33", mediaArrayList.get(3), true));
        groupMemberList.add(new GroupMemberViewModel("ali zvdglrxclv", "2018/11/11 11:11", mediaArrayList.get(4), false));
        groupMemberList.add(new GroupMemberViewModel("ali xvc fbdb", "online", mediaArrayList.get(5), false));
        groupMemberList.add(new GroupMemberViewModel("ali rogl; fobk;fldb, f", "10 minasdfg", mediaArrayList.get(6), false));
        groupMemberList.add(new GroupMemberViewModel("ali dzsgr dfg;l, ldf", "online", mediaArrayList.get(7), true));
        groupMemberList.add(new GroupMemberViewModel("ali dfmgdf l;bfd", "online", mediaArrayList.get(8), false));
        return groupMemberList;
    }


}
