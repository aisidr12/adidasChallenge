package com.adidas.challenge.email.email.service;

import com.adidas.challenge.email.email.dto.SubscriptionEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendSubscriptionCreatedEmail(SubscriptionEventResponse dto) {
        logger.info("Mock send email the info is {}", dto);
    }
}
