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
    public String addUser_info(@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info newUser = user_infoService.findByName(user_infoDTO.getName());
        if(newUser == null) {
            newUser = user_infoService.findByEmail(user_infoDTO.getEmail());
            if(newUser == null) {
                User_info newUser_info = userInfoDTOService.mapUser(user_infoDTO);
                user_infoService.save(newUser_info);
                return "Usuário " + user_infoDTO.getName() + " criado com sucesso";
            }
            else{
                return "O email '"+user_infoDTO.getEmail()+"' já existe";
            }
        }
        else{
            return "O nome '"+user_infoDTO.getName()+"' já existe";
        }
    }
    @PutMapping("/name/{id}")
    public String updateUser_infoName(@PathVariable Long id,@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info newUserName = user_infoService.findByName(user_infoDTO.getName());
        User_info oldUser_info = user_infoService.findById(id);
        if(newUserName==null) {
            String oldName = oldUser_info.getName();
            User_info user_info = userInfoDTOService.mapUser(user_infoDTO);
            user_info.setEmail(oldUser_info.getEmail());
            user_info.setPassword(oldUser_info.getPassword());
            user_info.setId(oldUser_info.getId());
            user_infoService.save(user_info);
            return "O nome '"+oldName+"' foi atualizado para '"+user_infoDTO.getName()+"' com sucesso";
        }
        else{
            return "O nome '"+user_infoDTO.getName()+"' já existe";
        }
    }
    @PutMapping("/email/{id}")
    public String updateUser_infoEmail(@PathVariable Long id,@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info newUserEmail = user_infoService.findByEmail(user_infoDTO.getEmail());
        User_info oldUser_info = user_infoService.findById(id);
        if(newUserEmail==null) {
            String oldEmail = oldUser_info.getEmail();
            User_info user_info = userInfoDTOService.mapUser(user_infoDTO);
            user_info.setName(oldUser_info.getName());
            user_info.setPassword(oldUser_info.getPassword());
            user_info.setId(oldUser_info.getId());
            user_infoService.save(user_info);
            return "O email '"+oldEmail+"' foi atualizado para '"+user_infoDTO.getEmail()+"' com sucesso";
        }
        else{
            return "O email '"+user_infoDTO.getEmail()+"' já existe";
        }
    }
    @PutMapping("/password/{id}")
    public String updateUser_infoPassword(@PathVariable Long id,@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info oldUser_info = user_infoService.findById(id);
        User_info user_info = userInfoDTOService.mapUser(user_infoDTO);
        user_info.setName(oldUser_info.getName());
        user_info.setEmail(oldUser_info.getEmail());
        user_info.setId(oldUser_info.getId());
        user_infoService.save(user_info);
        return "Senha atualizada com sucesso";

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
