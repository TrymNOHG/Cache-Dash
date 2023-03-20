package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public record LoginReplyDTO(@NonNull String username, @NonNull String token) {

}
