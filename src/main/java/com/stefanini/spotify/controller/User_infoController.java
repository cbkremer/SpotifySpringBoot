package com.stefanini.spotify.controller;

import com.stefanini.spotify.dto.User_infoDTO;
import com.stefanini.spotify.exception.PlaylistNotFoundException;
import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.mapper.User_infoDTOService;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.service.User_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "user_info")
public class User_infoController {
    private final User_infoService user_infoService;
    private final User_infoDTOService userInfoDTOService;

    @Autowired
    public User_infoController(User_infoService userService, User_infoDTOService userInfoDTOService){
        this.user_infoService = userService;
        this.userInfoDTOService = userInfoDTOService;
    }

    @Autowired

    @RequestMapping
    public List<User_infoDTO> listUser_info(){
        List <User_info> users = user_infoService.findAllUsers();
        return User_infoDTOService.convertList(users);
    }
    @RequestMapping("/{id}")
    public User_infoDTO user_info(@PathVariable Long id) throws User_infoNotFoundException{
        User_info user = user_infoService.findById(id);
        return User_infoDTOService.convertUser(user);
    }
    @PostMapping
    public User_info addUser_info(@RequestBody User_infoDTO user_infoDTO) throws User_infoNotFoundException{
        User_info newUser_info = userInfoDTOService.mapUser(user_infoDTO);
        return user_infoService.save(newUser_info);
    }

    /*public String saveUser(User_infoDTO user) throws PlaylistNotFoundException{
        User_info newUserInfo = userInfoDTOService.mapUser(user);

        userService.save(newUserInfo);
        return "redirect:/user";
    }*/

}
