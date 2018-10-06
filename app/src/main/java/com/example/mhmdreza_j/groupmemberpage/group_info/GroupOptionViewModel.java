package com.example.mhmdreza_j.groupmemberpage.group_info;

public class GroupOptionViewModel {
    public static int OPTION_TYPE = 0;
    public static int TITLE_TYPE = 1;
    private String title;
    private int optionImageResID;
    private int type;


    public GroupOptionViewModel(int type, String title, int optionImageResID) {
        this.title = title;
        this.type = type;
        this.optionImageResID = optionImageResID;
    }

    public String getTitle() {
        return title;
    }

    public int getOptionImageResID() {
        return optionImageResID;
    }

    public int getType() {
        return type;
    }

}
