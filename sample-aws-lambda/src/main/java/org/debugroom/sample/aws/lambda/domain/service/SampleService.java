package org.debugroom.sample.aws.lambda.domain.service;

import org.debugroom.sample.aws.lambda.common.exception.BusinessException;

public interface SampleService {

    public void executeThrowsBusinessException() throws BusinessException;
    public void executeThrowsSystemException();

}
