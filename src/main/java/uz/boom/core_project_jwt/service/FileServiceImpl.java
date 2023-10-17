package uz.boom.core_project_jwt.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.boom.core_project_jwt.entity.Attachment;
import uz.boom.core_project_jwt.exception.BadRequestException;
import uz.boom.core_project_jwt.exception.NotFoundException;
import uz.boom.core_project_jwt.repository.AttachmentRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl {


    private final AttachmentRepository attachmentRepository;

    public Long fileUpload(@NonNull MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename();
            Long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = Attachment.builder()
                    .fileOriginalName(originalName)
                    .size(size)
                    .contentType(contentType)
                    .content(file.getBytes())
                    .build();
            Attachment savedAttachment = attachmentRepository.save(attachment);

            if (savedAttachment.getId() != 0) {
                return savedAttachment.getId();
            } else {
                throw new BadRequestException("FILE DIDN'T SAVE");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Attachment downloadFile(Long fileId) {
        return attachmentRepository.findById(fileId).orElseThrow(() -> new NotFoundException("THERE IS NO FILE WITH THIS ID"));
    }


}
