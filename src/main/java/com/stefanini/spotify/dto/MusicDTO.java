package com.stefanini.spotify.dto;

import com.stefanini.spotify.model.Playlist;

import java.util.List;

public class MusicDTO {
    private String name;
    private List<PlaylistDTO> playlistsDTO;
    private int tag;

    public MusicDTO(String name, List<PlaylistDTO> playlistsDTO, int tag){
        this.name=name;
        this.playlistsDTO=playlistsDTO;
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

    public void setPlaylists(List<PlaylistDTO> playlistsDTO) {
        this.playlistsDTO = playlistsDTO;
    }

    public List<PlaylistDTO> getPlaylistsDTO() {
        return playlistsDTO;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
