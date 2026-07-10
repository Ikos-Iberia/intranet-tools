package com.saniikos.backend.services;

import com.saniikos.backend.authorization.LoginRequestDTO;
import com.saniikos.backend.authorization.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);

}