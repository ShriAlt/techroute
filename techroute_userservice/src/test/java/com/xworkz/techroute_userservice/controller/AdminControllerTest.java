package com.xworkz.techroute_userservice.controller;

import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.enums.Status;

import com.xworkz.techroute_userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.data.jpa.autoconfigure.DataJpaRepositoriesAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@ImportAutoConfiguration(exclude = {DataJpaRepositoriesAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
class AdminControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void listAllUsers_success() throws Exception {
        UserResponse user = new UserResponse("shriharsha", "test@example.com", Role.CUSTOMER, Status.ACTIVE);
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("shriharsha"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateUserRole_success() throws Exception {
        UUID id = UUID.randomUUID();
        UserResponse updated = new UserResponse("shriharsha", "test@example.com", Role.ADMIN, Status.ACTIVE);

        when(userService.updateUserRole(eq(id), any(Role.class))).thenReturn(updated);

        mockMvc.perform(put("/api/admin/users/" + id + "/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"ADMIN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateUserStatus_success() throws Exception {
        UUID id = UUID.randomUUID();
        UserResponse updated = new UserResponse("shriharsha", "test@example.com", Role.CUSTOMER, Status.INACTIVE);

        when(userService.updateUserStatus(eq(id), any(Status.class))).thenReturn(updated);

        mockMvc.perform(put("/api/admin/users/" + id + "/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"INACTIVE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("INACTIVE"));
    }
}
