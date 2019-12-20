package net.enver.itcompanydemo.service;

/**
 * Service for Security
 *
 * @author Enver on 13.12.2019 21:18.
 * @project ITCompanyDemo
 */
public interface SecurityService {

    String findLoggedInEmployeeName();

    void autoLogin(String username, String password);
}
