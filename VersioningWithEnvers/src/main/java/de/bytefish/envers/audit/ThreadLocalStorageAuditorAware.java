// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.envers.audit;

import de.bytefish.envers.core.ThreadLocalStorage;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalStorageAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return ThreadLocalStorage.getUsername();
    }

}
