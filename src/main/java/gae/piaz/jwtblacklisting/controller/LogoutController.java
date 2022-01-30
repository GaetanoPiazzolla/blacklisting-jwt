package gae.piaz.jwtblacklisting.controller;

import gae.piaz.jwtblacklisting.config.UserRequestScopedBean;
import gae.piaz.jwtblacklisting.service.BlackListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private BlackListingService blackListingService;

    @Autowired
    private UserRequestScopedBean userRequestScopedBean;

    @PutMapping
    public ResponseEntity<Void> logout() {
        blackListingService.blackListJwt(userRequestScopedBean.getJwt());
        return ResponseEntity.ok(null);
    }

}
