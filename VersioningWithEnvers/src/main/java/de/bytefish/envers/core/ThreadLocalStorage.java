// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.envers.core;

public class ThreadLocalStorage {

    private static ThreadLocal<String> user = new ThreadLocal<>();

    public static void setUsername(String username) {
        user.set(username);
    }

    public static String getUsername() {
        return user.get();
    }


}

