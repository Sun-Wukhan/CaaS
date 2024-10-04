package com.yeswekhan.construction_service.controller;

import com.yeswekhan.construction_service.dto.ConstructionRequest;
import com.yeswekhan.construction_service.dto.ConstructionResponse;
import com.yeswekhan.construction_service.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/construction")
@RequiredArgsConstructor
public class ConstructionController {

    private final ConstructionService constructionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createConstruction(@RequestBody ConstructionRequest constructionRequest) {
    constructionService.createConstruction(constructionRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConstructionResponse> getAllConstruction() {
        return constructionService.getAllConstruction();
    }
}
