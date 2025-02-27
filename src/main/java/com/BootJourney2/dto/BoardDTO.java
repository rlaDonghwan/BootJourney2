package com.BootJourney2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString // DTO 내부 정보를 printOut 할 수 있게 해줌
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createAt;

    private int fileAttached;
    private List<MultipartFile> boardFile;
    private List<BoardFileDTO> boardFileDTOList; // 🔥 파일 목록을 저장할 필드 추가


}
