package com.crom.spring.r2dbc.mysql.service;

import com.crom.spring.r2dbc.mysql.controller.payload.GamePayload;
import com.crom.spring.r2dbc.mysql.model.Game;
import com.crom.spring.r2dbc.mysql.repository.r2dbc.R2GameRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class R2GameService {

   R2GameRepository r2GameRepository;

   public Flux<Game> findAll() {

      return r2GameRepository.findAll();
   }

   public Flux<Game> findByTitleContaining(String title) {

      return r2GameRepository.findByTitleContaining(title);
   }

   public Mono<Game> findById(long id) {

      return r2GameRepository.findById(id);
   }

   public Mono<Game> save(Game game) {

      return r2GameRepository.save(game);
   }

   public Flux<Game> saveAll(List<Game> game) {

      return r2GameRepository.saveAll(game);
   }

   //   @Transactional("reactiveTransactionManager")
   public Mono<Game> update(long id, GamePayload gamePayload) {

      return r2GameRepository.findById(id)
         .switchIfEmpty(Mono.error(new Exception("Game not found with id: " + id)))
         .flatMap(existingGame -> {
            Game updatedGame = Game.builder()
               .id(existingGame.getId())
               .title(gamePayload.getTitle())
               .description(gamePayload.getDescription())
               .published(gamePayload.isPublished())
               .build();
            return r2GameRepository.save(updatedGame);
         });
   }

   public Mono<Void> deleteById(long id) {

      return r2GameRepository.deleteById(id);
   }

   public Mono<Void> deleteAll() {

      return r2GameRepository.deleteAll();
   }

   public Flux<Game> findByPublished(boolean isPublished) {

      return r2GameRepository.findByPublished(isPublished);
   }
}
