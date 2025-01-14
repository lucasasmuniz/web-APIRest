// package proj.web_api.security;

// import java.util.List;
// import java.util.stream.Collectors;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.SignatureException;
// import io.jsonwebtoken.UnsupportedJwtException;

// import javax.crypto.SecretKey;

// public class JWTCreator {
//     public static final String HEADER_AUTHORIZATION = "Authorization";
//     public static final String ROLES_AUTHORITIES = "authorities";

//     public static String create(String prefix, String key, JWTObject jwtObject) {
//         // A chave precisa ter ao menos 256 bits (32 caracteres)
//         SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());
    
//         // Cria o token JWT
//         String token = Jwts.builder()
//                 .setSubject(jwtObject.getSubject()) // Nome do usuário
//                 .setIssuedAt(jwtObject.getIssuedAt()) // Data de emissão
//                 .setExpiration(jwtObject.getExpiration()) // Data de expiração
//                 .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())) // Perfis de acesso
//                 .signWith(secretKey, SignatureAlgorithm.HS512) // Assinatura
//                 .compact();
    
//         return prefix + " " + token; // Retorna o token com prefixo
//     }
    

//     public static JWTObject create(String token, String prefix, String key)
//         throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
//     // Remove o prefixo do token
//     token = token.replace(prefix, "").trim();

//     // Gera uma chave segura a partir da string de chave
//     SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

//     // Decodifica e valida o token
//     Claims claims = Jwts.builder()
//             .setSigningKey(secretKey) // Configura a chave de assinatura
//             .build()
//             .parseClaimsJws(token) // Valida o token JWT
//             .getBody();

//     // Popula o objeto JWTObject
//     JWTObject object = new JWTObject();
//     object.setSubject(claims.getSubject());
//     object.setIssuedAt(claims.getIssuedAt().toInstant());
//     object.setExpiration(claims.getExpiration().toInstant());
//     object.setRoles(claims.get(ROLES_AUTHORITIES, List.class));

//     return object;
// }

//     private static List<String> checkRoles(List<String> roles) {
//         return roles.stream()
//                 .map(s -> "ROLE_".concat(s.replaceAll("ROLE_", "")))
//                 .collect(Collectors.toList());
//     }
// }
