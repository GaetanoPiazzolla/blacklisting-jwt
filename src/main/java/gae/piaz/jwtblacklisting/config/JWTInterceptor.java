package gae.piaz.jwtblacklisting.config;

import gae.piaz.jwtblacklisting.service.BlackListingService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private BlackListingService blackListingService;

    @Autowired
    private UserRequestScopedBean userRequestScopedBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        try {

            String token = request.getHeader("Authorization");
            token = token.substring(7);

            Jwts.parser()
                    .setSigningKey(Base64.encodeBase64String(jwtKey.getBytes()))
                    .parseClaimsJws(token);

            String blackListedToken = blackListingService.getJwtBlackList(token);
            if (blackListedToken != null) {
                log.error("JwtInterceptor: Token is blacklisted");
                response.sendError(401);
                return false;
            }

            userRequestScopedBean.setJwt(token);
            return true;

        } catch (Exception e) {
            log.error("JwtInterceptor - Exception : {} ",e.getMessage());
            response.sendError(401);
            return false;
        }
    }

} 