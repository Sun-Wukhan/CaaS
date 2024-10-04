package com.yeswekhan.construction_service.service;

import com.yeswekhan.construction_service.dto.ConstructionRequest;
import com.yeswekhan.construction_service.dto.ConstructionResponse;
import com.yeswekhan.construction_service.model.Construction;
import com.yeswekhan.construction_service.repository.ConstructionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    public void createConstruction(ConstructionRequest constructionRequest) {
        Construction construction = Construction.builder()
                .name(constructionRequest.getName())
                .description(constructionRequest.getDescription())
                .price(constructionRequest.getPrice())
                .build();

        constructionRepository.save(construction);
        log.info("Construction: {} is saved", construction.getId());
    }

    public List<ConstructionResponse> getAllConstruction() {
        List<Construction> constructions =  constructionRepository.findAll();

        return constructions.stream().map(this::mapToConstructionResponse).toList();

    }

    private ConstructionResponse mapToConstructionResponse(Construction construction) {
        return ConstructionResponse.builder()
                .id(construction.getId())
                .name(construction.getName())
                .description(construction.getDescription())
                .price(construction.getPrice())
                .build();
    }
}
