package org.debugroom.sample.aws.lambda.domain.service;

import java.util.Locale;

import org.debugroom.sample.aws.lambda.common.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import org.debugroom.sample.aws.lambda.common.exception.BusinessException;

@Service
public class SampleServiceImpl implements SampleService{

    @Autowired
    MessageSource messageSource;

    @Override
    public void executeThrowsBusinessException() throws BusinessException {
        throw new BusinessException("BE0001", messageSource.getMessage("BE0001", null, Locale.getDefault()));
    }

    @Override
    public void executeThrowsSystemException() {
        throw new SystemException("SE0001", messageSource.getMessage("SE0001", null, Locale.getDefault()));
    }

}
