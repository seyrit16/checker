package org.example.checker.service;

import org.example.checker.exception.FailedWorkWithFileException;
import org.example.checker.exception.FileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

@Service
public class LocalFileStorageService {
    private final Path rootLocation;

    public LocalFileStorageService(@Value("${file.storage.location}")String storagePath){
        this.rootLocation = Paths.get(storagePath).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать хранилище", e);
        }
    }

    public String save(MultipartFile file) {
        try(InputStream inputStream = file.getInputStream()){
            Path destinationFile =
                    this.rootLocation.resolve(rootLocation.toAbsolutePath()+ "\\"+file.getOriginalFilename());

            Files.createDirectories(destinationFile.getParent());

            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new FailedWorkWithFileException("Ошибка при сохранении файла");
        }
    }

    public Resource load(String filename) {
        try {
            Path file = this.rootLocation.resolve(rootLocation.toAbsolutePath()+filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()){
                throw new FileNotFoundException("Такой файл не найден.");
            }
            if (resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Не получилось прочитать файл: " + filename);
            }
        }catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка загрузки файла", e);
        }
    }

    public void delete(String filename) {
        try {
            Path file = this.rootLocation.resolve(rootLocation.toAbsolutePath()+filename).normalize();

            if (Files.isDirectory(file)) {
                Files.walk(file)
                        .sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException e) {
                                throw new RuntimeException("Не удалось удалить: " + path, e);
                            }
                        });
            } else {
                Files.deleteIfExists(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при удалении: " + filename, e);
        }
    }
}
