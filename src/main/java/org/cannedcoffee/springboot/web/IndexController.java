package org.cannedcoffee.springboot.web;

import lombok.RequiredArgsConstructor;
import org.cannedcoffee.springboot.domain.posts.PostsRepository;
import org.cannedcoffee.springboot.service.posts.PostsService;
import org.cannedcoffee.springboot.web.dto.PostsResponseDto;
import org.cannedcoffee.springboot.web.dto.PostsUpdateRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        // prefix and suffix already set in mustache.
        logger.info("index");
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        logger.info("update controller called");

        PostsResponseDto responseDto = postsService.findById(id);

        model.addAttribute("post", responseDto);

        return "posts-update";

    }


}
