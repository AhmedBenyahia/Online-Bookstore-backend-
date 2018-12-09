package com.insat.service;

public interface PersonneService {

    boolean singout(String username,String password);
    boolean singin(String username,String password);
    boolean disconnect();
}
