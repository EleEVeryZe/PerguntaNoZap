package com.demo.account.domain.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtils {

    public static String generateJwtToken(int withExpiresAt) {
        Algorithm algorithm = Algorithm.HMAC256("${jwt.secret}");
        return JWT.create().withIssuer("auth0")
                .withExpiresAt(DateUtils.getNDaysFromNow(withExpiresAt)).sign(algorithm);
    }
}
