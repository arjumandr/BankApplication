package com.bankApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankApp.entities.Clerk;
import com.bankApp.entities.Manager;
import com.bankApp.service.ClerkService;
import com.bankApp.service.ManagerService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ClerkService clerkService;

    @Override
    public UserDetails loadUserByUsername(String idStr) throws UsernameNotFoundException {
        Integer id;
        try {
            id = Integer.parseInt(idStr); // convert username to integer ID
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid ID");
        }

        // Try to find as Manager first
        try {
            Manager manager = managerService.getById(id); 
            return org.springframework.security.core.userdetails.User
                    .withUsername(String.valueOf(manager.getManagerId()))
                    .password(manager.getPassword()) // hashed password
                    .roles("MANAGER")
                    .build();
        } catch (RuntimeException e) {
            // Not a manager, try clerk
            Clerk clerk = clerkService.getById(id);
            return org.springframework.security.core.userdetails.User
                    .withUsername(String.valueOf(clerk.getClerkId()))
                    .password(clerk.getPassword()) // hashed
                    .roles("CLERK")
                    .build();
        }
    }
}
