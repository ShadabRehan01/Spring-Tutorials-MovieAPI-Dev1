package com.moviepulse.movieApi.controllers;

import com.moviepulse.movieApi.Services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${project.poster}")
    private String path;

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws Exception {
        String uploadedFileName = fileService.uploadFile(path,file);
        return ResponseEntity.ok("File uploaded : "+uploadedFileName);
    }

    @GetMapping(value = "/{fileName}")
    public void serveFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
       InputStream resourceFile =  fileService.getResourceFile(path, fileName);

       response.setContentType(MediaType.IMAGE_PNG_VALUE);
       StreamUtils.copy(resourceFile,response.getOutputStream());
    }
}
