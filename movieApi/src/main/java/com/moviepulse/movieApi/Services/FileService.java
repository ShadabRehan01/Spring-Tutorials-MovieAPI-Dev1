package com.moviepulse.movieApi.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileService {

    String uploadFile(String path, MultipartFile file) throws Exception; // upload the File

    InputStream getResourceFile(String path, String fileName) throws FileNotFoundException; // download or Fetch the File

}
