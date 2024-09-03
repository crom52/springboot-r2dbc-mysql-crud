package com.crom.spring.r2dbc.mysql.repository.r2dbc;

import com.crom.spring.r2dbc.mysql.model.Game;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface R2GameRepository extends R2dbcRepository<Game, Long>{
  Flux<Game> findByTitleContaining(String title);

  Flux<Game> findByPublished(boolean isPublished);
}
