package com.stefanini.spotify.exception;

public class User_infoNotFoundException extends Exception{
    public User_infoNotFoundException(Long id){
        super("Usuário não encontrado com id: "+id);
    }
}
