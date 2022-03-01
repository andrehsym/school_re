package ru.hogwarts.school_re.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school_re.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    Avatar findAvatar(Long id);

    void uploadAvatar(Long studentId, MultipartFile avatar) throws IOException;
}
