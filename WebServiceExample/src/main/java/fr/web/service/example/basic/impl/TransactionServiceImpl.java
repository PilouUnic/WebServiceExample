package fr.web.service.example.basic.impl;

import org.springframework.stereotype.Component;

import fr.web.service.example.basic.TransactionService;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Override
    public String save() {
        return "Jersey + Spring example";
    }

}
