package com.bezkoder.spring.r2dbc.mysql.service;

import com.bezkoder.spring.r2dbc.mysql.controller.payload.GamePayload;
import com.bezkoder.spring.r2dbc.mysql.model.Game;

import org.springframework.stereotype.Service;

import com.bezkoder.spring.r2dbc.mysql.repository.GameRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {

    this.gameRepository = gameRepository;
  }

  public Flux<Game> findAll() {
    return gameRepository.findAll();
  }

  public Flux<Game> findByTitleContaining(String title) {
    return gameRepository.findByTitleContaining(title);
  }

  public Mono<Game> findById(int id) {
    return gameRepository.findById(id);
  }

  public Mono<Game> save(Game game) {
    return gameRepository.save(game);
  }

  public Mono<Game> update(int id, GamePayload gamePayload) {

    return gameRepository.findById(id)
       .switchIfEmpty(Mono.error(new Exception("Game not found with id: " + id)))
       .flatMap(existingGame -> {
         Game updatedGame = Game.builder()
            .id(existingGame.getId())
            .title(gamePayload.getTitle())
            .description(gamePayload.getDescription())
            .published(gamePayload.isPublished())
            .build();
         return gameRepository.save(updatedGame);
       });
  }

  public Mono<Void> deleteById(int id) {
    return gameRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return gameRepository.deleteAll();
  }

  public Flux<Game> findByPublished(boolean isPublished) {
    return gameRepository.findByPublished(isPublished);
  }
}
