package com.crom.spring.r2dbc.mysql.controller;

import com.crom.spring.r2dbc.mysql.controller.payload.GamePayload;
import com.crom.spring.r2dbc.mysql.model.Game;
import com.crom.spring.r2dbc.mysql.service.JPAGameService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jpa/games")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JPAGameController {

   JPAGameService jpaGameService;

   @GetMapping("")
   @ResponseStatus(HttpStatus.OK)
   public List<Game> findGames(@RequestParam(required = false) String title) {

      if (title == null) {
         return jpaGameService.findAll();
      } else {
         return jpaGameService.findByTitleContaining(title);
      }
   }

   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Game getTutorialById(@PathVariable("id") int id) {

      return jpaGameService.findById(id);
   }

   @PostMapping("")
   @ResponseStatus(HttpStatus.CREATED)
   public Game createGame(@RequestBody GamePayload gamePayload) {

      Game game = Game.builder()
         .title(gamePayload.getTitle())
         .description(gamePayload.getDescription())
         .published(gamePayload.isPublished())
         .build();

      return jpaGameService.save(game);
   }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Game updateGame(@PathVariable("id") long id, @RequestBody GamePayload gamePayload) {

      return jpaGameService.update(id, gamePayload);
   }

}
