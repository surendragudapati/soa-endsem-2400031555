
package com.soa.klef.exam.service;

import com.soa.klef.exam.entity.User;

public interface AuthService {

    User registerUser(User user);

    String loginUser(String email, String password);

    boolean validateToken(String token);

    String extractEmailFromToken(String token);
}
	