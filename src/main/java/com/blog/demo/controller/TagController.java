package com.blog.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.model.Tag;
import com.blog.demo.service.TagServiceImpl;

@RestController
@RequestMapping("/tags")
public class TagController {
    
    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") long id) {
        return new ResponseEntity<>(tagService.getTagById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag tagCreate = tagService.createTag(tag);
        if (tagCreate != null) {
            return new ResponseEntity<>(tagCreate, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tag) {
        Tag updatedTag = tagService.updateTag(id, tag);
        if (updatedTag != null) {
            return new ResponseEntity<>(updatedTag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") long id) {
        if (getTagById(id) != null) {
            tagService.deleteTag(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Tag>> getAllTags() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

}
