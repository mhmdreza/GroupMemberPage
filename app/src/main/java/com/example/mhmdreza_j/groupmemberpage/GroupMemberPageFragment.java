package com.example.mhmdreza_j.groupmemberpage;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberAdapter;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberDatabase;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberRepository;
import com.example.mhmdreza_j.groupmemberpage.group_member.GroupMemberViewModel;
import com.example.mhmdreza_j.groupmemberpage.listener.DataSetChangeListener;
import com.example.mhmdreza_j.groupmemberpage.listener.LoadMoreGroupMemberListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupMemberPageFragment
        extends Fragment
        implements LoadMoreGroupMemberListener, DataSetChangeListener {

    private int lastIndexRead = 0;
    private GroupMemberRepository repository;
    private Fragment fragment = this; //bad practice :))
    private GroupMemberViewModel changedGroupMemberViewModel; //bad practice :))
    private ArrayList<GroupMemberViewModel> groupMemberList = new ArrayList<>();
    GroupMemberAdapter memberAdapter;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = view.findViewById(R.id.toolbar_group_member_page);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    private void setGroupInfo(View view) {
        TextView addMemberTextView = view.findViewById(R.id.text_view_add_member);
        addMemberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddMemberButtonClicked();
            }
        });
        TextView createLinkTextView = view.findViewById(R.id.text_view_create_link);
        createLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateLinkButtonClicked();
            }
        });
        ImageView groupImageView = view.findViewById(R.id.image_view_group_image);
        TextView groupNameTextView = view.findViewById(R.id.text_view_group_name);
        TextView groupMemberNumberTextView = view.findViewById(R.id.text_view_group_member_number);
        groupImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGroupImageClicked();
            }
        });
        groupNameTextView.setText("نام گروه");
        groupMemberNumberTextView.setText("تعداد اعضای گروه");
        Glide.with(view)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9S1cddQSI1jovslj7jvSg6EFjOsn0d8O6QHsMOxKr3iOHPrrV")
                .apply(new RequestOptions().circleCrop())
                .into(groupImageView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.group_member_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                repository.getSearchResult(s).observe(fragment, new Observer<List<GroupMemberViewModel>>() {
                    @Override
                    public void onChanged(@Nullable List<GroupMemberViewModel> groupMemberViewModels) {
                        groupMemberList.clear();
                        groupMemberList.addAll(groupMemberViewModels);
                        memberAdapter.notifyDataSetChanged();
                    }
                });
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSearchButtonClicked() {
        Toast.makeText(getContext(), "onSearchButtonClicked", Toast.LENGTH_SHORT).show();
    }

    private void onGroupImageClicked() {
        Toast.makeText(getContext(), "onGroupImageClicked", Toast.LENGTH_SHORT).show();
    }

    private void onCreateLinkButtonClicked() {
        Toast.makeText(this.getContext(), "onCreateLinkButtonClicked", Toast.LENGTH_SHORT).show();
    }

    private void onAddMemberButtonClicked() {
        Toast.makeText(this.getContext(), "onAddMemberButtonClicked", Toast.LENGTH_SHORT).show();
    }

    private void initializeGroupMember(View view) {
        RecyclerView groupMemberRecyclerView = view.findViewById(R.id.recycler_view_group_member);
        memberAdapter = new GroupMemberAdapter(groupMemberList, this, this);
        getData();
        groupMemberRecyclerView.setAdapter(memberAdapter);
    }

    @Override
    public void getData() {
        repository = new GroupMemberRepository();
        LiveData<List<GroupMemberViewModel>> members = repository.getMembers(lastIndexRead, lastIndexRead + 50);
        members.observe(this, new Observer<List<GroupMemberViewModel>>() {
            @Override
            public void onChanged(@Nullable List<GroupMemberViewModel> groupMemberViewModels) {
                if (groupMemberViewModels != null && groupMemberViewModels.size() > 0) {
                    if (repository.isLastQueryInsert()){
                        groupMemberList.addAll(groupMemberViewModels);
                    }
                    memberAdapter.notifyDataSetChanged();
                }
            }
        });
        Toast.makeText(MyApplication.getContext(), "load " + lastIndexRead + " to " + (lastIndexRead + 50), Toast.LENGTH_SHORT).show();
        lastIndexRead += 50;
    }


    @Override
    public void memberStatusChanged(GroupMemberViewModel groupMemberViewModel) {
        changedGroupMemberViewModel = groupMemberViewModel;
        repository.updateUserStatus(groupMemberViewModel);

    }

    @Override
    public void removeFromGroup(GroupMemberViewModel groupMemberViewModel) {
        changedGroupMemberViewModel = groupMemberViewModel;
        repository.removeFromGroup(groupMemberViewModel);
        groupMemberList.remove(groupMemberViewModel);
    }
}
