package com.govey.service.posts.application;

import com.govey.service.posts.domain.FileEntity;
import com.govey.service.posts.infrastructure.FileRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public FileEntity saveFile(FileEntity file) {
        FileEntity entity = fileRepository.save(file);
        fileRepository.flush();
        return entity;
    }

    @Transactional
    public Optional<FileEntity> retrieve(UUID id) {
        return fileRepository.findById(id);
    }

    @Transactional
    public FileEntity getFile(UUID id) {
        FileEntity file = fileRepository.findById(id).get();
        return file;
    }
}