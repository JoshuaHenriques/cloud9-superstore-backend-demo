package org.jayhenri.store_manager.interfaces.service.other;

import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.model.login.Login;

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
