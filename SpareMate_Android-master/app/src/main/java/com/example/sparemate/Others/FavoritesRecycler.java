package com.example.sparemate.Others;

public class FavoritesRecycler {

    private String title;
    private int imgid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public FavoritesRecycler(String title, int imgid) {
        this.title = title;
        this.imgid = imgid;
    }
}

