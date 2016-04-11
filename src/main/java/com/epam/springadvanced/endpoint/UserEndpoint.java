package com.epam.springadvanced.endpoint;

import com.epam.springadvanced.domain.entity.soap.UsersRequest;
import com.epam.springadvanced.domain.entity.soap.UsersResponse;
import com.epam.springadvanced.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author dimon
 * @since 11/04/16.
 */
@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com/spring/advanced/soap";

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public UsersResponse getAllUsers(@RequestPayload UsersRequest request) {
        UsersResponse response = new UsersResponse();
        response.setUsers(userService.getAll());
        return response;
    }

}
