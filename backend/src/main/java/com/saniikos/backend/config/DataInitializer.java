package com.saniikos.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.saniikos.backend.entities.Department;
import com.saniikos.backend.entities.Role;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.entities.UserRole;
import com.saniikos.backend.entities.UserRoleId;
import com.saniikos.backend.repositories.DepartmentRepository;
import com.saniikos.backend.repositories.RoleRepository;
import com.saniikos.backend.repositories.UserRepository;
import com.saniikos.backend.repositories.UserRoleRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {


        // Evitamos duplicar datos si ya existe un usuario
        if (userRepository.count() > 0) {
            return;
        }


        /*
         * DEPARTMENT
         */
        Department department = new Department();

        department.setName("IT");
        department.setCode("IT");
        department.setDescription("Departamento de tecnología");
        department.setSystem(false);
        department.setActive(true);

        department = departmentRepository.save(department);



        /*
         * ROLE
         */
        Role role = new Role();

        role.setName("Administrador");
        role.setCode("ROLE_ADMIN");
        role.setDescription("Administrador del sistema");
        role.setActive(true);

        role = roleRepository.save(role);



        /*
         * USER
         */
        User user = new User();

        user.setUsername("admin");
        user.setEmail("admin@saniikos.com");

        user.setFirstName("Admin");
        user.setLastName("System");

        user.setPasswordHash(
                passwordEncoder.encode("admin123")
        );

        user.setDepartment(department);

        user.setActive(true);

        user = userRepository.save(user);



        /*
         * USER_ROLE
         */
        UserRoleId userRoleId = new UserRoleId(
                user.getId(),
                role.getId()
        );


        UserRole userRole = new UserRole();

        userRole.setId(userRoleId);

        userRole.setUser(user);

        userRole.setRole(role);


        userRoleRepository.save(userRole);



        System.out.println("==============================");
        System.out.println(" Usuario inicial creado");
        System.out.println(" Username: admin");
        System.out.println(" Password: admin123");
        System.out.println("==============================");

    }

}