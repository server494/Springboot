package com.work.turfbooking.controller;

import com.work.turfbooking.dto.TurfDto;
import com.work.turfbooking.service.TurfService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turf")
public class TurfController {

    @Autowired
    private TurfService turfService;

    @PostMapping("/addTurf")
    @ApiOperation("Adding Turf")
    public TurfDto addTurf(@RequestBody TurfDto turfDto){
        turfService.addTurf(turfDto);
        return turfDto;
    }

    @GetMapping("/getAllTurf")
    @ApiOperation("GetAllTurfs")
    public List<TurfDto> getAllTurfs(@RequestParam (value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
                               @RequestParam (value = "pageSize",defaultValue = "5",required = false)Integer pageSize){

        List<TurfDto> turfDtoList=turfService.getAllProducts(pageNumber,pageSize);
        return turfDtoList;
    }
    @GetMapping("/getTurf/{id}")
    public TurfDto getTurfById(@PathVariable Integer id){
        TurfDto turfDto = turfService.getTurfById(id);
        return turfDto;
    }
    @GetMapping("/getTurfByName/{name}")
    public List<TurfDto> getTurfByName(@PathVariable String name){
        List<TurfDto> turfDto = turfService.getTurfsByName(name);
        return turfDto;
    }

    @PutMapping("/updateTurf/{id}")
    public TurfDto updateTurf(@RequestBody TurfDto turfDto,@PathVariable Integer id){

        TurfDto updatedTurf = turfService.updateTurf(id, turfDto);
        return updatedTurf;
    }

    @DeleteMapping("/deleteTurf/{id}")
    public void deleteTurfById(@PathVariable Integer id){
        turfService.deleteTurf(id);
    }

}
