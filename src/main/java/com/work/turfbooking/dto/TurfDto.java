package com.work.turfbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurfDto {
    int id;
    String name;
    String place;
    String openingTime;
    String closingTime;
}
