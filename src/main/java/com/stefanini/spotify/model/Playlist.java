package com.stefanini.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
//@NoArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long quantity;
    @Column(nullable = false)
    private int tag;
    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "user_info_id")
    private User_info userInfo;

    @ManyToMany
    @JoinTable(name = "playlist_music",joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns =  @JoinColumn(name = "music_id"))
    private List<Music> musics;

    public Playlist(Long id, String name, Long quantity, User_info userInfo,int tag,List<Music> musics){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.userInfo = userInfo;
        this.tag = tag;
        this.musics=musics;
    }
    public Playlist(){

    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public User_info getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User_info userInfo) {
        this.userInfo = userInfo;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public List<Music> getMusics() {
        return musics;
    }
}
