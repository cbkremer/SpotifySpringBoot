package com.stefanini.spotify.dto;

public class PlaylistDTO {
    private String name;
    private Long quantity;
    private Long id_user;

    public PlaylistDTO(String name, Long quantity, Long id_user, Long id_music){
        this.name = name;
        this.quantity = quantity;
        this.id_user = id_user;
    }
    public PlaylistDTO(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId_user() {
        return id_user;
    }
}
