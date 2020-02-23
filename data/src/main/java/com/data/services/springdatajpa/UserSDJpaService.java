package com.data.services.springdatajpa;

import com.data.model.UserBO;
import com.data.repositories.UserRepository;
import com.data.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class UserSDJpaService implements UserService {

    private final UserRepository userRepository;

    public UserSDJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserBO findByFullName(String lastName) {
        return userRepository.findByFullName(lastName);
    }

    @Override
    public List<UserBO> findAllByFullNameLike(String lastName) {
        return userRepository.findAllByFullNameLike(lastName);
    }

    @Override
    public Set<UserBO> findAll() {
        Set<UserBO> ownerBOS = new HashSet<>();
        userRepository.findAll().forEach(ownerBOS::add);
        return ownerBOS;
    }

    @Override
    public UserBO findById(Integer id) {
        Optional<UserBO> optionalOwner = userRepository.findById(id);
        return optionalOwner.orElse(null);
    }

    @Override
    public UserBO save(UserBO object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(UserBO object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
