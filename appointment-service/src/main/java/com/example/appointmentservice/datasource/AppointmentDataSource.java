package com.example.appointmentservice.datasource;

import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AppointmentDataSource {
    private List<Appointment> appointments;

    public AppointmentDataSource() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        appointments = Stream.of(
                new Appointment("1", "BMW",
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        new User("1", "dada", "dada", "dada.dada@gmail.com", "dadada", "mechanic")
                ),
                new Appointment("2", "Audi",
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        new User("2","Vlad", "Codina", "Vlad.Codina@gmail.com", "Strada Inimii", "car owner")
                ),
                new Appointment("3", "Skoda",
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        new User("3","Ionut", "Dulla", "Ionut.Dulla@gmail.com", "Strada Kosuth Lajos", "car owner")
                ),
                new Appointment("4", "Lada",
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        LocalDateTime.parse("2022-05-02 15:00", formatter),
                        new User("4","Emil", "Constantinescu", "Emil.Constantinescu@gmail.com", "Strada Harghitei", "mechanic")
                )
        ).collect(Collectors.toList());

    }

    public List<Appointment> getAllAppointments() {
        System.out.println("get appointment");
        return appointments;
    }

    public Optional<Appointment> findAppointmentById(String id) {
        return appointments.stream().filter(appointment -> appointment.getId().equals(id)).findFirst();
    }

    public Optional<Appointment> findAppointmentByStartDate(LocalDateTime startDate) {
        return appointments.stream().filter(appointment -> appointment.getStartDate().equals(startDate)).findFirst();
    }


    public Appointment saveAppointment(Appointment appointment) {
        System.out.println("save appointment");
        findAppointmentById(appointment.getId()).ifPresent(i -> {
            throw new RuntimeException("Appointment with id " + i.getId() + " already exists!");
        });
        findAppointmentByStartDate(appointment.getStartDate()).ifPresent(i -> {
            throw new RuntimeException("Appointment with start date " + i.getStartDate() + " already exists!");
        });
        this.appointments.add(appointment);

        return appointment;
    }

    public Appointment updateAppointment(Appointment appointment) {
        System.out.println("In 1002 update");
        Appointment existingAppointment = findAppointmentById(appointment.getId()).orElseThrow(
                () -> new RuntimeException("Appointment with id " + appointment.getId() + " not found!"));

        int index = appointments.indexOf(existingAppointment);

        appointments.get(index).setCar(appointment.getCar());
        appointments.get(index).setCarOwner(appointment.getCarOwner());
        appointments.get(index).setEndDate(appointment.getEndDate());
        appointments.get(index).setStartDate(appointment.getStartDate());

        return appointment;
    }

    public boolean deleteAppointment(String id) {
        Appointment userToDelete = findAppointmentById(id).orElseThrow(
                () -> new RuntimeException("Appointment with id " + id + " not found!"));

        return appointments.remove(userToDelete);
    }
}
