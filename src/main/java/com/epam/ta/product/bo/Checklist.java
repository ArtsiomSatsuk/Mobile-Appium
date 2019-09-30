package com.epam.ta.product.bo;

import com.opencsv.bean.CsvBindByName;

public class Checklist {

    @CsvBindByName
    private String name;

    public String getName() {
        return name;
    }

}