package uz.boom.core_project_jwt.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.boom.core_project_jwt.entity.Attachment;
import uz.boom.core_project_jwt.response.DataDTO;
import uz.boom.core_project_jwt.service.FileServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileServiceImpl service;

    @PostMapping(value = "/api/v1/file/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DataDTO<Long>> uploadFile(@RequestParam("picture") MultipartFile request) {
        Long fileId = service.fileUpload(request);
        return ResponseEntity.ok(
                new DataDTO<>(fileId));
    }

    @GetMapping(value = "/api/v1/file/download/{id}")
    public ResponseEntity<DataDTO<Void>> downloadFile(@PathVariable("id") Long fileId, HttpServletResponse response) throws IOException {
        Attachment attachment = service.downloadFile(fileId);

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
        response.setContentType(attachment.getContentType());

        FileCopyUtils.copy(attachment.getContent(), response.getOutputStream());

        return ResponseEntity.ok(new DataDTO<>());
    }


}
