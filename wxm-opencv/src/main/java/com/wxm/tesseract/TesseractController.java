package com.wxm.tesseract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-06-12 20:42:15
 */
@Slf4j
@RestController
@RequestMapping("/tesseract")
public class TesseractController {

    private final TesseractService tesseractService;

    public TesseractController(TesseractService tesseractService) {
        this.tesseractService = tesseractService;
    }

    @PostMapping("/recognize")
    public ResponseEntity<String> recognizeText(@RequestParam("file") MultipartFile file) {
        try {
            File convertedFile = convertMultipartFileToFile(file);
            String result = tesseractService.recognizeText(convertedFile);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            log.error("Error converting multipart file to file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
}

