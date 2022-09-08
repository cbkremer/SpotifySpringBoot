package com.stefanini.spotify.mapper;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlaylistDTOService {
    private final User_infoService userService;
    private final PlaylistService playlistService;

    public PlaylistDTOService(User_infoService userService, PlaylistService playlistService){
        this.userService = userService;
        this.playlistService = playlistService;
    }

    public List<PlaylistDTO> convertAllPlaylists(){
        List<PlaylistDTO> allPlaylistsDTO = new ArrayList<>();
        List<Playlist> allPlaylists = playlistService.findAllPlaylists();
        for (Playlist playlist: allPlaylists) {
            allPlaylistsDTO.add(new PlaylistDTO(
                    playlist.getName(),
                    playlist.getQuantity(),
                    playlist.getUserInfo().getName(),
                    playlist.getTag(),
                    null
            ));
        }
        return allPlaylistsDTO;
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
                        user.getName(),
                        listPlaylist.getTag(),
                        listPlaylist.getMusics()
                ));
            }
        }
        return playlistsDTO;
    }
    public Playlist mapPlaylist(PlaylistDTO playlist,Long id)throws User_infoNotFoundException,PlaylistNotFoundException {
        User_info userInfo;
        Playlist newPlaylist = new Playlist(
                                        null,
                                        playlist.getName(),
                                        playlist.getQuantity(),
                userInfo = userService.findById(id),
                generateTag(),
                null
        );
        return newPlaylist;
    }
    private int generateTag()throws PlaylistNotFoundException {
        Random rand = new Random();
        int tag;
        for (;;){
            tag = rand.nextInt();
            if(playlistService.findByTag(tag)==null)
                break;
        }

        return Math.abs(tag);
    }
}
