package com.adidas.challenge.email.email.service;

import com.adidas.challenge.email.email.dto.SubscriptionEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
  public void sendSubscriptionCreatedEmail(SubscriptionEventResponse dto) {
    // Mock behaviour: log only
    logger.info("Mock send email the info is {}",dto);
    //logger.info("Mock send email to {} for subscription id={} plan={}", dto.email(), dto.getSubscriptionId(), dto.getPlanName());
  }

  //
//  private final JavaMailSender mailSender;
//
//  public EmailService(JavaMailSender mailSender) {
//    this.mailSender = mailSender;
//  }
//
//  public void processSubscriptionEvent(SubscriptionEventDTO event) {
//    String subject = getEmailSubject(event.getEventType());
//    String body = getEmailBody(event);
//
//    sendEmail(event.getUserEmail(), subject, body);
//  }
//
//  private void sendEmail(String to, String subject, String body) {
//    try {
//      SimpleMailMessage message = new SimpleMailMessage();
//      message.setTo(to);
//      message.setSubject(subject);
//      message.setText(body);
//      message.setFrom("noreply@tuempresa.com");
//
//      mailSender.send(message);
//      logger.info("Email enviado a: {}", to);
//
//    } catch (Exception e) {
//      logger.error("Error enviando email a {}: {}", to, e.getMessage(), e);
//      throw new RuntimeException("Error enviando email", e);
//    }
//  }
//
//  private String getEmailSubject(String eventType) {
//    return switch (eventType) {
//      case "CREATED" -> "Bienvenido - Suscripción Creada";
//      case "UPDATED" -> "Suscripción Actualizada";
//      case "CANCELLED" -> "Suscripción Cancelada";
//      default -> "Notificación de Suscripción";
//    };
//  }
//
//  private String getEmailBody(SubscriptionEventDTO event) {
//    return switch (event.getEventType()) {
//      case "CREATED" -> String.format(
//          "Hola %s,\n\n" +
//              "Tu suscripción al plan %s ha sido creada exitosamente.\n" +
//              "ID de suscripción: %s\n\n" +
//              "Gracias por confiar en nosotros.\n\n" +
//              "Saludos,\nEl equipo",
//          event.getUserName(), event.getPlanName(), event.getSubscriptionId()
//      );
//      case "UPDATED" -> String.format(
//          "Hola %s,\n\n" +
//              "Tu suscripción %s ha sido actualizada.\n\n" +
//              "Saludos,\nEl equipo",
//          event.getUserName(), event.getSubscriptionId()
//      );
//      case "CANCELLED" -> String.format(
//          "Hola %s,\n\n" +
//              "Tu suscripción %s ha sido cancelada.\n" +
//              "Esperamos verte pronto de nuevo.\n\n" +
//              "Saludos,\nEl equipo",
//          event.getUserName(), event.getSubscriptionId()
//      );
//      default -> "Notificación sobre tu suscripción.";
//    };
//  }
}
