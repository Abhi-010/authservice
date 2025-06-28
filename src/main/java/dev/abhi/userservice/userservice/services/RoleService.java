package dev.abhi.userservice.userservice.services;

import dev.abhi.userservice.userservice.dtos.RoleDto;
import dev.abhi.userservice.userservice.dtos.UserResponseDto;
import dev.abhi.userservice.userservice.models.Role;
import dev.abhi.userservice.userservice.models.User;
import dev.abhi.userservice.userservice.repo.RoleRepository;
import dev.abhi.userservice.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository ;

    public RoleDto createRole(String name){

        List<Role> roles = roleRepository.findAll() ;

        Set<Role> set = new HashSet<>(roles);
        int numberOfRoles = set.size() ;

        Role newRole = new Role() ;
        newRole.setRoleName(name);

        set.add(newRole);
        if(set.size() == numberOfRoles){
            System.out.println("Duplicates Role !! Role is already added ");
        }

        Role savedRole = roleRepository.save(newRole);
        RoleDto roleDto = new RoleDto() ;
        roleDto.setName(savedRole.getRoleName());
        return roleDto ;
    }

    public UserResponseDto addRoleToUser(Long userId, String roleName){

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User doesn't exist with this User ID"));
        Role newROle = roleRepository.findRoleByRoleName(roleName).orElseThrow(()->new RuntimeException("Role with this name doesn't exist"));

        List<Role> roleList = user.getRoles() ;
        for(Role role : roleList){
            if(role.equals(newROle)){
                throw new RuntimeException("Role already linked to the user");
            }
        }

        roleList.add(newROle);
        user.setRoles(roleList);
        User savedUser = userRepository.save(user);
        return  UserResponseDto.from(savedUser) ;
    }
}
