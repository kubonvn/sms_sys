package com.classroom.service.api;

import com.classroom.utils.DictionaryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    public final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public DictionaryHelper dicHelper;
}
