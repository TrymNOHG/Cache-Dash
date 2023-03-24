package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.NonNull;

public record UserPasswordUpdateDTO (@NonNull String username, @NonNull String oldPassword,
                                     @NonNull String newPassword) {
}
