package com.hotel.hotelapi.controller;

import com.hotel.hotelapi.entity.AccountEntity;
import com.hotel.hotelapi.exception.MyException;
import com.hotel.hotelapi.model.AuthenticationModel;
import com.hotel.hotelapi.service.AccountServiceImpl;
import com.hotel.hotelapi.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    private IAccountService accountService = new AccountServiceImpl();

    @PostMapping("/login")
    public AuthenticationModel login(@RequestParam String username, @RequestParam String password) throws Exception{
        return accountService.login(username, password);
//        return null;
    }
    @GetMapping("register")
    public AuthenticationModel regiser(@RequestBody AccountEntity account) throws Exception {
        return accountService.register(account);
    }

    @PostMapping("checkLogin")
    public boolean checkLogin(@RequestBody AuthenticationModel authenticationModel){
        return accountService.checkLogin(authenticationModel);
    }
}
