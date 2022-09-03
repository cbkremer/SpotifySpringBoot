package com.stefanini.spotify.controller;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.mapper.PlaylistDTOService;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "playlist")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;
    private final User_infoService user_infoService;
    @Autowired
    public PlaylistController(PlaylistDTOService playlistDTOService, PlaylistService playlistService, User_infoService user_infoService){
        this.playlistDTOService = playlistDTOService;
        this.playlistService = playlistService;
        this.user_infoService = user_infoService;
    }

    @Autowired

    @RequestMapping
    public ModelAndView loadHtml(){
        ModelAndView mv = new ModelAndView("playlsit");
        PlaylistDTO playlistDTO = new PlaylistDTO();

        mv.addObject("playlistDTO", playlistDTO);

        return mv;
    }

    @PutMapping("/{id}")
    public String updateUserPlaylist(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO)throws User_infoNotFoundException, PlaylistNotFoundException{
        return "nao implementado ainda";
    }
    @GetMapping("/{id}")
    public List<PlaylistDTO> getUserPlaylists(@PathVariable Long id)throws User_infoNotFoundException {
        return playlistDTOService.convertPlaylistsByUserId(id);
    }
    @PostMapping("/{id}")
    public String savePlaylist(@RequestBody PlaylistDTO playlistDTO, @PathVariable Long id) throws User_infoNotFoundException {
        Playlist newPlaylist = playlistDTOService.mapPlaylist(playlistDTO,id);
        playlistService.save(newPlaylist);
        User_info user = user_infoService.findById(id);
        return user.getName()+", sua playlist: "+playlistDTO.getName()+" foi criada com sucesso";
    }

}
