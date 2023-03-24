package edu.ntnu.idatt2105.g6.backend.dto.users;

import lombok.NonNull;

public record UserLoginDTO(@NonNull String username, @NonNull String password) {

}
