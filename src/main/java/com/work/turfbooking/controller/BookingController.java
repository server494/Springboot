package com.work.turfbooking.controller;


import com.work.turfbooking.dto.BookingDto;
import com.work.turfbooking.entity.Booking;
import com.work.turfbooking.service.BookingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/addBooking")
    @ApiOperation("Adding new booking")
    public BookingDto saveBooking(@RequestBody BookingDto bookingDto){

        BookingDto saveBooking = bookingService.addBooking(bookingDto);
        return saveBooking;
    }



    @GetMapping("/getAllBookings")
    @ApiOperation("Getting all bookings")
    public List<BookingDto> getAllBookings(@RequestParam(value = "pageNumber",defaultValue ="0",required = false)Integer pageNumber,
                                           @RequestParam(value = "pageSize",defaultValue = "1",required = false)Integer pageSize){

        List<BookingDto> bookingDtoList = bookingService.getAllBooking(pageNumber,pageSize);
        return bookingDtoList;
    }
    @GetMapping("/getBooking/{id}")
    @ApiOperation("Getting booking by id")
    public BookingDto getBooking(@PathVariable Integer id){

        BookingDto bookingDto = bookingService.getBookingById(id);
        return bookingDto;
    }
    @PutMapping("/updateBooking/{id}")
    @ApiOperation("updating booking by id")
    public BookingDto updateBooking(@PathVariable Integer id, @RequestBody BookingDto bookingDto){

        BookingDto updatedBooking = bookingService.updateBooking(id,bookingDto);
        return updatedBooking;
    }
    @DeleteMapping("/deleteBooking/{id}")
    @ApiOperation("delete booking by id")
    public String deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);
        return "Booking Successfully deleted";
    }


}
