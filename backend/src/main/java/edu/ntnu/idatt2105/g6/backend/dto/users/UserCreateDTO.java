package edu.ntnu.idatt2105.g6.backend.dto.users;

import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Date;

public record UserCreateDTO(@NonNull String username, @NonNull String password,
                            @NonNull String fullName, @NonNull String email,
                            @Nullable Date birthDate, @Nullable String phone,
                            @Nullable byte[] picture, @NonNull Role role) {
}
