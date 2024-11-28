package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.User;

public interface UserService extends BaseServiceInterface<User> {
    User findUserByUsername(String username);
}
