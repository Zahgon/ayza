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
package nl.altindag.ssl.socket;

import nl.altindag.sude.Logger;
import nl.altindag.sude.LoggerFactory;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import java.util.function.Supplier;

/**
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 *
 * @author Hakan Altindag
 */
class FenixSSLSocket extends DelegatingSSLSocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenixSSLSocket.class);

    private final SSLParameters sslParameters;

    public FenixSSLSocket(SSLSocket socket, SSLParameters sslParameters) {
        super(socket);
        this.sslParameters = sslParameters;
    }

    @Override
    public void setSSLParameters(SSLParameters params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEnabledCipherSuites(String[] suites) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEnabledProtocols(String[] protocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setNeedClientAuth(boolean need) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setWantClientAuth(boolean want) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getEnabledCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getNeedClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getEnabledProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getWantClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLParameters getSSLParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <T> T updateAndGet(Supplier<T> supplier) {
        socket.setSSLParameters(sslParameters);
        return supplier.get();
    }
}
