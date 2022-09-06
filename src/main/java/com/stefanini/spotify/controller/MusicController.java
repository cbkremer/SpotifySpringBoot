package com.stefanini.spotify.controller;


import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.mapper.MusicDTOService;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "music")
public class MusicController {
    private final MusicService musicService;
    private final MusicDTOService musicDTOService;

    @Autowired
    public MusicController(MusicService musicService, MusicDTOService musicDTOService){
        this.musicService=musicService;
        this.musicDTOService=musicDTOService;
    }

    @Autowired

    @RequestMapping
    public ModelAndView loadHtml(){
        ModelAndView mv = new ModelAndView("music");
        MusicDTO musicDTO = new MusicDTO();

        mv.addObject("musicDTO", musicDTO);

        return mv;
    }

    @PostMapping
    public Music addMusic(@RequestBody MusicDTO musicDTO){
        Music newMusic = musicDTOService.mapMusic(musicDTO);
        return musicService.save(newMusic);
    }

    @GetMapping
    public MusicDTO getAllMusics(){
        return null;
    }
}
