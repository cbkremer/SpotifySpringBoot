package com.stefanini.spotify.mapper;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistDTOService {
    private final User_infoService userService;
    private final PlaylistService playlistService;

    public PlaylistDTOService(User_infoService userService, PlaylistService playlistService){
        this.userService = userService;
        this.playlistService = playlistService;
    }

    public List<PlaylistDTO> convertPlaylistsByUserId(Long id)throws User_infoNotFoundException{
        User_info user = userService.findById(id);
        List<Playlist> playlists = playlistService.findAllPlaylists();
        List<PlaylistDTO> playlistsDTO = new ArrayList<>();
        for (Playlist listPlaylist : playlists) {
            if(listPlaylist.getUserInfo().getId() == id){
                playlistsDTO.add(new PlaylistDTO(
                        listPlaylist.getName(),
                        listPlaylist.getQuantity(),
                        user.getName()
                ));
            }
        }
        return playlistsDTO;
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
