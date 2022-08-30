package com.stefanini.spotify.service;

import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.repository.User_infoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_infoService {
    private final User_infoRepository userInfoRepository;

    public User_infoService(User_infoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }
    public List<User_info> findAllUsers(){
        return userInfoRepository.findAll();
    }
    public User_info findById(Long id) throws User_infoNotFoundException {
        return userInfoRepository.findById(id).
                orElseThrow(() -> new User_infoNotFoundException(id));
    }
    public User_info save(User_info userInfo){
        return userInfoRepository.save(userInfo);
    }
}