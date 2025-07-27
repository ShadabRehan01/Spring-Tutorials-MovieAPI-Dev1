package com.moviepulse.movieApi.Services.impl;

import com.moviepulse.movieApi.Services.FileService;
import com.moviepulse.movieApi.exceptions.EmptyFileException;
import com.moviepulse.movieApi.exceptions.FileAlreadyExistException;
import com.moviepulse.movieApi.exceptions.GivenFileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws Exception {
//  Get Original File name
        String fileName = file.getOriginalFilename();

//  Create full path (directory + file name)
        String filePath = path + File.separator + fileName;

        File destinationFile = new File(filePath); //1-->
        if (destinationFile.exists()){
            throw new FileAlreadyExistException("File Already exist! please upload another file!");
        }

//  Create File Object and also Create folder if Not exists
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs(); // Create folder
        }
//   Copy the file or Upload file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName; // return uploaded File name
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
//2-->        return new FileInputStream(filePath);

        File file = new File(filePath);

        if (!file.exists()){
            throw new GivenFileNotFoundException("File with name "+fileName+" not found!");
        }
        if (file.length()==0){
            throw new EmptyFileException("The File"+fileName+" is empty!");
        }
        return new FileInputStream(file);
    }
}
