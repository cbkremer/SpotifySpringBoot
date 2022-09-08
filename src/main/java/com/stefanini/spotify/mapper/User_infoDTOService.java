package com.stefanini.spotify.mapper;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.dto.User_infoDTO;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class User_infoDTOService {
    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;


    @Autowired
    public User_infoDTOService(PlaylistService playlistService,PlaylistDTOService playlistDTOService){
        this.playlistService = playlistService;
        this.playlistDTOService = playlistDTOService;
    }
    public static List<User_infoDTO> convertList(List<User_info> users,List<Playlist> playlists)throws User_infoNotFoundException {
        List<User_infoDTO> allUsers = new ArrayList<>();

        for (User_info user : users) {
            List<PlaylistDTO> userPlaylists = new ArrayList<>();
            for (Playlist playlist: playlists) {
                if(playlist.getUserInfo().getId()==user.getId()){
                    userPlaylists.add(new PlaylistDTO(
                            playlist.getName(),
                            playlist.getQuantity(),
                            playlist.getUserInfo().getName(),
                            playlist.getTag(),
                            null
                    ));
                }
            }
            allUsers.add(new User_infoDTO(
                    user.getName(),
                    user.getEmail(),
                    null,
                    userPlaylists
            ));
        }
        return allUsers;
    }
    public static User_infoDTO convertUser(User_info user,List<PlaylistDTO> playlistsDTO){
        User_infoDTO newUser_infoDTO = new User_infoDTO(
          user.getName(),
          user.getEmail(),
                null,
                playlistsDTO
        );
        return newUser_infoDTO;
    }
    public User_info mapUser(User_infoDTO user){
        //Playlist playlist;
        User_info newUserInfo =  new User_info(
                null,
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        return newUserInfo;
    }
}
