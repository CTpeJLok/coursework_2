package com.sport_objects.services;

import com.sport_objects.entities.Role;
import com.sport_objects.entities.User;
import com.sport_objects.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAll(String searchKeyword) {
        return userRepository.searchKeyword(searchKeyword);
    }

    public boolean save(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            return false;

        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getUserRoleOrCreate());
        user.setRoles(roles);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public void tryUpdate(User user) {
        User oldInfo = userRepository.findByUsername(user.getUsername());

        if (oldInfo != null) {
            oldInfo.setFirstName(user.getFirstName());
            oldInfo.setLastName(user.getLastName());
            oldInfo.setBirthday(user.getBirthday());
            oldInfo.setPhone(user.getPhone());
            oldInfo.setEmail(user.getEmail());
            oldInfo.setRoles(user.getRoles());

            userRepository.save(oldInfo);
        }
    }

    public void tryDelete(Long userId) {
        if (userRepository.findById(userId).isPresent())
            userRepository.deleteById(userId);
    }

    public boolean isExist(Long id) {
        return userRepository.findById(id).isPresent();
    }

}
