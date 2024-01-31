package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.AccountEntity;

public interface IAccountService {
    public boolean login(String usernameOrEmail, String passwd); //Return về một mã tokens
    public boolean register(AccountEntity account);
//    public boolean logout(String usernameOrEmail)
}
