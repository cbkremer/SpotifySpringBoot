package com.stefanini.spotify.controller;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.MusicNotFoundException;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.mapper.MusicDTOService;
import com.stefanini.spotify.mapper.PlaylistDTOService;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "playlist")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;
    private final User_infoService user_infoService;
    private final MusicDTOService musicDTOService;
    @Autowired
    public PlaylistController(PlaylistDTOService playlistDTOService, PlaylistService playlistService, User_infoService user_infoService,MusicDTOService musicDTOService){
        this.playlistDTOService = playlistDTOService;
        this.playlistService = playlistService;
        this.user_infoService = user_infoService;
        this.musicDTOService=musicDTOService;
    }

    @Autowired

    @RequestMapping
    public ModelAndView loadHtml(){
        ModelAndView mv = new ModelAndView("playlsit");
        PlaylistDTO playlistDTO = new PlaylistDTO();

        mv.addObject("playlistDTO", playlistDTO);

        return mv;
    }

    @DeleteMapping
    @Transactional
    public String deleteUserPlaylist(@RequestBody PlaylistDTO playlistDTO)throws PlaylistNotFoundException{
        try {
            Playlist playlist = playlistService.findByTag(playlistDTO.getTag());
            playlistService.delete(playlist);
            return "Playlist " + playlist.getName() + " deletada com sucesso";
        }
        catch(PlaylistNotFoundException ex){
            Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return "Ocorreu um erro ao deletar playlist";
    }
    @PutMapping("/{user_id}")
    @Transactional
    public String updateUserPlaylist(@PathVariable Long user_id, @RequestBody PlaylistDTO playlistDTO)throws User_infoNotFoundException, PlaylistNotFoundException, MusicNotFoundException {
        Playlist newPlaylist = playlistDTOService.mapPlaylist(playlistDTO,user_id,musicDTOService);
        Playlist playlist = playlistService.findByTag(playlistDTO.getTag());
        String oldPlaylistName = playlist.getName();
        newPlaylist.setTag(playlistDTO.getTag());
        newPlaylist.setId(playlist.getId());
        newPlaylist.setUserInfo(playlist.getUserInfo());
        playlistService.save(newPlaylist);
        return playlist.getUserInfo().getName()+", sua playlist "+oldPlaylistName+" foi atualizada";
    }
    @GetMapping("/{id}")
    public List<PlaylistDTO> getUserPlaylists(@PathVariable Long id)throws User_infoNotFoundException {
        return playlistDTOService.convertPlaylistsByUserId(id,musicDTOService);
    }
    @GetMapping("/music/{id}")
    public List<PlaylistDTO> getUserPlaylistMusic(@PathVariable Long id)throws User_infoNotFoundException{
        return null;
    }
    @GetMapping
    public List<PlaylistDTO> getAllPlaylists(){
        return playlistDTOService.convertAllPlaylists(musicDTOService);
    }
    @PostMapping("/{id}")
    @Transactional
    public String savePlaylist(@RequestBody PlaylistDTO playlistDTO, @PathVariable Long id) throws User_infoNotFoundException,PlaylistNotFoundException,MusicNotFoundException {
        Playlist newPlaylist = playlistDTOService.mapPlaylist(playlistDTO,id,musicDTOService);
        playlistService.save(newPlaylist);
        User_info user = user_infoService.findById(id);
        return user.getName()+", sua playlist: "+playlistDTO.getName()+" foi criada com sucesso";
    }

}
