package com.stefanini.spotify.dto;

public class PlaylistDTO {
    private String name;
    private Long quantity;
    private String user_name;

    public PlaylistDTO(String name, Long quantity, String user_name){
        this.name = name;
        this.quantity = quantity;
        this.user_name = user_name;
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

    public String getUser_name(){
        return user_name;
    }
}
