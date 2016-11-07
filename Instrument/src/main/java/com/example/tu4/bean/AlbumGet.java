package com.example.tu4.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by byyoung:专辑图片 on 2016/11/1.
 */
public class AlbumGet implements Serializable{
    private ArrayList<AlbumPicEntity> albums;

    public ArrayList<AlbumPicEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<AlbumPicEntity> albums) {
        this.albums = albums;
    }

    public static class AlbumPicEntity{
        private String album_url;

        public String getAlbum_url() {
            return album_url;
        }

        public void setAlbum_url(String album_url) {
            this.album_url = album_url;
        }
    }
}
