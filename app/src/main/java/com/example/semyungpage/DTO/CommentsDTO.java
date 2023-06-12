package com.example.semyungpage.DTO;

public class CommentsDTO {

    private String main;

    private int postid; //_스네이크 케이스 사용 금지

    private int userid;


    //g s tter


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}