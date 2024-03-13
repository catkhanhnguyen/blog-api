package com.blog.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.model.Tag;
import com.blog.demo.service.TagServiceImpl;

@RestController
@RequestMapping("/tags")
public class TagController {
    
    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}
