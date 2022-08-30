package com.stefanini.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stefanini.spotify.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
