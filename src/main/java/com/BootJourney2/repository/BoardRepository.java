package com.BootJourney2.repository;

import com.BootJourney2.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
    }

    public List<BoardDTO> findAll(){
        log.info("findAll");
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        log.info("updateHits");
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) {
        log.info("findById");
        return sql.selectOne("Board.findById", id);
    }

    public void update(BoardDTO boardDTO) {
        log.info("update");
        sql.update("Board.update", boardDTO);
    }

    public void delete(Long id) {
        log.info("delete");
        sql.delete("Board.delete", id);
    }
}
