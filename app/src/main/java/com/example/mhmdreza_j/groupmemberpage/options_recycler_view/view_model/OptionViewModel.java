package com.example.mhmdreza_j.groupmemberpage.options_recycler_view.view_model;

public class OptionViewModel {
    public static final int OPTION_TYPE = 0;
    public static final int VIEW_TYPE = 1;

    private int type;
    private String title;
    private int iconResID;
    private boolean isLastOptionItem;


    public boolean isLastOptionItem() {
        return isLastOptionItem;
    }

    public void setLastOptionItem(boolean lastOptionItem) {
        isLastOptionItem = lastOptionItem;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconResID() {
        return iconResID;
    }

    public void setIconResID(int iconResID) {
        this.iconResID = iconResID;
    }

    public OptionViewModel(int type, String title, int iconResID, boolean isLastOptionItem) {

        this.type = type;
        this.title = title;
        this.iconResID = iconResID;
        this.isLastOptionItem = isLastOptionItem;
    }
}
