package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.AccountEntity;
import com.hotel.hotelapi.model.AuthenticationModel;
import com.hotel.hotelapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private IEmailService emailService = new EmailServiceImpl();

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
                accountRepository.save(account);
                return authenticationModel;}
            throw new Exception("Lỗi đăng nhập");
    }

    @Override
    public AuthenticationModel register(AccountEntity account) throws Exception {
        String password = account.getPassword();
        if(isExistUsername(account.getUsername()))
            throw new Exception("Tên người dùng không hợp lệ!");
        if(password.length()<=6 || password.contains(" ")) {
            throw new Exception("Mật khẩu không đúng định dạng nhập, xin hãy kiểm tra lại!");
        }
        if(!account.getEmail().contains("@"))
            throw new Exception("Định dạng email không đúng, xin hãy nhập lại!");
        if(account.getLoginCode() != null)
            throw new Exception("Có phải bạn đang muốn đánh cắp thông tin?");

        account.setActive(false);

        String loginCode = this.generateLoginCode();
        account.setLoginCode(loginCode);

        String code = this.getRandom();
        account.setCode(code);
        String body = "Mã xác nhận Happy Hotel Service của bạn là: "+code+" ! Nếu bạn không đăng ký" +
                "Happy Hotel Service thì hãy bỏ qua email này!";
        emailService.sendEmail("Happy Hotel Team",account.getEmail(),"Confirm email",body);
        account.setCode(getRandom());

        accountRepository.save(account);

        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setUsername(account.getUsername());
        authenticationModel.setLoginCode(loginCode);
        return authenticationModel;
    }

    @Override
    public boolean checkLogin(AuthenticationModel auth){
        if(auth.getUsername().isEmpty() || auth.getLoginCode().isEmpty() || auth.getLoginCode().isBlank() || auth.getUsername().isBlank())
            return false;
        AccountEntity account = accountRepository.findAccountEntityByUsername(auth.getUsername());
        if(account == null || auth.getLoginCode().equals(account.getLoginCode()) && auth.getUsername().equals(account.getUsername()))
            return true;
        return false;
    }
    @Override
    public boolean isExistUsername(String username){
        return accountRepository.existsByUsername(username);
    }
    private String generateLoginCode(){
        String loginCode = UUID.randomUUID().toString();
        loginCode = loginCode.replaceAll("-", "");
        return loginCode;
    }
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}
