package com.jrinehuls.mimeemail.service;

import com.jrinehuls.mimeemail.model.B64File;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface FileService {
    ArrayList<B64File> encodeFilesB64(MultipartFile[] files);
}
