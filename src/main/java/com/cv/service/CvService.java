package com.cv.service;

import com.cv.model.CvInfo;
import com.cv.repository.CvRepository;
import org.springframework.stereotype.Service;

@Service
public class CvService {

    private final CvRepository cvRepository;

    // Adicione este construtor manualmente
    public CvService(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    private static final String MAIN_DOCUMENT_ID = "main";

    public CvInfo getCv() {
        return cvRepository.findById(MAIN_DOCUMENT_ID).orElse(null);
    }

    public CvInfo updateCv(CvInfo cvInfo) {
        cvInfo.setId(MAIN_DOCUMENT_ID);
        return cvRepository.save(cvInfo);
    }
}