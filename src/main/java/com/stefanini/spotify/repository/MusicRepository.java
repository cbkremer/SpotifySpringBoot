package com.stefanini.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stefanini.spotify.model.Music;


public interface MusicRepository extends JpaRepository<Music, Long>{
    Music findByName(String name);
    Music findByTag(int tag);
}
