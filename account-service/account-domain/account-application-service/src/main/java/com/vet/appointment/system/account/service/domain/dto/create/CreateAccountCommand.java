package com.vet.appointment.system.account.service.domain.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class CreateAccountCommand {

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Please enter a valid email!")
    private String email;
    @NotBlank(message = "Password must not be blank!")
    private String password;
    @NotBlank(message = "First name must not be blank!")
    private String firstName;
    @NotBlank(message = "Last name must not be blank!")
    private String lastName;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public CreateAccountCommand() {}

    public CreateAccountCommand(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
