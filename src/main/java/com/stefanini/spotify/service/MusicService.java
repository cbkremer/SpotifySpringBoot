package com.stefanini.spotify.service;

import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.exception.MusicNotFoundException;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.repository.MusicRepository;
import org.springframework.stereotype.Service;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.repository.PlaylistRepository;

import java.util.List;

@Service
public class MusicService {
    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository){
        this.musicRepository=musicRepository;
    }

    public List<Music> findAllMusics(){
        return musicRepository.findAll();
    }
    public Music findById(Long id)throws MusicNotFoundException {
        return musicRepository.findById(id).
                orElseThrow(() -> new MusicNotFoundException(id));
    }
    public Music save(Music music){
        return musicRepository.save(music);
    }
    public void delete(Music music){
        musicRepository.delete(music);
    }
    public Music findByName(String name)throws MusicNotFoundException{
        return musicRepository.findByName(name);
    }
}
