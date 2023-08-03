package com.wxm.tesseract;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-06-12 20:40:53
 */
@Slf4j
@Service
public class TesseractService {

    public String recognizeText(File file) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("Z:\\download\\tesseract-5.3.1\\tessdata");
        tesseract.setLanguage("chi_sim");
        tesseract.setOcrEngineMode(1);
        try {
            String result = tesseract.doOCR(file);
            log.info("Recognized text: {}", result);
            return result;
        } catch (TesseractException e) {
            log.error("Error recognizing text: {}", e.getMessage());
            return null;
        }
    }
}
