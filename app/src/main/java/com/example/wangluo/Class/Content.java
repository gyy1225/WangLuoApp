package com.example.wangluo.Class;

/**
 * Created by ASUS on 2018/7/4.
 */

public class Content {
    private String id;
    private String title;
    private String author;
    private String content;
    private int imageID;
    private String contentURL;
    private String resource;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }
}
