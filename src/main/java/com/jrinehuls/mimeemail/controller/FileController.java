package com.jrinehuls.mimeemail.controller;

import com.jrinehuls.mimeemail.model.B64File;
import com.jrinehuls.mimeemail.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
public class FileController {

    FileService fileService;

    @PostMapping(value = "/b64")
    public ResponseEntity<ArrayList<B64File>> encodeFilesB64(@RequestParam(value = "files") MultipartFile[] files) {
        return new ResponseEntity<>(fileService.encodeFilesB64(files), HttpStatus.OK);
    }

    @PostMapping(value = "/png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> encodeFilesB64Img(@RequestParam(value = "files") MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }


}
