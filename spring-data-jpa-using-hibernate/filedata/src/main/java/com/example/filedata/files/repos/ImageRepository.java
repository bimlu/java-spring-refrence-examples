package com.example.filedata.files.repos;

import com.example.filedata.files.entities.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
