package com.work.turfbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean openBooking;
    private String openBookerName;
    private String TimeSlot;
    private Date bookedDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="turf_id",referencedColumnName = "id")
    private Turf turf;
}
