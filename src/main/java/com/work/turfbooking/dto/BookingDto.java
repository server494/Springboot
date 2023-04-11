package com.work.turfbooking.dto;


import com.work.turfbooking.entity.Customer;
import com.work.turfbooking.entity.Turf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {

    private int id;
    private boolean openBooking;
    private String openBookerName;
    private Date bookedDate;
    int customerId;
    int turfId;

}
