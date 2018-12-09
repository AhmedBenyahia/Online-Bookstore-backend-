package com.insat.serviceImpl;


import com.insat.model.Tag;
import com.insat.repository.TagRepository;
import com.insat.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagRepository tagRepository;

    public List getAll() {
        return tagRepository.findAll();
    }


    public Tag addOne(Tag tag) {
        return tagRepository.save(tag);
    }
}
