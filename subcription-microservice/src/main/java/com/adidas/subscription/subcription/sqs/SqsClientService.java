package com.adidas.subscription.subcription.sqs;

import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.exception.SqsExceptionCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@Slf4j
public class SqsClientService {

  private final SqsClient sqsClient;
  @Value("${aws.sqs.queue.subscription-events}")
  private String subscriptionEventsQueueUrl;

  public SqsClientService(SqsClient sqsClient) {
    this.sqsClient = sqsClient;
  }

  public void sendMessage(SubscriptionResponse body) {
    try {
      SendMessageRequest req = SendMessageRequest
          .builder()
          .queueUrl(subscriptionEventsQueueUrl)
          .messageBody(body.toString())
          .build();
      sqsClient.sendMessage(req);
    } catch (SdkException e) {
      log.error("Failed to send SQS {}", e.getMessage());
      throw new SqsExceptionCustom(e.getMessage());
    }
  }
}
