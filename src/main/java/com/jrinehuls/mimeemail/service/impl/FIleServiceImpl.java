package com.jrinehuls.mimeemail.service.impl;

import com.jrinehuls.mimeemail.model.B64File;
import com.jrinehuls.mimeemail.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class FIleServiceImpl implements FileService {

    @Override
    public ArrayList<B64File> encodeFilesB64(MultipartFile[] files) {
        ArrayList<B64File> b64Files = new ArrayList<>();
        for (MultipartFile file: files) {
            try {
                byte[] bytes = Base64.getEncoder().encode(file.getBytes());
                b64Files.add(new B64File(new String(bytes)));
            } catch (IOException e) {
                throw new MultipartException("Problem with: " + file.getOriginalFilename() + ". Here's some info: " +
                        e.getMessage());
            }
        }
        return b64Files;
    }

}
