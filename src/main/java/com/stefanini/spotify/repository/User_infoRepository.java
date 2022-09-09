package com.stefanini.spotify.repository;

import com.stefanini.spotify.model.User_info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_infoRepository extends JpaRepository<User_info, Long> {
    User_info findByName(String name);
    User_info findByEmail(String email);
}
