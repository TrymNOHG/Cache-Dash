package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.users.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO){

            User user = userRepository.findByUsername(userUpdateDTO.username()).orElseThrow(() -> new UserNotFoundException(userUpdateDTO.username()));

            Optional<User> newUser = userRepository.findByUsername(userUpdateDTO.newUsername());
            if (newUser.isPresent()) throw new UserExistsException(userUpdateDTO.newUsername());

            user = User
                    .builder()
                    .userId(user.getUserId())
                    .username(userUpdateDTO.newUsername() != null ? userUpdateDTO.username() : user.getUsername())
                    .fullName(userUpdateDTO.fullName() != null ? userUpdateDTO.fullName() : user.getFullName())
                    .email(userUpdateDTO.email() != null ? userUpdateDTO.email() : user.getEmail())
                    .birthDate(userUpdateDTO.birthDate() != null ? userUpdateDTO.birthDate() : user.getBirthDate())
                    .phone(userUpdateDTO.phone() != null ? userUpdateDTO.phone() : user.getPhone())
                    .picture(userUpdateDTO.picture() != null ? userUpdateDTO.picture() : user.getPicture())
                    .role(userUpdateDTO.role() != null ? userUpdateDTO.role() : user.getRole())
                    .build();

            userRepository.save(user);


    }

    @Override
    public void deleteUser(UserDeletionDTO userDeletionDTO) {
        User user = userRepository.findByUsername(userDeletionDTO.username()).orElseThrow(() -> new UserNotFoundException(userDeletionDTO.username()));
        User userToDelete = userRepository.findByUsername(userDeletionDTO.userToDelete()).orElseThrow(() -> new UserNotFoundException(userDeletionDTO.userToDelete()));
        if(user.getRole() == Role.ADMIN || user.getUsername().equals(userToDelete.getUsername())){
            userRepository.delete(userToDelete);
        }
        else throw new UnauthorizedException(user.getUsername());

    }
}
