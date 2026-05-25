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
package nl.altindag.ssl.keymanager;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

/**
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 *
 * @author Hakan Altindag
 */
abstract class DelegatingKeyManager<T extends X509KeyManager> extends X509ExtendedKeyManager {

    private static final String NO_KEY_MANAGER_EXCEPTION_MESSAGE = "No valid KeyManager has been provided. KeyManager must be present, but was absent.";

    T keyManager;

    DelegatingKeyManager(T keyManager) {
        this.keyManager = requireNotNull(keyManager, NO_KEY_MANAGER_EXCEPTION_MESSAGE);
    }

    @Override
    public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public abstract String chooseEngineClientAlias(String[] keyType, Principal[] issuers, SSLEngine engine);

    @Override
    public abstract String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine);

    public T getInnerKeyManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
