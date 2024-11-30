package com.assemblyconsultoria.baterponto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginForm {

    @NotBlank(message = "O nome de usuário ou e-mail é obrigatório.")
    private String usernameOrEmail;

    @NotBlank(message = "A senha é obrigatória.")
    @Pattern(
        regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
        message = "A senha deve ter no mínimo 8 caracteres e incluir pelo menos um caractere especial."
    )
    private String password;

    // Getters e Setters
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
