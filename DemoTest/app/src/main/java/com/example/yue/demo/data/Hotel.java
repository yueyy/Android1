package com.example.yue.demo.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yue on 2018/3/1.
 */

public class Hotel extends RealmObject{
    private String name;
    private String intro;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
