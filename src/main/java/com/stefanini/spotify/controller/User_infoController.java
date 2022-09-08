package com.stefanini.spotify.controller;

import com.stefanini.spotify.dto.PlaylistDTO;
import com.stefanini.spotify.dto.User_infoDTO;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.mapper.PlaylistDTOService;
import com.stefanini.spotify.mapper.User_infoDTOService;
import com.stefanini.spotify.model.Playlist;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.PlaylistService;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "user_info")
public class User_infoController {
    private final User_infoService user_infoService;
    private final User_infoDTOService userInfoDTOService;
    private final PlaylistDTOService playlistDTOService;
    private final PlaylistService playlistService;

    @Autowired
    public User_infoController(User_infoService userService, User_infoDTOService userInfoDTOService,PlaylistDTOService playlistDTOService,PlaylistService playlistService){
        this.user_infoService = userService;
        this.userInfoDTOService = userInfoDTOService;
        this.playlistDTOService = playlistDTOService;
        this.playlistService = playlistService;
    }

    @Autowired

    @RequestMapping
    public List<User_infoDTO> listUser_info()throws User_infoNotFoundException{
        List <User_info> users = user_infoService.findAllUsers();
        List<Playlist> playlists = playlistService.findAllPlaylists();
        return User_infoDTOService.convertList(users,playlists);
    }
    @RequestMapping("/{id}")
    public User_infoDTO user_info(@PathVariable Long id) throws User_infoNotFoundException{
        User_info user = user_infoService.findById(id);
        List<PlaylistDTO> playlists = playlistDTOService.convertPlaylistsByUserId(id);
        return User_infoDTOService.convertUser(user,playlists);
    }
    @PostMapping
    public User_info addUser_info(@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info newUser_info = userInfoDTOService.mapUser(user_infoDTO);
        return user_infoService.save(newUser_info);
    }
    @PutMapping("/{id}")
    public User_info updateUser_info(@PathVariable Long id,@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info user_info = userInfoDTOService.mapUser(user_infoDTO);
        user_info.setId(id);
        return user_infoService.save(user_info);
    }

    @DeleteMapping("/{id}")
    public String deleteUser_info(@PathVariable Long id){
        User_info user_info;
        try{
            user_info = user_infoService.findById(id);
            user_infoService.delete(user_info);
            return "Usuário "+user_info.getName()+" deletado com sucesso";
        }catch (User_infoNotFoundException ex){
            Logger.getLogger(User_infoController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return "Ocorreu um erro ao deletar usuário";
    }

    /*public String saveUser(User_infoDTO user) throws PlaylistNotFoundException{
        User_info newUserInfo = userInfoDTOService.mapUser(user);

        userService.save(newUserInfo);
        return "redirect:/user";
    }*/

}
