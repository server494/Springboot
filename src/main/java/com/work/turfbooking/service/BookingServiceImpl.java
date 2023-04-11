package com.work.turfbooking.service;


import com.work.turfbooking.dto.BookingDto;
import com.work.turfbooking.entity.Booking;
import com.work.turfbooking.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{


    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BookingDto addBooking(BookingDto bookingDto) {

        Booking newBooking = dtoToBooking(bookingDto);
        Date date = bookingDto.getBookedDate();
        String time= String.valueOf(date.getTime());
        newBooking.setTimeSlot(time);
        bookingRepository.save(newBooking);
        BookingDto newBookingDto = bookingToDto(newBooking);
        return newBookingDto;
    }

    @Override
    public List<BookingDto> getAllBooking(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest= PageRequest.of(pageNumber,pageSize);
        Page<Booking> page = bookingRepository.findAll(pageRequest);
        List<Booking> bookingList = page.getContent();

        List<BookingDto> bookingDtoList = bookingList.stream()
                .map((booking -> modelMapper.map(booking, BookingDto.class)))
                .collect(Collectors.toList());
        return bookingDtoList;
    }

    @Override
    public BookingDto getBookingById(Integer id) {

        Booking booking = bookingRepository.findById(id).get();
        BookingDto bookingDto = bookingToDto(booking);
        return bookingDto;
    }

    @Override
    public BookingDto updateBooking(Integer id, BookingDto bookingDto) {

        Booking booking =bookingRepository.findById(id).get();
        booking.setBookedDate(booking.getBookedDate());
        booking.setOpenBooking(bookingDto.isOpenBooking());
        booking.setOpenBookerName(bookingDto.getOpenBookerName());
        bookingRepository.save(booking);
        BookingDto updatedBookingDto = bookingToDto(booking);

        return updatedBookingDto;
    }

    @Override
    public void deleteBooking(Integer id) {

        Booking booking = bookingRepository.findById(id).get();
        bookingRepository.delete(booking);
    }


    private Booking dtoToBooking(BookingDto bookingDto){
        Booking booking = modelMapper.map(bookingDto,Booking.class);
        return booking;
    }

    private BookingDto bookingToDto(Booking booking){
        BookingDto bookingDto = modelMapper.map(booking,BookingDto.class);
        return bookingDto;
    }

}
