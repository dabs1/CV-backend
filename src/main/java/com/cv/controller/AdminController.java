package com.cv.controller;

import com.cv.model.CvInfo;
import com.cv.service.CvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/cv")
public class AdminController {

    private final CvService cvService;

    // Adicione este construtor manualmente
    public AdminController(CvService cvService) {
        this.cvService = cvService;
    }

    @PutMapping
    public ResponseEntity<CvInfo> updateEntireCv(@RequestBody CvInfo cvInfo) {
        CvInfo updatedCv = cvService.updateCv(cvInfo);
        return ResponseEntity.ok(updatedCv);
    }
}