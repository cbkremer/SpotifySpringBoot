package com.stefanini.spotify.service;

import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }
    public List<Playlist> findAllPlaylists(){
        return playlistRepository.findAll();
    }
    public Playlist findByName(String name)throws PlaylistNotFoundException{
        return playlistRepository.findByName(name);
    }
    public Playlist findById(Long id) throws PlaylistNotFoundException{
        return playlistRepository.findById(id).
                orElseThrow(() -> new PlaylistNotFoundException(id));
    }
    public Playlist findByTag(int tag)throws PlaylistNotFoundException{
        return playlistRepository.findByTag(tag);
    }
    public Playlist save(Playlist playlist){
        return playlistRepository.save(playlist);
    }
    public void delete(Playlist playlist){
        playlistRepository.delete(playlist);
    }
    public User_info getUser_info(Playlist playlist){
        return playlist.getUserInfo();
    }
}
