package com.sellinglaptop.service;

import com.sellinglaptop.dao.AccountDAO;
import com.sellinglaptop.model.AccountModel;

import javax.inject.Inject;
import java.util.List;

public class AccountService {
    @Inject
    private AccountDAO accountDAO;
    public AccountModel findByUsernameAndPassword(AccountModel accountModel){
        return accountDAO.findByUsernameAndPassword(accountModel.getUsername(),accountModel.getPassword());
    }

    public AccountModel findByUsername(String username){
        return accountDAO.findByUsername(username);
    }

    public List<AccountModel> findAll(){
        return accountDAO.finAll();
    }
    public AccountModel findOne(int id){
        return accountDAO.findOne(id);
    }
    public AccountModel save(AccountModel accountModel){
        int id = accountDAO.save(accountModel);
        return accountDAO.findOne(id);
    }
    public AccountModel update(AccountModel accountModel){
        accountDAO.update(accountModel);
        return accountDAO.findOne(accountModel.getId());
    }
    public void delete(int id){
        accountDAO.delete(id);
    }

}
