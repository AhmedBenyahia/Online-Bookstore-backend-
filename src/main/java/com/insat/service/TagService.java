package com.insat.service;

import com.insat.model.Tag;

import java.util.List;

public interface TagService {

     List getAll();
     Tag addOne(Tag tag);

}
