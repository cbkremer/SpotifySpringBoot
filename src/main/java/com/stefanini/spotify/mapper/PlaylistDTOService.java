package com.stefanini.spotify.mapper;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.stereotype.Service;

@Service
public class PlaylistDTOService {
    private final User_infoService userService;

    public PlaylistDTOService(User_infoService userService){
        this.userService = userService;
    }

    public Playlist mapPlaylist(PlaylistDTO playlist,Long id)throws User_infoNotFoundException {
        User_info userInfo;
        Playlist newPlaylist = new Playlist(
                                        null,
                                        playlist.getName(),
                                        playlist.getQuantity(),
                userInfo = userService.findById(id)
        );
        return newPlaylist;
    }
}
