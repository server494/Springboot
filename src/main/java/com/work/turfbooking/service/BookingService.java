package com.work.turfbooking.service;

import com.work.turfbooking.dto.BookingDto;
import com.work.turfbooking.entity.Booking;

import java.util.List;

public interface BookingService {


    BookingDto addBooking(BookingDto booking);

    List<BookingDto> getAllBooking(Integer pageNumber,Integer pageSize);

    BookingDto getBookingById(Integer id);

    BookingDto updateBooking(Integer id,BookingDto bookingDto);

    void deleteBooking(Integer id);


}
