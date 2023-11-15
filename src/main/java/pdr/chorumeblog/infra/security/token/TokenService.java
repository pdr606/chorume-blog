package pdr.chorumeblog.infra.security.token;

import com.auth0.jwt.algorithms.Algorithm;
import pdr.chorumeblog.model.UserEntity;

import java.time.Instant;

public interface TokenService {
    String generateToken(UserEntity user);
    String validateToken(String token);
    Instant generateExpirationDate();
    Algorithm generateAlgorithm(String token);
}
