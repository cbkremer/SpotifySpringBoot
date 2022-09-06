package com.stefanini.spotify.mapper;


import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.model.Music;
import org.springframework.stereotype.Service;

@Service
public class MusicDTOService {
    public Music mapMusic(MusicDTO musicDTO){
        Music newMusic = new Music(
          null,
                musicDTO.getName(),
                null
        );
        return newMusic;
    }
}
