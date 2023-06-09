package edu.ntnu.idatt2105.g6.backend.dto.users;

import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;

public record UserUpdateDTO(@NonNull String username,
                            @Nullable String newUsername,
                            @Nullable String fullName,
                            @Nullable String email,
                            @Nullable Date birthDate,
                            @Nullable String phone,
                            @Nullable byte[] picture,
                            @Nullable Role role) {
}
