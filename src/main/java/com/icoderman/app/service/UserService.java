package com.icoderman.app.service;

import com.icoderman.app.dao.AddressRepository;
import com.icoderman.app.dao.UserRepository;
import com.icoderman.app.model.Address;
import com.icoderman.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Oleksandr Mandryk
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void createNewUser() {
        Address address = new Address("Main str", "Krakow", "Poland");
        User user = new User("myusername", "mypwd", "Oleksandr Mandryk", "icoderman@test.com", address);
        User saved = userRepository.save(user);
    }

    @Transactional
    public void updateUserFields() {
        User user = userRepository.findByUsername("myusername");
        user.setFullName("Updated Name");
        user.setPassword("superpwd");
        userRepository.save(user);
    }

    @Transactional
    public void updateUserAddress() {
        User user = userRepository.findByUsername("myusername");
        Address address = user.getAddress();
        address.setCity("Lodz");
        user.setAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void restoreLastRevision() {
        Revision<Integer, User> revision = userRepository.findLastChangeRevision(1L);
        User revisionUser = revision.getEntity();
        User user = userRepository.findByUsername("myusername");

        user.setUsername(revisionUser.getUsername());
        user.setFullName(revisionUser.getFullName());
        user.setPassword(revisionUser.getPassword());
        user.setAddress(revisionUser.getAddress());
        user.setEmail(revisionUser.getEmail());
        userRepository.save(user);
    }

    @Transactional
    public void showAllUserRevisions() {
        Revisions<Integer, User> revisions = userRepository.findRevisions(1L).reverse();
        for (Revision<Integer, User> revision : revisions) {
            System.out.println("-----------------------------------------");
            System.out.println("REVISION: " + revision.getRevisionNumber());
            System.out.println(revision.getEntity());
            System.out.println("-----------------------------------------");
        }
    }
}
