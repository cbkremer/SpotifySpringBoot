package com.stefanini.spotify.dto;

import com.stefanini.spotify.model.Playlist;

import java.util.List;

public class MusicDTO {
    private String name;
    private List<Playlist> playlists;

    public MusicDTO(String name, List<Playlist> playlists){
        this.name=name;
        this.playlists=playlists;
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
}
