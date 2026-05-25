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
package nl.altindag.ssl.model.internal;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.util.List;
import static nl.altindag.laleler.CollectionUtils.toUnmodifiableList;

/**
 * <p>
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 * </p>
 *
 * @author Hakan Altindag
 */
public final class SSLMaterial {

    private SSLContext sslContext;

    private X509ExtendedKeyManager keyManager;

    private X509ExtendedTrustManager trustManager;

    private HostnameVerifier hostnameVerifier;

    private SSLParameters sslParameters;

    private SSLMaterial() {
    }

    public SSLContext getSslContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public X509ExtendedKeyManager getKeyManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public X509ExtendedTrustManager getTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SSLParameters getSslParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public HostnameVerifier getHostnameVerifier() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getCiphers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Builder {

        private SSLContext sslContext;

        private X509ExtendedKeyManager keyManager;

        private X509ExtendedTrustManager trustManager;

        private HostnameVerifier hostnameVerifier;

        private SSLParameters sslParameters;

        public Builder withSslContext(SSLContext sslContext) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withHostnameVerifier(HostnameVerifier hostnameVerifier) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSslParameters(SSLParameters sslParameters) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withKeyManager(X509ExtendedKeyManager keyManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustManager(X509ExtendedTrustManager trustManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public SSLMaterial build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
