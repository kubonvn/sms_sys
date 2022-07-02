package com.sms_sys.service.api;

import com.sms_sys.utils.DictionaryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    public final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public DictionaryHelper dicHelper;
}
