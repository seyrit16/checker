package org.example.checker.controller;

import org.example.checker.service.LocalFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {
    private final LocalFileStorageService fileStorageService;

    @Autowired
    public FileController(LocalFileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("")
    public ResponseEntity<Resource> getFile(@RequestParam String filename) {
        Resource resource = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
