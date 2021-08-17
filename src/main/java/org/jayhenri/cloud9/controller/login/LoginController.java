package org.jayhenri.cloud9.controller.login;

import org.jayhenri.cloud9.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidLoginException;
import org.jayhenri.cloud9.exception.notfound.LoginNotFoundException;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Login controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/login")
public class LoginController {

    private final LoginService loginService;

    /**
     * Instantiates a new Login controller.
     *
     * @param loginService the login service
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Register response entity.
     *
     * @param login the login
     * @return the response entity
     * @throws LoginAlreadyExistsException the login already exists exception
     * @throws InvalidLoginException       the invalid login exception
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addLogin(@RequestBody Login login)
            throws LoginAlreadyExistsException, InvalidLoginException {

        if (ObjectUtils.isEmpty(login))
            throw new InvalidLoginException();

        else if (loginService.existsById(login.getLoginUUID()))
            throw new LoginAlreadyExistsException();

        loginService.add(login);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("LoginRegistrationController", "register");
        return new ResponseEntity<>("Successfully Created Login", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update login.
     *
     * @param login   the login
     * @param loginId the login id
     * @return the response entity
     * @throws InvalidLoginException  the invalid login exception
     * @throws LoginNotFoundException the login not found exception
     */
    @PutMapping(value = "/update/{loginId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateLogin(@RequestBody Login login, @PathVariable UUID loginId)
            throws InvalidLoginException, LoginNotFoundException {
        if (!ObjectUtils.isEmpty(login)) {
            if (loginService.existsById(loginId)) {
                login.setLoginUUID(loginId);
                loginService.update(login);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("LoginController", "updateLogin");
                return new ResponseEntity<>("Successfully Updated Login", responseHeaders, HttpStatus.OK);
            } else
                throw new LoginNotFoundException();
        } else
            throw new InvalidLoginException();
    }

    /**
     * Delete login.
     *
     * @param loginId the email
     * @return the response entity
     * @throws LoginNotFoundException the login not found exception
     */
    @DeleteMapping(value = "/delete/{loginId}")
    public ResponseEntity<String> deleteLogin(@PathVariable UUID loginId)
            throws LoginNotFoundException {
        if (loginService.existsById(loginId)) {
            loginService.remove(loginService.getById(loginId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("LoginController", "deleteLogin");
            return new ResponseEntity<>("Successfully Deleted Login", responseHeaders, HttpStatus.OK);
        } else
            throw new LoginNotFoundException();
    }

    /**
     * List logins response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list/logins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Login>> listLogins() {
        // @RequestParam(defaultValue = "email") String sortBy
        List<Login> list = loginService.findAll(); // sortBy

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("LoginController", "listLogins");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }
}
