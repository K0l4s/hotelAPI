package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService{
    @Override
    public boolean login(String usernameOrEmail, String passwd) {
        return false;
    }

    @Override
    public boolean register(AccountEntity account) {
        return false;
    }
}
