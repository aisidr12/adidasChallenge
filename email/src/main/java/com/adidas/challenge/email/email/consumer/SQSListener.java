package com.adidas.challenge.email.email.consumer;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SQSListener {

    @SqsListener("subscription-events")
    public void receiveMessage(String messageFromSQS){
        log.info("message receive from SQS is {}", messageFromSQS);
    }

}
