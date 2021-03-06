package org.cannedcoffee.springboot.web;

import lombok.RequiredArgsConstructor;
import org.cannedcoffee.springboot.service.posts.PostsService;
import org.cannedcoffee.springboot.web.dto.PostsResponseDto;
import org.cannedcoffee.springboot.web.dto.PostsSaveRequestDto;
import org.cannedcoffee.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

//this annotation will create constructor and initialize the object
//with final field. So basically will do the same as @Autowired, that is less recommended
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
        public Long save(@RequestBody PostsSaveRequestDto requestDto){
            return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deleteById(@PathVariable Long id){
        postsService.delete(id);

        // to send json back to client when ajax request was successful
        return id;
    }

}
