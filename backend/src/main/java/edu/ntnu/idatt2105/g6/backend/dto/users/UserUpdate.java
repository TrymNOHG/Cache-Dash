package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

public record UserUpdate(@NonNull String username,
                         @Nullable String newUsername,
                         @Nullable String fullName,
                         @Nullable String email,
                         @Nullable Date birthDate,
                         @Nullable String phone,
                         @Nullable byte[] picture) {
}
