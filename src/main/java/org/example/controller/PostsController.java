package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.dto.PostsListResponseDto;
import org.example.controller.dto.PostsResponseDto;
import org.example.controller.dto.PostsSaveRequestDto;
import org.example.controller.dto.PostsUpdateRequestDto;
import org.example.service.posts.PostsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/posts")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAll();
    }

    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
