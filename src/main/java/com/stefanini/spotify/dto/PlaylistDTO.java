package com.stefanini.spotify.dto;

public class PlaylistDTO {
    private String name;
    private Long quantity;
    private String user_name;
    private int tag;
    public PlaylistDTO(String name, Long quantity, String user_name,int tag){
        this.name = name;
        this.quantity = quantity;
        this.user_name = user_name;
        this.tag = tag;
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

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }
}
