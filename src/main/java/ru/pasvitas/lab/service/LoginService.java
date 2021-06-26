package ru.pasvitas.lab.service;

import ru.pasvitas.lab.model.User;

public interface LoginService {
    User authorize(String username, String password);
}
