package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;

public interface IUserService {

    void updateUser(UserUpdateDTO userUpdateDTO);
    void deleteUser(UserDeletionDTO userDeletionDTO);
    void banUser(UserDeletionDTO userDeletionDTO);


}
