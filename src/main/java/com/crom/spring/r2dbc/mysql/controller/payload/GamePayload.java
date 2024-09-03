package com.crom.spring.r2dbc.mysql.controller.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
@Getter
public class GamePayload {

   String title;
   String description;
   boolean published;

}
