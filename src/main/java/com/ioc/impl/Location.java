package com.ioc.impl;

import com.ioc.annotations.Inject;
import com.ioc.service.CompanyService;

public class Location {
    @Inject
    private LocationParser locationParser;
    @Inject
    private CompanyService companyService;
}
