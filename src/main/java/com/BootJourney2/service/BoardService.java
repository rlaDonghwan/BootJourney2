package com.BootJourney2.service;

import com.BootJourney2.dto.BoardDTO;
import com.BootJourney2.dto.BoardFileDTO;
import com.BootJourney2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private static final String UPLOAD_DIR = "/Users/donghwan/Documents/code/BootJourney2/src/main/resources/upload_files/"; // ✅ 절대경로 수정

    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            // 파일 없음
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        } else {
            // 파일이 존재함
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.save(boardDTO);

            // 디렉토리 없으면 생성
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("📁 저장 디렉토리 생성: " + UPLOAD_DIR);
            }

            // 파일 저장 처리
            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
                String originalFilename = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = UPLOAD_DIR + storedFileName; // ✅ 파일 저장 경로 수정

                // 파일 저장
                boardFile.transferTo(new File(savePath));
                System.out.println("✅ 파일 저장 완료: " + savePath);

                // DB 저장
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFilename);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());

                boardRepository.saveFile(boardFileDTO);
            }
        }
    }

    public List<BoardDTO> findAll() {
        List<BoardDTO> boardList = boardRepository.findAll();
        for (BoardDTO board : boardList) {
            if (board.getFileAttached() == 1) {  // fileAttached가 1이면 파일 리스트 불러오기
                List<BoardFileDTO> fileDTOList = boardRepository.findFile(board.getId());
                board.setBoardFileDTOList(fileDTOList); // boardDTO에 파일 리스트 추가
            }
        }
        return boardList;
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        BoardDTO boardDTO = boardRepository.findById(id);
        if (boardDTO.getFileAttached() == 1) {  // fileAttached가 1이면 파일 리스트 불러오기
            List<BoardFileDTO> fileDTOList = boardRepository.findFile(id);
            System.out.println("✅ 파일 리스트: " + fileDTOList); // 확인 로그 추가
            boardDTO.setBoardFileDTOList(fileDTOList);
        }
        return boardDTO;
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
    }
}