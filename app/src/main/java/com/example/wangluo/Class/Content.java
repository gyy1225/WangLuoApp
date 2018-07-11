package com.example.wangluo.Class;

import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/7/4.
 */

public class Content extends LitePalSupport implements Serializable{
    private String grade;
    private String title;
    private String author;
    private String content;
    private String imageID;
    private String contentURL;
    private String resource;
    private String titleURL;
    private int imageID2;
    private String UID;
    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getImageID2() {
        return imageID2;
    }

    public void setImageID2(int imageID2) {
        this.imageID2 = imageID2;
    }

    public String getTitleURL() {
        return titleURL;
    }

    public void setTitleURL(String titleURL) {
        this.titleURL = titleURL;
    }

    public String getWebres() {
        return webres;
    }

    public void setWebres(String webres) {
        this.webres = webres;
    }

    private String webres;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }
}
