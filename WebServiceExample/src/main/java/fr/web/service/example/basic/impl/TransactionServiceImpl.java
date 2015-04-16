package fr.web.service.example.basic.impl;

import fr.web.service.example.basic.TransactionService;

import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public String save() {
        return "Jersey + Spring example";
    }

}
