package com.crom.spring.r2dbc.mysql.controller;

import com.crom.spring.r2dbc.mysql.controller.payload.GamePayload;
import com.crom.spring.r2dbc.mysql.model.Game;
import com.crom.spring.r2dbc.mysql.service.R2GameService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/jpa/games")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JPAGameController {

   R2GameService r2GameService;

   @GetMapping("")
   @ResponseStatus(HttpStatus.OK)
   public Flux<Game> findGames(@RequestParam(required = false) String title) {

      if (title == null) {
         return r2GameService.findAll();
      } else {
         return r2GameService.findByTitleContaining(title);
      }
   }

   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Mono<Game> getTutorialById(@PathVariable("id") int id) {

      return r2GameService.findById(id);
   }

   @PostMapping("")
   @ResponseStatus(HttpStatus.CREATED)
   public Mono<Game> createTutorial(@RequestBody GamePayload gamePayload) {

      Game game = Game.builder()
         .title(gamePayload.getTitle())
         .description(gamePayload.getDescription())
         .published(gamePayload.isPublished())
         .build();

      return r2GameService.save(game);
   }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Mono<Game> updateTutorial(@PathVariable("id") long id, @RequestBody GamePayload gamePayload) {

      return r2GameService.update(id, gamePayload);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public Mono<Void> deleteGame(@PathVariable("id") int id) {

      return r2GameService.deleteById(id);
   }

   @DeleteMapping("/games")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public Mono<Void> deleteAllGames() {

      return r2GameService.deleteAll();
   }

   @GetMapping("/games/published")
   @ResponseStatus(HttpStatus.OK)
   public Flux<Game> findByPublished() {

      return r2GameService.findByPublished(true);
   }
}
