package com.kyungbae.fileupload.dao;

import com.kyungbae.fileupload.dto.AttachmentDto;
import com.kyungbae.fileupload.dto.BoardDto;

public interface BoardMapper {

    int insertBoard(BoardDto boardDto);

    int insertAttach(AttachmentDto attachmentDto);

}
