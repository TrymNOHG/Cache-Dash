package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.NonNull;

//TODO do we token??
public record DeleteUserDTO(@NonNull String username, @NonNull String userToDelete) {
}
