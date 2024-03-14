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
    public Tag getTagById(long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(long id, Tag tag) {
        if (tagRepository.existsById(id)) {
            tag.setId(id);
            return tagRepository.save(tag);
        }
        return null;
    }

    @Override
    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }
    
    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
