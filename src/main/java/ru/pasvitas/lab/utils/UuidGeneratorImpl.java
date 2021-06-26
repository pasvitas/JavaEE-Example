package ru.pasvitas.lab.utils;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UuidGeneratorImpl implements UuidGenerator {

    @Override
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
