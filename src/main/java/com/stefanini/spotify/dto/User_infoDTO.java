package com.stefanini.spotify.dto;

import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;

import java.util.List;

public class User_infoDTO {
    private String name;
    private String email;
    private String password;
    private List<PlaylistDTO> playlistsDTO;

    public User_infoDTO(String name, String email, String password, List<PlaylistDTO> playlistsDTO){
        this.name = name;
        this.email = email;
        this.password = password;
        this.playlistsDTO = playlistsDTO;
    }

    public User_infoDTO() {

    }

    public User_infoDTO(User_info user_info) {
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setPlaylistsDTO(List<PlaylistDTO> playlistsDTO) {
        this.playlistsDTO = playlistsDTO;
    }

    public List<PlaylistDTO> getPlaylistsDTO() {
        return playlistsDTO;
    }
}
