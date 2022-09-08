package com.stefanini.spotify.mapper;


import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.exception.MusicNotFoundException;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MusicDTOService {
    private final MusicService musicService;
    public MusicDTOService(MusicService musicService){
        this.musicService=musicService;
    }
    public Music mapMusic(MusicDTO musicDTO)throws MusicNotFoundException{
        Music newMusic = new Music(
          null,
                musicDTO.getName(),
                null,
                generateTag()
        );
        return newMusic;
    }
    public List<MusicDTO> convertAllMusics()throws MusicNotFoundException{
        List<MusicDTO> allMusicsDTO = new ArrayList<>();
        List<Music> allMusics = musicService.findAllMusics();
        for (Music music:allMusics) {
            allMusicsDTO.add(new MusicDTO(
               music.getName(),
               music.getPlaylists(),
                    generateTag()
            ));
        }
        return allMusicsDTO;
    }
    private int generateTag()throws MusicNotFoundException {
        Random rand = new Random();
        int tag;
        for (;;){
            tag = rand.nextInt();
            if(musicService.findByTag(tag)==null)
                break;
        }

        return Math.abs(tag);
    }

}
