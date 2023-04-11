package com.work.turfbooking.service;

import com.work.turfbooking.dto.TurfDto;
import com.work.turfbooking.entity.Turf;
import com.work.turfbooking.repository.TurfRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurfServiceImpl implements TurfService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TurfRepository turfRepository;


    @Override
    public TurfDto addTurf(TurfDto turfDto) {
        Turf turf = dtoToTurf(turfDto);
        Turf savedTurf=turfRepository.save(turf);
        return turfToDto(savedTurf);
    }

    @Override
    public TurfDto updateTurf(Integer id, TurfDto turfDto) {

        Turf turf = turfRepository.findById(id).get();
        turf.setName(turfDto.getName());
        turf.setClosingTime(turfDto.getClosingTime());
        turf.setOpeningTime(turfDto.getOpeningTime());
        turf.setPlace(turfDto.getPlace());

        Turf updatedTurf = turfRepository.save(turf);
        TurfDto updatedTurfDto = turfToDto(updatedTurf);
        return updatedTurfDto;
    }

    @Override
    public TurfDto getTurfById(Integer id) {

        Turf turf = turfRepository.findById(id).get();
        TurfDto turfDto = turfToDto(turf);
        return turfDto;
    }

    @Override
    public void deleteTurf(Integer id) {
        Turf turf = turfRepository.findById(id).get();
        turfRepository.delete(turf);
    }

    @Override
    public List<TurfDto> getTurfsByName(String name) {

        List<Turf> turfList = turfRepository.getTurfsByName(name);
        List<TurfDto> turfDtoList = turfList
                .stream()
                .map((turf -> modelMapper.map(turf,TurfDto.class)))
                .collect(Collectors.toList());
        return turfDtoList;
    }

    @Override
    public List<TurfDto> getAllProducts(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        Page<Turf> page = turfRepository.findAll(pageRequest);
        List<Turf> turfList = page.getContent();


        List<TurfDto> turfDtoList = turfList.stream()
                .map((turf -> modelMapper.map(turf,TurfDto.class)))
                .collect(Collectors.toList());
        return turfDtoList;
    }


    private Turf dtoToTurf(TurfDto turfDto){
        Turf turf = modelMapper.map(turfDto,Turf.class);
        return turf;
    }
    private TurfDto turfToDto(Turf turf){
        TurfDto turfDto = modelMapper.map(turf, TurfDto.class);
        return turfDto;
    }
}
