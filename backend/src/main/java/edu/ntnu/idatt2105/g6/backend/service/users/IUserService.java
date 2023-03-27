package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;

public interface IUserService {

    void updateUser(UserUpdateDTO userUpdateDTO, byte[] profilePicture);
    void deleteUser(UserDeletionDTO userDeletionDTO);

    UserLoadDTO loadUserByUsername(String username);


}
