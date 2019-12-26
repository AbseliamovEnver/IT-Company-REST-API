package net.enver.itcompanydemo.service;

/**
 * Service for Security
 */
public interface SecurityService {

    String findLoggedInEmployeeName();

    void autoLogin(String username, String password);
}
