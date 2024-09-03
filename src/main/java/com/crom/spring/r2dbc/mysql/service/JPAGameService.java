package com.crom.spring.r2dbc.mysql.service;

import com.crom.spring.r2dbc.mysql.model.Game;
import com.crom.spring.r2dbc.mysql.repository.jpa.JPAGameRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JPAGameService {
   JPAGameRepository JPAGameRepository;

   public List<Game> saveAll(List<Game> game) {
      return JPAGameRepository.saveAll(game);
   }

   public List<Game> findAll() {
      return JPAGameRepository.findAll();
   }

}
