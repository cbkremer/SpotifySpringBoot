package com.stefanini.spotify.mapper;


import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicDTOService {
    private final MusicService musicService;
    public MusicDTOService(MusicService musicService){
        this.musicService=musicService;
    }
    public Music mapMusic(MusicDTO musicDTO){
        Music newMusic = new Music(
          null,
                musicDTO.getName(),
                null
        );
        return newMusic;
    }
    public List<MusicDTO> convertAllMusics(){
        List<MusicDTO> allMusicsDTO = new ArrayList<>();
        List<Music> allMusics = musicService.findAllMusics();
        for (Music music:allMusics) {
            allMusicsDTO.add(new MusicDTO(
               music.getName(),
               music.getPlaylists()
            ));
        }
        return allMusicsDTO;
    }

}
