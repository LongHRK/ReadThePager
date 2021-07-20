package com.example.app_readbook;

public class ObjRead {
    private String title,link,image;

    public ObjRead() {
    }

    @Override
    public String toString() {
        return "ObjRead{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ObjRead(String title, String link, String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }
}
