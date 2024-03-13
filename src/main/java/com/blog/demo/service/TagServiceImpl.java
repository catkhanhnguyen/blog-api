package com.blog.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.model.Tag;
import com.blog.demo.repository.TagRepository;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
