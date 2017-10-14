// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.envers.web.filters;


import de.bytefish.envers.core.ThreadLocalStorage;
import de.bytefish.envers.exceptions.MissingUsernameException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class UserNameFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        MultivaluedMap<String, String> headers = ctx.getHeaders();

        if(headers == null) {
            handleError(ctx);
        }

        if(!headers.containsKey("X-Username")) {
            handleError(ctx);
        }

        String username = headers.getFirst("X-Username");

        if(username == null) {
            handleError(ctx);
        }

        // Set the Username in the ThreadLocalStorage of the Request:
        ThreadLocalStorage.setUsername(username);
    }

    public void handleError(ContainerRequestContext ctx) {
        throw new MissingUsernameException("Request is missing a Username");
    }
}
