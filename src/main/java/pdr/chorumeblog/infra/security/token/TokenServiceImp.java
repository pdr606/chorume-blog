package pdr.chorumeblog.infra.security.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.exceptions.exceptions.CreateTokenException;
import pdr.chorumeblog.exceptions.exceptions.UnauthorizedException;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.model.UserEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImp implements TokenService {

    @Value("${api.security.token}")
    private String secretAuthToken;

    @Override
    public String generateToken(UserEntity user){
        try{
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getNickName())
                    .withExpiresAt(generateExpirationDate())
                    .sign(generateAlgorithm(secretAuthToken));

        } catch (JWTCreationException ex){
            throw new CreateTokenException();
        }
    }

    @Override
    public String validateToken(String token){
        try {
            return JWT.require(generateAlgorithm(secretAuthToken))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTCreationException ex){
            throw new UnauthorizedException("Token unauthorized");
        }
    }

    @Override
    public Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    @Override
    public Algorithm generateAlgorithm(String token){
        return Algorithm.HMAC256(token);
    }
}
