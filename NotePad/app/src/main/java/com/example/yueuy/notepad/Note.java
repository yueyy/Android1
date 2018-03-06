package com.example.yueuy.notepad;

/**
 * Created by yueuy on 17-11-28.
 */

public class Note {

    private String title;
    private String content;
    private int date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public  String toString(){
        return title + " " + content +" made on "+date;
    }
    public Note(String title, String content, int date){
     //   super();
        this.title = title;
        this.content = content;
        this.date = date;
    }
    public Note(){

    }
    public Note(String title,String content){
//        super();
        this.title = title;
        this.content = content;
    }

}
