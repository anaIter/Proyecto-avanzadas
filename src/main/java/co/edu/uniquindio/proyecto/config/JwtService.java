package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.Enum.Rol;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secretKey; // Debe tener 256 bits (32 chars)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 horas

    public String generateToken(String email, Rol rol) {
        try {
            // 1. Crear las claims
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(email)
                    .claim("email", email)
                    .claim("rol", rol)
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .build();

            // 2. Crear la firma HMAC
            JWSSigner signer = new MACSigner(secretKey.getBytes());

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );

            signedJWT.sign(signer);

            // 3. Serializar el token
            return signedJWT.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token JWT", e);
        }
    }

    public String extractEmail(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getStringClaim("email");
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el email del token", e);
        }
    }

    public String extractRol(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            String ROL = signedJWT.getJWTClaimsSet().getStringClaim("rol");
            System.out.println(ROL);
            return ROL;
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el rol del token", e);
        }
    }
}



