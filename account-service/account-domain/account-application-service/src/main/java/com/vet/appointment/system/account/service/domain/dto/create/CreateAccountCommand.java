package com.vet.appointment.system.account.service.domain.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class CreateAccountCommand {

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Please enter a valid email!")
    private final String email;
    @NotBlank(message = "Password must not be blank!")
//    @Max(value = 50)
    private final String password;
    @NotBlank(message = "First name must not be blank!")
//    @Max(value = 50)
    private final String firstName;
    @NotBlank(message = "Last name must not be blank!")
//    @Max(value = 50)
    private final String lastName;

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

    public CreateAccountCommand(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private CreateAccountCommand(Builder builder) {
        email = builder.email;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private @NotBlank @Email String email;
        private @NotBlank @Max(value = 50) String password;
        private @NotBlank @Max(value = 50) String firstName;
        private @NotBlank @Max(value = 50) String lastName;

        private Builder() {
        }

        public Builder email(@NotBlank @Email String val) {
            email = val;
            return this;
        }

        public Builder password(@NotBlank @Max(value = 50) String val) {
            password = val;
            return this;
        }

        public Builder firstName(@NotBlank @Max(value = 50) String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(@NotBlank @Max(value = 50) String val) {
            lastName = val;
            return this;
        }

        public CreateAccountCommand build() {
            return new CreateAccountCommand(this);
        }
    }
}
