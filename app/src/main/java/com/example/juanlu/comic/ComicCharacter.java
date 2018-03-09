package com.example.juanlu.comic;

import java.io.Serializable;

/**
 * Created by juanlu on 8/03/18.
 */

public class ComicCharacter implements Serializable{

    private static final long serialVersionUID = 6963798315818375812L;

    private String id;
    private String title;
    private String description;
    private String thumbnail;

    public ComicCharacter(String id, String mTitle, String description, String thumbnail) {
        this.title = mTitle;
        this.description = description;
        this.thumbnail = thumbnail;
        this.id = id;
    }

    public String getmTitle() {
        return title;
    }

    public void setmTitle(String mTitle) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ComicCharacter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
