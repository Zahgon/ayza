/*
 * Copyright 2019 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.ssl.util;

import nl.altindag.ssl.sslparameters.HotSwappableSSLParameters;
import javax.net.ssl.SSLParameters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Hakan Altindag
 */
public final class SSLParametersUtils {

    private SSLParametersUtils() {
    }

    public static SSLParameters copy(SSLParameters source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static SSLParameters merge(SSLParameters baseSslParameters, SSLParameters alternativeSslParameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static SSLParameters merge(SSLParameters baseSslParameters, SSLParameters alternativeSslParameters, List<String> excludedCiphers, List<String> excludedProtocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wraps the given SSLParameters into an instance of a Hot Swappable SSLParameters.
     * This type of SSLParameters has the capability of swapping in and out different SSLParameters at runtime.
     *
     * @param sslParameters To be wrapped SSLParameters
     * @return Swappable SSLParameters
     */
    public static SSLParameters createSwappableSslParameters(SSLParameters sslParameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
