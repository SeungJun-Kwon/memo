package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/memos")
public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping()
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDTO) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDTO);

        // Memo Max ID Check
        Long maxId = !memoList.isEmpty() ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        return new MemoResponseDto(memo);
    }

    @GetMapping()
    public List<MemoResponseDto> getMemos() {
        // Map to List
        return memoList.values().stream()
                .map(MemoResponseDto::new).toList();
    }

    @PutMapping("/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)) {
            Memo memo = memoList.get(id);

            memo.update(requestDto);

            return memo.getId();
        }
        else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        if(memoList.containsKey(id)) {
            memoList.remove(id);
            
            return id;
        }
        else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
