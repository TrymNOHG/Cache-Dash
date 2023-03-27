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

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final UserRepository userRepository;

    @Transactional
    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO, byte[] profilePicture){

        User user = userRepository.findByUsername(userUpdateDTO.username()).orElseThrow(() -> new UserNotFoundException(userUpdateDTO.username()));

        Optional<User> newUser = userRepository.findByUsername(userUpdateDTO.newUsername());
        if (newUser.isPresent()) throw new UserExistsException(userUpdateDTO.newUsername());

        user.setUsername(userUpdateDTO.newUsername() != null ? userUpdateDTO.username() : user.getUsername());
        user.setFullName(userUpdateDTO.fullName() != null ? userUpdateDTO.fullName() : user.getFullName());
        user.setEmail(userUpdateDTO.email() != null ? userUpdateDTO.email() : user.getEmail());
        user.setBirthDate(userUpdateDTO.birthDate() != null ? userUpdateDTO.birthDate() : user.getBirthDate());
        user.setPhone(userUpdateDTO.phone() != null ? userUpdateDTO.phone() : user.getPhone());
        user.setPicture(userUpdateDTO.picture() != null ? userUpdateDTO.picture() : user.getPicture());
        user.setRole(userUpdateDTO.role() != null ? userUpdateDTO.role() : user.getRole());
        user.setBookmarks(user.getBookmarks());
        user.setListedItems(user.getListedItems());
        user.setPicture(profilePicture != null ? profilePicture : user.getPicture());

        userRepository.save(user);

    }

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

    @Override
    public UserLoadDTO loadUserByUsername(String username) {
        return UserMapper.userLoadDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
    }

    public User loadByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
