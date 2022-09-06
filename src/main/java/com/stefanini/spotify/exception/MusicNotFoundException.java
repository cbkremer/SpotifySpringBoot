package com.stefanini.spotify.exception;

public class MusicNotFoundException extends Exception{
    public MusicNotFoundException(Long id){
        super("Musica não encontrada com id: "+id);
    }
}
