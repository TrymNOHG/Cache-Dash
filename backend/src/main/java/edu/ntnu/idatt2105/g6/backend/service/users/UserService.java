package edu.ntnu.idatt2105.g6.backend.service.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.exists.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.users.UserMapper;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 This service class handles the business logic for user-related operations.
 It implements the IUserService interface.
 It provides methods for updating and deleting users, as well as loading a user by username.
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    /**
     * The UserRepository used to access the user database.
     */
    private final UserRepository userRepository;

    /**
     * This method updates a user's information.
     * It first checks if the given user exists in the database. It then checks if the new username already exists,
     * and throws a UserExistsException if it does. It then creates a new User object with the updated information and saves
     * it to the database.
     *
     * @param userUpdateDTO A UserUpdateDTO object containing the user's current username, the new username (if any),
     *                      and other updated information.
     * @throws UserNotFoundException If the user is not found in the database.
     * @throws UserExistsException If the new username already exists in the database.
     */
    @Transactional
    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO){

            User user = userRepository.findByUsername(userUpdateDTO.username()).orElseThrow(() -> new UserNotFoundException(userUpdateDTO.username()));

            Optional<User> newUser = userRepository.findByUsername(userUpdateDTO.newUsername());
            if (newUser.isPresent()) throw new UserExistsException(userUpdateDTO.newUsername());

            user = User
                    .builder()
                    .userId(user.getUserId())
                    .password(user.getPassword())
                    .username(userUpdateDTO.newUsername() != null ? userUpdateDTO.username() : user.getUsername())
                    .fullName(userUpdateDTO.fullName() != null ? userUpdateDTO.fullName() : user.getFullName())
                    .email(userUpdateDTO.email() != null ? userUpdateDTO.email() : user.getEmail())
                    .birthDate(userUpdateDTO.birthDate() != null ? userUpdateDTO.birthDate() : user.getBirthDate())
                    .phone(userUpdateDTO.phone() != null ? userUpdateDTO.phone() : user.getPhone())
                    .picture(userUpdateDTO.picture() != null ? userUpdateDTO.picture() : user.getPicture())
                    .role(userUpdateDTO.role() != null ? userUpdateDTO.role() : user.getRole())
                    .bookmarks(user.getBookmarks())
                    .listedItems(user.getListedItems())
                    .build();

            System.out.println(user);
            userRepository.save(user);


    }

    /**
     Deletes a user.

     @param userDeletionDTO The DTO containing the username of the user performing the action and the username of the user to delete.
     @throws UserNotFoundException If either of the users is not found in the database.
     @throws UnauthorizedException If the user performing the action is not an admin or is trying to delete themselves.
     */
    @Transactional
    @Override
    public void deleteUser(UserDeletionDTO userDeletionDTO) {
        User user = userRepository.findByUsername(userDeletionDTO.username()).orElseThrow(() -> new UserNotFoundException(userDeletionDTO.username()));
        User userToDelete = userRepository.findByUsername(userDeletionDTO.userToDelete()).orElseThrow(() -> new UserNotFoundException(userDeletionDTO.userToDelete()));
        if(user.getRole() == Role.ADMIN || user.getUsername().equals(userToDelete.getUsername())){
            userRepository.delete(userToDelete);
        }
        else throw new UnauthorizedException(user.getUsername());

    }

    /**
     Loads a user's DTO information.

     @param username The username of the user to load.
     @return The DTO containing the user's information.
     @throws UsernameNotFoundException If the user is not found in the database.
     */
    @Override
    public UserLoadDTO loadUserDTOByUsername(String username) {
        return UserMapper.userLoadDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
    }

    /**
     Loads a user's information.
     @param username The username of the user to load.
     @return The user object.
     @throws UsernameNotFoundException If the user is not found in the database.
     */
    public User loadByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
