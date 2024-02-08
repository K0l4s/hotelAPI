package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.AccountEntity;
import com.hotel.hotelapi.exception.MyException;
import com.hotel.hotelapi.model.AuthenticationModel;

public interface IAccountService {
    public AuthenticationModel login(String username, String passwd) throws Exception; //Return về một mã token
    public AuthenticationModel register(AccountEntity account) throws Exception;

    boolean checkLogin(AuthenticationModel auth);

    boolean isExistUsername(String username);
//    public boolean logout(String usernameOrEmail)
}
