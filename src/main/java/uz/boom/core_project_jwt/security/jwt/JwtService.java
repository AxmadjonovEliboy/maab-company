package uz.boom.core_project_jwt.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.boom.core_project_jwt.dto.auth.SessionDTO;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Jarvis on Sat 12:05. 08/04/23
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "244226452948404D6351665468576D5A7134743777217A25432A462D4A614E64";
    public static int EXPIRE_ACCESS_TOKEN = 1000 * 60 * 60 * 24;
    public static long EXPIRE_REFRESH_TOKEN = 1000 * 60 * 60 * 24 * 30L;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public SessionDTO createSessionDto(UserDetails user) {

        String accessToken = generateAccessToken(new HashMap<>(), user);
        String refreshToken = generateRefreshToken(new HashMap<>(), user);
        return SessionDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("JWT")
                .refreshTokenExpire(EXPIRE_REFRESH_TOKEN)
                .issuedAt(System.currentTimeMillis())
                .expiresIn(extractExpiration(accessToken).getTime())
                .build();
    }

//    public String generateAccessToken(UserDetails user) {
//        return generateAccessToken(new HashMap<>(), user);
//    }

    private String generateAccessToken(
            HashMap<String, Object> extractClaims,
            UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(
            HashMap<String, Object> extractClaims,
            UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_REFRESH_TOKEN))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
