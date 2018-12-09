package com.insat.controller;


import com.insat.model.Tag;
import com.insat.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class TagController {

    @Autowired
    private TagService tagService;

    @CrossOrigin(origins = "*")
    @GetMapping("/tags")
    public List getAll(){
            return tagService.getAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/tags/add")
    public Tag add(@Valid @RequestBody Tag tag){
        return tagService.addOne(tag);
    }
}
