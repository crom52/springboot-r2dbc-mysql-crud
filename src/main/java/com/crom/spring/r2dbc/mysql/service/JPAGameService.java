package com.crom.spring.r2dbc.mysql.service;

import com.crom.spring.r2dbc.mysql.controller.payload.GamePayload;
import com.crom.spring.r2dbc.mysql.model.Game;
import com.crom.spring.r2dbc.mysql.repository.jpa.JPAGameRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JPAGameService {

   JPAGameRepository jpaGameRepository;

   public List<Game> saveAll(List<Game> game) {

      return jpaGameRepository.saveAll(game);
   }

   public List<Game> findAll() {

      return jpaGameRepository.findAll();
   }

   public Game findById(long id) {

      return jpaGameRepository.findById(id).orElse(null);
   }

   public List<Game> findByTitleContaining(String title) {

      return jpaGameRepository.findByTitleContaining(title);
   }

   @Transactional("transactionManager")
   public Game update(long id, GamePayload gamePayload) {

      return jpaGameRepository.findById(id)
         .map(existingGame -> {
            Game updatedGame = Game.builder()
               .id(existingGame.getId())
               .title(gamePayload.getTitle())
               .description(gamePayload.getDescription())
               .published(gamePayload.isPublished())
               .build();
            return jpaGameRepository.save(updatedGame);
         }).orElse(null);
   }

   @Transactional("transactionManager")
   public Game save(Game game) {

      return jpaGameRepository.save(game);
   }
}
