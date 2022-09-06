package com.stefanini.spotify.controller;


import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.MusicNotFoundException;
import com.stefanini.spotify.mapper.MusicDTOService;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Music addMusic(@RequestBody MusicDTO musicDTO)throws MusicNotFoundException{
        Music newMusic = musicDTOService.mapMusic(musicDTO);
        return musicService.save(newMusic);
    }

    @GetMapping
    public List<MusicDTO> getAllMusics()throws MusicNotFoundException{
        return musicDTOService.convertAllMusics();
    }
    @DeleteMapping
    public String deleteMusic(@RequestBody MusicDTO musicDTO)throws MusicNotFoundException {
        try{
            Music music = musicService.findByName(musicDTO.getName());
            musicService.delete(music);
            return "Musica "+musicDTO.getName()+" deletada com sucesso";
        }
        catch (MusicNotFoundException ex){
            Logger.getLogger(MusicController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return "Ocorreu um erro ao deletar a musica";
    }
    @PutMapping
    public String updateMusic(@RequestBody MusicDTO musicDTO)throws MusicNotFoundException{
        try {
            Music music = musicService.findByTag(musicDTO.getTag());
            String old_name = music.getName();
            music.setName(musicDTO.getName());
            musicService.save(music);
            return "Música '" + old_name + "' atualizada para: '" + musicDTO.getName() + "' com sucesso";
        }
        catch(MusicNotFoundException ex){
            Logger.getLogger(MusicController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return "Ocorreu um erro ao atualizar a música";
    }
}
