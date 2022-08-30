package com.stefanini.spotify.controller;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.mapper.PlaylistDTOService;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PlaylistController {
    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;
    @Autowired
    public PlaylistController(PlaylistDTOService playlistDTOService, PlaylistService playlistService){
        this.playlistDTOService = playlistDTOService;
        this.playlistService = playlistService;
    }

    @Autowired

    @RequestMapping(path = "/playlist")
    public ModelAndView loadHtml(){
        ModelAndView mv = new ModelAndView("playlsit");
        PlaylistDTO playlistDTO = new PlaylistDTO();

        mv.addObject("playlistDTO", playlistDTO);

        return mv;
    }
    @PostMapping(path = "playlist")
    public String savePlaylist(PlaylistDTO playlist) throws User_infoNotFoundException {
        Playlist newPlaylist = playlistDTOService.mapPlaylist(playlist);

        playlistService.save(newPlaylist);
        return "redirect:/playlist";
    }

}
