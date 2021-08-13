package org.cannedcoffee.springboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cannedcoffee.springboot.config.auth.dto.SessionUser;
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

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){

        //show all posts
        model.addAttribute("posts", postsService.findAllDesc());

        //
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user !=null){
            model.addAttribute("loginUserName", user.getName());
        }

        // prefix and suffix already set in mustache.
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        log.info("update controller called");

        PostsResponseDto responseDto = postsService.findById(id);

        model.addAttribute("post", responseDto);

        return "posts-update";

    }





}
