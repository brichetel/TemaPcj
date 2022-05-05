package com.example.appointmentservice.model;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppointmentSummary {
    @Id
    @NotNull
    private String id;

    @NotNull
    private Appointment appointment;

    @NotNull
    private User mechanic;

    @NotNull
    private String comment;

    @NotNull
    private Double totalCost;
}