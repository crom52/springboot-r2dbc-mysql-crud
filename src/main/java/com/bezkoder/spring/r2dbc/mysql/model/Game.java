package com.bezkoder.spring.r2dbc.mysql.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;

@Builder
@Value
public class Game {

   @Id
   Integer id;
   String title;
   String description;
   boolean published;

}
