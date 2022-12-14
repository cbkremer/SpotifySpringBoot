package com.stefanini.spotify.controller;


import com.stefanini.spotify.dto.MusicDTO;
import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.exception.MusicNotFoundException;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.mapper.MusicDTOService;
import com.stefanini.spotify.mapper.PlaylistDTOService;
import com.stefanini.spotify.model.Music;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.service.MusicService;
import com.stefanini.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "music")
public class MusicController {
    private final MusicService musicService;
    private final MusicDTOService musicDTOService;
    private final PlaylistService playlistService;
    private final PlaylistDTOService playlistDTOService;

    @Autowired
    public MusicController(MusicService musicService, MusicDTOService musicDTOService,PlaylistService playlistService,PlaylistDTOService playlistDTOService){
        this.musicService=musicService;
        this.musicDTOService=musicDTOService;
        this.playlistService=playlistService;
        this.playlistDTOService=playlistDTOService;
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
    public String addMusic(@RequestBody MusicDTO musicDTO)throws MusicNotFoundException{
        //por enquanto nenhuma valida????o parece necess??ria
        Music newMusic = musicDTOService.mapMusic(musicDTO);
        musicService.save(newMusic);
        return "Musica "+musicDTO.getName()+" criada com sucesso";
    }

    @GetMapping
    public List<MusicDTO> getAllMusics()throws MusicNotFoundException {
        return musicDTOService.convertAllMusics();
    }
    @DeleteMapping
    @Transactional
    public String deleteMusic(@RequestBody MusicDTO musicDTO)throws MusicNotFoundException {
        try{
            Music music = musicService.findByTag(musicDTO.getTag());
            String music_name = music.getName();
            musicService.delete(music);
            return "Musica "+music_name+" deletada com sucesso";
        }
        catch (MusicNotFoundException ex){
            Logger.getLogger(MusicController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return "Ocorreu um erro ao deletar a musica";
    }
    @PutMapping
    @Transactional
    public String updateMusic(@RequestBody MusicDTO musicDTO)throws MusicNotFoundException{
        try {
            Music music = musicService.findByTag(musicDTO.getTag());
            String old_name = music.getName();
            music.setName(musicDTO.getName());
            musicService.save(music);
            return "M??sica '" + old_name + "' atualizada para: '" + musicDTO.getName() + "' com sucesso";
        }
        catch(MusicNotFoundException ex){
            Logger.getLogger(MusicController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return "Ocorreu um erro ao atualizar a m??sica";
    }
    @PutMapping("{playlist_tag}")
    @Transactional
    public String addMusicToPlaylist(@PathVariable int playlist_tag, @RequestBody MusicDTO musicDTO)throws MusicNotFoundException, PlaylistNotFoundException {
        Playlist playlist = playlistService.findByTag(playlist_tag);
        Music music = musicService.findByTag(musicDTO.getTag());
        List <Music> musics = playlist.getMusics();
        for (Music playlistMusic: musics) {
            if(playlistMusic.getTag()==musicDTO.getTag()){
                return "A m??sica '"+music.getName()+"' j?? esta na playlist '"+playlist.getName()+"'";
            }
        }
        List<Playlist> playlists = music.getPlaylists();
        musics.add(music);
        playlists.add(playlist);
        playlist.setMusics(musics);
        long count = 0;
        for (Music playMusic: playlist.getMusics()) {
            count++;
        }
        playlist.setQuantity(count);
        playlistService.save(playlist);
        music.setPlaylists(playlists);
        musicService.save(music);
        return "A m??sica '"+music.getName()+"' foi adicionada a playlist '"+playlist.getName()+"' com sucesso";
    }
    @PutMapping("/remove/{playlist_tag}")
    @Transactional
    public String removeMusicFromPlaylist(@PathVariable int playlist_tag, @RequestBody MusicDTO musicDTO)throws PlaylistNotFoundException, MusicNotFoundException{
        Playlist playlist = playlistService.findByTag(playlist_tag);
        Music music = musicService.findByTag(musicDTO.getTag());
        List<Music> musics = playlist.getMusics();
        List<Playlist> playlists = music.getPlaylists();
        for (int i = 0;i< musics.size();i++){
            if(musics.get(i).getTag()==musicDTO.getTag()){
                musics.remove(i);
            }
        }
        playlist.setMusics(musics);
        for(int i = 0;i < playlists.size();i++){
            if(playlists.get(i).getTag()==playlist.getTag()){
                playlists.remove(i);
            }
        }
        long count = 0;
        for (Music playMusic: playlist.getMusics()) {
            count++;
        }
        playlist.setQuantity(count);
        music.setPlaylists(playlists);
        playlistService.save(playlist);
        musicService.save(music);
        return "Musica '"+music.getName()+"' removida da playlist '"+playlist.getName()+"' com sucesso";
    }
    @PutMapping("/removeall/")
    @Transactional
    public String removeMusicFromAllPlaylists(@RequestBody MusicDTO musicDTO)throws PlaylistNotFoundException, MusicNotFoundException{
        //getall playlists do banco n??o ?? a melhor ideia mas funciona por enquannto

        //Pageable paginacao = PageRequest.of(pag,qtd);

        List<Playlist> playlists = playlistService.findAllPlaylists();
        Music music = musicService.findByTag(musicDTO.getTag());
        for (Playlist playlist: playlists) {
            List<Music> musics = playlist.getMusics();
            for(int i = 0;i < musics.size();i++) {
                if(musics.get(i).getTag()==musicDTO.getTag()){
                    musics.remove(i);
                }
            }
            long count = 0;
            for (Music playMusic: playlist.getMusics()) {
                count++;
            }
            playlist.setQuantity(count);
            music.setPlaylists(playlists);
            playlistService.save(playlist);
        }
        music.setPlaylists(new ArrayList<>());
        musicService.save(music);
        return "Musica '"+music.getName()+"' removida de todas as playlists com sucesso";
    }
}
