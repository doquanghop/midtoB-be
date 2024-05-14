package com.example.StudyWithMe.controllers.user;

import com.example.StudyWithMe.dataTransferObjects.user.auth.ChangePasswordRequest;
import com.example.StudyWithMe.dataTransferObjects.user.auth.LoginDTO;
import com.example.StudyWithMe.dataTransferObjects.user.auth.RegisterDTO;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.user.auth.AuthResponse;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/${api.prefix}/auth")
public class AuthController {
    private final IAuthService authService;
    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @ModelAttribute RegisterDTO registerDTO,
            HttpServletRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(ResponseObject.error(HttpStatus.BAD_REQUEST, errorMessages));
        }
        String userAgent = request.getHeader("User-Agent");
        AuthResponse authResponse = authService.register(registerDTO,userAgent);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Register successfully",authResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginDTO loginDTO,
            HttpServletRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(ResponseObject.error(HttpStatus.BAD_REQUEST, errorMessages));
        }
        String userAgent = request.getHeader("User-Agent");
        AuthResponse authResponse = authService.login(loginDTO, userAgent);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Login successfully", authResponse));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Logout successfully", null));
    }
    @PostMapping("/refresh-token")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshTokenHeader) {
        AuthResponse authResponse = authService.refreshToken(refreshTokenHeader);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Refresh Token successfully", authResponse));
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest passwordRequest
    ) {
        AuthResponse authResponse = authService.changePassword(passwordRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Change password successful!", authResponse));
    }

    @DeleteMapping("/delete-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(
            @RequestParam Long userId
    ) {
        authService.deleteUser(userId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, String.format("Delete user with id %d successful!", userId), null));
    }
}
