package com.stefanini.spotify.dto;

import com.stefanini.spotify.model.Playlist;

import java.util.List;

public class MusicDTO {
    private String name;
    private List<Playlist> playlists;
    private int tag;

    public MusicDTO(String name, List<Playlist> playlists, int tag){
        this.name=name;
        this.playlists=playlists;
        this.tag=tag;
    }

    public MusicDTO(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
