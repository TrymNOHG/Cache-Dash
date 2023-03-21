package edu.ntnu.idatt2105.g6.backend.dto.users;

import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String fullName;

    @NonNull
    private String email;
    private Date birthDate;
    private String phone;
    private byte[] picture;

    @NonNull
    private Role role;



}
