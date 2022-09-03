package com.stefanini.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stefanini.spotify.model.Playlist;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Playlist findByName(String name);

    Playlist findByTag(int tag);
}
