package com.cv.controller;

import com.cv.model.CvInfo;
import com.cv.service.CvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cv")
public class CvController {

    private final CvService cvService;

    // Adicione este construtor manualmente
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping
    public ResponseEntity<CvInfo> getCv() {
        CvInfo cvInfo = cvService.getCv();
        if (cvInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cvInfo);
    }
}