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
package nl.altindag.ssl.netty.util;

import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SupportedCipherSuiteFilter;
import nl.altindag.ssl.SSLFactory;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.X509ExtendedKeyManager;

/**
 * @author Hakan Altindag
 */
public final class NettySslUtils {

    private NettySslUtils() {
    }

    /**
     * Creates a basic {@link SslContextBuilder Client SslContextBuilder}
     * with the available properties from {@link SSLFactory}.
     *
     * The returned object can be enriched with additional configuration for your needs
     *
     * @param sslFactory {@link SSLFactory}
     * @return {@link SslContextBuilder}
     */
    public static SslContextBuilder forClient(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a basic {@link SslContextBuilder Server SslContextBuilder}
     * with the available properties from {@link SSLFactory}.
     *
     * The returned object can be enriched with additional configuration for your needs
     *
     * @param sslFactory {@link SSLFactory}
     * @return {@link SslContextBuilder}
     */
    public static SslContextBuilder forServer(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static ClientAuth getClientAuth(SSLParameters sslParameters) {
        if (sslParameters.getNeedClientAuth()) {
            return ClientAuth.REQUIRE;
        } else if (sslParameters.getWantClientAuth()) {
            return ClientAuth.OPTIONAL;
        } else {
            return ClientAuth.NONE;
        }
    }
}
