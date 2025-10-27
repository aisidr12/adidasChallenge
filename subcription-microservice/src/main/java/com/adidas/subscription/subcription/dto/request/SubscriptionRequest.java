package com.adidas.subscription.subcription.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SubscriptionRequest {

  @NotNull(message = "Email es obligatorio")
  @Email(message = "El formato del email es inválido")
  private String email;

  @NotNull(message = "La fecha de nacimiento es obligatoria")
  @Past(message = "La fecha de nacimiento debe ser en el pasado")
  private LocalDate dateOfBirth;

  @NotNull(message = "El consentimiento (consentFlag) es obligatorio")
  private Boolean consentFlag; // Debe ser un Boolean wrapper, no primitivo 'boolean' para permitir @NotNull

  @NotNull(message = "El ID de la newsletter es obligatorio")
  private String newsletterId;

  // --- Campos Opcionales ---
  // No se usa @NotNull, así que pueden ser null o omitidos en el JSON.

  private String firstName;

  // Usamos @Pattern simple para validar que solo acepte M o F (o null si es opcional)
  @Pattern(regexp = "^[MF]?$", message = "El género debe ser 'M', 'F' o nulo/vacío")
  private String gender;
}
