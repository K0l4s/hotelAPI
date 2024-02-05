package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.AccountEntity;
import com.hotel.hotelapi.exception.MyException;
import com.hotel.hotelapi.model.AuthenticationModel;
import com.hotel.hotelapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AuthenticationModel login(String username, String passwd) throws Exception{
            if(accountRepository.countAccountEntitiesByUsernameAndPassword(username,passwd)>0){
                AuthenticationModel authenticationModel = new AuthenticationModel();
                String loginCode = generateLoginCode();
                authenticationModel.setLoginCode(loginCode);
                authenticationModel.setUsername(username);

                AccountEntity account = accountRepository.findAccountEntityByUsername(username);
                if(!account.isActive())
                    throw new Exception("Tài khoản chưa kích hoạt hoặc bị khoá!");
                account.setLoginCode(loginCode);
//                account.setOnline(true);
                accountRepository.save(account);
                return authenticationModel;}
            throw new Exception("Lỗi đăng nhập");
    }

    @Override
    public AuthenticationModel register(AccountEntity account) throws Exception {
        String password = account.getPassword();
        if(password.length()<=6 || password.contains(" ")) {
            throw new Exception("Mật khẩu không đúng định dạng nhập, xin hãy kiểm tra lại!");
//            return null;
        }
        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setUsername("pr");
        return authenticationModel;

    }

    private String generateLoginCode(){
        String token = UUID.randomUUID().toString();
        token = token.replaceAll("-", "");
        return token;
    }
}
