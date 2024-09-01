package com.bezkoder.spring.r2dbc.mysql.repository;

import com.bezkoder.spring.r2dbc.mysql.model.Game;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface GameRepository extends R2dbcRepository<Game, Integer>{
  Flux<Game> findByTitleContaining(String title);

  Flux<Game> findByPublished(boolean isPublished);
}
