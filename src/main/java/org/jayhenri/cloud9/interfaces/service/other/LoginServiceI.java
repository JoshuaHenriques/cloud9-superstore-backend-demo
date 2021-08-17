package org.jayhenri.cloud9.interfaces.service.other;

import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.model.login.Login;

/**
 * The interface Login service i.
 */
public interface LoginServiceI extends ServiceI<Login> {

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean existsByPhoneNumber(String phoneNumber);
}
