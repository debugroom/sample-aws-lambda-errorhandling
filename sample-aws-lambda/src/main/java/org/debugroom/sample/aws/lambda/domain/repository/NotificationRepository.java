package org.debugroom.sample.aws.lambda.domain.repository;

import java.util.Map;

public interface NotificationRepository {

    public void save(Map<String, Object> message);

}
