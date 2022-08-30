package com.stefanini.spotify.model;

//import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

        import java.util.List;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "user_info")
public class User_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "userInfo")
    private List<Playlist> playlists;

    public User_info(Long id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        //this.playlists = playlists;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
