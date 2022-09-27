package com.stefanini.spotify.service;

import com.stefanini.spotify.exception.User_infoNotFoundException;
import com.stefanini.spotify.model.User_info;
import com.stefanini.spotify.repository.User_infoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_infoService {
    private final User_infoRepository userInfoRepository;

    public User_infoService(User_infoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }
    public Page<User_info> findAllUsers(Pageable paginacao){
        return userInfoRepository.findAll(paginacao);
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
    public void delete(User_info userInfo){
        userInfoRepository.delete(userInfo);
    }
    public User_info findByName(String name) throws User_infoNotFoundException{
        return userInfoRepository.findByName(name);
    }
    public User_info findByEmail(String email){
        return userInfoRepository.findByEmail(email);
    }
}
