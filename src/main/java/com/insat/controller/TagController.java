package com.insat.controller;


import com.insat.model.Tag;
import com.insat.service.AuthService;
import com.insat.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("api/")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private AuthService authService;

    @CrossOrigin(origins = "*")
    @GetMapping("/tags/")
    public List getAllWithAuth(@RequestHeader("Authorization") String token){
        System.out.println("get tags"+token);

        return tagService.getAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/tags/add")
    public Tag add(@Valid @RequestBody Tag tag,
                   @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("add tags"+token);
        if(authService.isValid(token))
            return tagService.addOne(tag);
        else
            return null;
    }
}
