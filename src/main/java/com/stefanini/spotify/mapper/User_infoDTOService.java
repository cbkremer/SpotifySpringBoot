package com.stefanini.spotify.mapper;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.dto.User_infoDTO;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.MusicService;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class User_infoDTOService {
    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;
    private final MusicService musicService;
    private final MusicDTOService musicDTOService;


    @Autowired
    public User_infoDTOService(PlaylistService playlistService,PlaylistDTOService playlistDTOService,MusicDTOService musicDTOService,MusicService musicService){
        this.playlistService = playlistService;
        this.playlistDTOService = playlistDTOService;
        this.musicService=musicService;
        this.musicDTOService=musicDTOService;
    }
    public static List<User_infoDTO> convertList(List<User_info> users, PlaylistDTOService playlistDTOService,MusicDTOService musicDTOService)throws User_infoNotFoundException {

        List<User_infoDTO> allUsers = new ArrayList<>();
        for (User_info user : users) {
            allUsers.add(new User_infoDTO(
                    user.getName(),
                    user.getEmail(),
                    null,
                    playlistDTOService.convertPlaylists(user.getPlaylists(),musicDTOService)
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
    public static User_info mapUser(User_infoDTO user){
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
