package com.work.turfbooking.service;

import com.work.turfbooking.dto.TurfDto;
import com.work.turfbooking.entity.Turf;

import java.util.List;

public interface TurfService {

    TurfDto addTurf(TurfDto turfDto);

    TurfDto updateTurf(Integer id, TurfDto turfDto);

    TurfDto getTurfById(Integer id);

    void deleteTurf(Integer id);

    List<TurfDto> getTurfsByName(String name);

    List<TurfDto> getAllProducts(Integer pageNumber,Integer pageSize);


}
