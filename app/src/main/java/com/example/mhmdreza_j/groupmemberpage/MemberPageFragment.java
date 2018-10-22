package com.example.mhmdreza_j.groupmemberpage;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhmdreza_j.groupmemberpage.group_member.MemberRepository;
import com.example.mhmdreza_j.groupmemberpage.group_member.MemberAdapter;
import com.example.mhmdreza_j.groupmemberpage.group_member.MemberViewModel;
import com.example.mhmdreza_j.groupmemberpage.library.GroupAdapter;
import com.example.mhmdreza_j.groupmemberpage.listener.DataSetChangeListener;
import com.example.mhmdreza_j.groupmemberpage.listener.LoadMoreGroupMemberListener;
import com.example.mhmdreza_j.groupmemberpage.listener.OptionOnClickListener;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.OptionAdapter;
import com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_model.OptionViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemberPageFragment
        extends Fragment
        implements LoadMoreGroupMemberListener, DataSetChangeListener, OptionOnClickListener {

    private int lastIndexRead = 0;
    private MemberRepository repository = new MemberRepository();
    private ArrayList<MemberViewModel> memberList = new ArrayList<>();
    private ArrayList<OptionViewModel> optionList = new ArrayList<>();
    private MemberAdapter memberAdapter;
    private OptionAdapter optionAdapter;
    private LiveData<List<MemberViewModel>> members;
    private RecyclerView membersRecyclerView;
    private int memberCounterIndex;

    public MemberPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member_page, container, false);
        setHasOptionsMenu(true);
        initializeGroupMember(view);
        initializeOptions(view);
        setDialogInfo(view);
        return view;
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

    private void initializeOptions(View view) {
//        RecyclerView optionRecyclerView = view.findViewById(R.id.optionRecyclerView);
//        optionRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
//        optionRecyclerView.setAdapter(optionAdapter);
    }

    private void initializeGroupMember(View view) {
        membersRecyclerView = view.findViewById(R.id.recycler_view_members);
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        int i = membersRecyclerView.computeVerticalScrollOffset();
        membersRecyclerView.setVerticalScrollbarPosition(i);
        getData();
        memberAdapter = new MemberAdapter(memberList, this, this);
        optionList.add(new OptionViewModel(OptionViewModel.OPTION_TYPE, getString(R.string.add_member), R.drawable.ic_add_member, false));
        optionList.add(new OptionViewModel(OptionViewModel.OPTION_TYPE, getString(R.string.create_link), R.drawable.ic_create_link, true));
        optionList.add(new OptionViewModel(OptionViewModel.VIEW_TYPE, getString(R.string.group_member_number), 0, false));
        memberCounterIndex = optionList.size() - 1;
        optionAdapter = new OptionAdapter(optionList, this);
        GroupAdapter.Builder builder = new GroupAdapter.Builder();
        builder.add(optionAdapter);
        builder.add(memberAdapter);
        GroupAdapter groupAdapter = builder.build();
        membersRecyclerView.setAdapter(groupAdapter);
        setGroupMemberNumber(view);
    }

    private void setDialogInfo(View view) {
        ImageView dialogImageView = view.findViewById(R.id.image_view_dialog_image);
        TextView dialogNameTextView = view.findViewById(R.id.text_view_dialog_name);
        TextView dialogInfoTextView = view.findViewById(R.id.text_view_dialog_info);
        dialogImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGroupImageClicked();
            }
        });
        dialogNameTextView.setText("نام گروه");
        dialogInfoTextView.setText("تعداد اعضای گروه");
        Glide.with(view)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9S1cddQSI1jovslj7jvSg6EFjOsn0d8O6QHsMOxKr3iOHPrrV")
                .apply(new RequestOptions().circleCrop())
                .into(dialogImageView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.group_member_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        AutoCompleteTextView textView = searchView.findViewById(R.id.search_src_text);
        ImageView imageView = searchView.findViewById(R.id.search_close_btn);
        imageView.setImageResource(R.drawable.ic_close);
        imageView.bringToFront();
        textView.setTextColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                repository.getSearchResult(s).observe(getThisFragment(), new Observer<List<MemberViewModel>>() {
                    @Override
                    public void onChanged(@Nullable List<MemberViewModel> memberViewModels) {
                        memberList.clear();
                        memberList.addAll(memberViewModels);
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


    @Override
    public void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                members = repository.getMembers(lastIndexRead, lastIndexRead + 50);
                members.observe(getThisFragment(), new Observer<List<MemberViewModel>>() {
                    @Override
                    public void onChanged(@Nullable List<MemberViewModel> memberViewModels) {
                        if (memberViewModels != null && memberViewModels.size() > 0) {
                            if (repository.isLastQueryInsert()) {
                                memberList.addAll(memberViewModels);
                            }
                            memberAdapter.notifyDataSetChanged();
                        }
                    }
                });
                lastIndexRead += 50;
            }
        }).start();
        Toast.makeText(MyApplication.getContext(), "load " + lastIndexRead + " to " + (lastIndexRead + 50), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void memberStatusChanged(final MemberViewModel memberViewModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repository.updateUserStatus(memberViewModel);
            }
        }).start();
    }

    @Override
    public void removeFromGroup(final MemberViewModel memberViewModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repository.removeFromGroup(memberViewModel);
            }
        }).start();
        memberList.remove(memberViewModel);
    }

    public void setGroupMemberNumber(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repository.getLiveCount().observe(getThisFragment(), new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer integer) {
                        if (integer != null) {
                            optionList.get(memberCounterIndex).setTitle(String.format(getString(R.string.group_member_number), integer));
                            optionAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }).start();
    }

    public Fragment getThisFragment() {
        return this;
    }

    private void onCreateLinkButtonClicked(View view) {
        Toast.makeText(view.getContext(), "onCreateLinkButtonClicked", Toast.LENGTH_SHORT).show();
    }

    private void onAddMemberButtonClicked(View view) {
        Toast.makeText(view.getContext(), "onAddMemberButtonClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOptionClicked(int iconResID, View view) {
        switch (iconResID){
            case R.drawable.ic_add_member:
                onAddMemberButtonClicked(view);
                break;
            case R.drawable.ic_create_link:
                onCreateLinkButtonClicked(view);
        }
    }
}