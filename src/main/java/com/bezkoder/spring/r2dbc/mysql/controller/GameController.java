package com.bezkoder.spring.r2dbc.mysql.controller;

import com.bezkoder.spring.r2dbc.mysql.controller.payload.GamePayload;
import com.bezkoder.spring.r2dbc.mysql.model.Game;
import com.bezkoder.spring.r2dbc.mysql.service.GameService;
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
@RequestMapping("/api/games")
public class GameController {

   private final GameService gameService;


   public GameController(GameService gameService) {

      this.gameService = gameService;
   }

   @GetMapping("")
   @ResponseStatus(HttpStatus.OK)
   public Flux<Game> findGames(@RequestParam(required = false) String title) {

      if (title == null) {
         return gameService.findAll();
      } else {
         return gameService.findByTitleContaining(title);
      }
   }

   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Mono<Game> getTutorialById(@PathVariable("id") int id) {

      return gameService.findById(id);
   }

   @PostMapping("")
   @ResponseStatus(HttpStatus.CREATED)
   public Mono<Game> createTutorial(@RequestBody GamePayload gamePayload) {

      Game game = Game.builder()
         .title(gamePayload.getTitle())
         .description(gamePayload.getDescription())
         .published(gamePayload.isPublished())
         .build();

      return gameService.save(game);
   }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Mono<Game> updateTutorial(@PathVariable("id") int id, @RequestBody GamePayload gamePayload) {

      return gameService.update(id, gamePayload);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public Mono<Void> deleteGame(@PathVariable("id") int id) {

      return gameService.deleteById(id);
   }

   @DeleteMapping("/games")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public Mono<Void> deleteAllGames() {

      return gameService.deleteAll();
   }

   @GetMapping("/games/published")
   @ResponseStatus(HttpStatus.OK)
   public Flux<Game> findByPublished() {

      return gameService.findByPublished(true);
   }
}
