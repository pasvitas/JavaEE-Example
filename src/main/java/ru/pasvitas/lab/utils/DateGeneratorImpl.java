package ru.pasvitas.lab.utils;

import java.util.Date;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DateGeneratorImpl implements DateGenerator {

    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
