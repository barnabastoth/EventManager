package application.controller;

import application.dao.AccountDaoJPA;

public class AccountController {

    private AccountDaoJPA accountDaoJPA;

    public AccountController(AccountDaoJPA accountDaoJPA) {
        this.accountDaoJPA = accountDaoJPA;
    }


}
