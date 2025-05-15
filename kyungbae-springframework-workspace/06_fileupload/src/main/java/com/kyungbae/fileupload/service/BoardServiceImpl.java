package com.kyungbae.fileupload.service;

import com.kyungbae.fileupload.dao.BoardMapper;
import com.kyungbae.fileupload.dto.AttachmentDto;
import com.kyungbae.fileupload.dto.BoardDto;
import com.kyungbae.fileupload.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final SqlSessionTemplate sqlSession;
    private final FileUtil fileUtil;

    @Override
    public int registOneFileBoard(BoardDto board, MultipartFile imageFile) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);

        // tbl_board insert
        int result = boardMapper.insertBoard(board);

        // 첨부파일이 있는지 확인
        if (result > 0 && imageFile != null && !imageFile.getOriginalFilename().equals("")) {
            // 1. 첨부파일 업로드
            Map<String, String> map = fileUtil.fileupload(imageFile);

            // 2. tbl_attachment insert (db에 기록 - 저장경로, 원본명, 실제저장된파일명(수정명), 참조게시글번호)
            AttachmentDto attach = AttachmentDto.builder()
                    .filePath(map.get("filePath"))
                    .originalName(map.get("originalFileName"))
                    .filesystemName(map.get("filesystemName"))
                    .boardNo(board.getBoardNo())
                    .build();
            result = boardMapper.insertAttach(attach);
        }

        return result;

    }

    @Override
    public int registManyFileBoard(BoardDto board, List<MultipartFile> imageFiles) {

        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);

        int result = boardMapper.insertBoard(board);

        if (result > 0 && imageFiles != null) {
            for (MultipartFile imageFile : imageFiles) {
                if (imageFile != null && !imageFile.getOriginalFilename().equals("")) {

                    // 파일 업로드
                    Map<String, String> map = fileUtil.fileupload(imageFile);

                    // db에 정보기록
                    AttachmentDto attach = AttachmentDto.builder()
                            .filePath(map.get("filePath"))
                            .originalName(map.get("originalFileName"))
                            .filesystemName(map.get("filesystemName"))
                            .boardNo(board.getBoardNo())
                            .build();
                    result += boardMapper.insertAttach(attach);
                }
            }
        }

        return result;
    }
}
