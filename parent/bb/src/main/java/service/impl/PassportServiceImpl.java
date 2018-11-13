package service.impl;

import service.PassportService;

public class PassportServiceImpl implements PassportService {
    @Override
    public void login(String name) {
        System.out.println(name+" login");
    }
}
