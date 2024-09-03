package com.crom.spring.r2dbc.mysql.repository.jpa;

import com.crom.spring.r2dbc.mysql.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAGameRepository extends JpaRepository<Game, Long> {

}
