package com.kyungbae.fileupload.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    // 1. 첨부파일 업로드
    public Map<String, String> fileupload(MultipartFile imageFile) {
        // 1) 저장경로 (/upload/board/yyyyMMdd)
        String filePath =  "/upload/board" + DateTimeFormatter.ofPattern("/yyyyMMdd").format(LocalDate.now());
        log.debug("file path: {}", filePath);

        File filePathDir = new File("C:" + filePath);
        if (!filePathDir.exists()) { // 해당 경로의 폴더가 없을 경우 생성
            filePathDir.mkdirs();
        }

        // 2) 파일명 수정 작업 (중복방지)
        String originalFileName = imageFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자명 추출
        String filesystemName = UUID.randomUUID().toString().replace("-", "") + ext; // 32자리 랜덤값 발생("-" 4개 포함)
        log.debug("filesystem name: {}",filesystemName);

        // 3) 업로드 (지정한 폴더에 파일을 저장
        try {
            imageFile.transferTo(new File(filePathDir, filesystemName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = Map.of(
                "filePath", filePath,
                "originalFileName", originalFileName,
                "filesystemName", filesystemName
        );

        return map;
    }

}
