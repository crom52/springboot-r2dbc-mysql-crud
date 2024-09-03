package com.crom.spring.r2dbc.mysql.controller;

import static java.lang.Thread.sleep;

import com.crom.spring.r2dbc.mysql.model.Game;
import com.crom.spring.r2dbc.mysql.service.JPAGameService;
import com.crom.spring.r2dbc.mysql.service.R2GameService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class TestController {

   static final int NUM_OF_RECORD = 200000;
   R2GameService r2GameService;
   JPAGameService jpaGameService;

   @PostMapping("/create")
   public String testCreate100kRecord() throws InterruptedException {
      //warm up
      sleep(1000);

      // loop ad dummy create the Games instance object then add to the List<Game>
      List<Game> games = IntStream.range(0, NUM_OF_RECORD)
         .mapToObj(i -> Game.builder()
            .title("Game " + i)
            .description("Description " + i)
            .published(true)
            .build())
         .toList();

      long startR2 = System.currentTimeMillis();
      AtomicLong durationR2 = new AtomicLong();
      r2GameService.saveAll(games).doFinally(signalType -> {
         durationR2.set(System.currentTimeMillis() - startR2);
      }).subscribe();
      long endR2 = System.currentTimeMillis();


      long startJPA = System.currentTimeMillis();
//      jpaGameService.saveAll(games);
      long endJPA = System.currentTimeMillis();

      return "Insert " + NUM_OF_RECORD + " Records: " + (endR2 - startR2) + " ms, JPA: " + (endJPA - startJPA) + " ms";
   }

   @GetMapping("/get")
   public String testGet100kRecords() throws InterruptedException {
      //warm up
      sleep(1000);

      // loop and dummy create the Games instance object then add to the List<Game>
      List<Game> games = IntStream.range(0, NUM_OF_RECORD)
         .mapToObj(i -> Game.builder()
            .title("Game " + i)
            .description("Description " + i)
            .published(true)
            .build())
         .toList();
      long startR2 = System.currentTimeMillis();
      r2GameService.findAll();
      long endR2 = System.currentTimeMillis();

      long startJPA = System.currentTimeMillis();
      jpaGameService.findAll();
      long endJPA = System.currentTimeMillis();

      return "Get " + NUM_OF_RECORD + " Records: " + "R2DBC: " + (endR2 - startR2) + " ms, JPA: " + (endJPA - startJPA) + " ms";
   }

   //use to remove data only
   @DeleteMapping("/delete")
   public Object deleteAll() {

      return r2GameService.deleteAll();
   }
}
