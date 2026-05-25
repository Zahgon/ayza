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

import nl.altindag.ssl.sslparameters.HotSwappableSSLParameters;
import nl.altindag.ssl.util.SSLParametersUtils;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import static nl.altindag.laleler.ValidationUtils.GENERIC_EXCEPTION_MESSAGE;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

/**
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 * Instead use the {@link nl.altindag.ssl.util.SSLSocketUtils SSLSocketUtils} which provides the same functionality
 * while it has a stable API because it is part of the public API.
 *
 * @author Hakan Altindag
 */
public final class FenixSSLServerSocketFactory extends SSLServerSocketFactory {

    private final SSLServerSocketFactory sslServerSocketFactory;

    private final SSLParameters sslParameters;

    public FenixSSLServerSocketFactory(SSLServerSocketFactory sslServerSocketFactory, SSLParameters sslParameters) {
        this.sslServerSocketFactory = requireNotNull(sslServerSocketFactory, GENERIC_EXCEPTION_MESSAGE.apply("SSLServerSocketFactory"));
        this.sslParameters = requireNotNull(sslParameters, GENERIC_EXCEPTION_MESSAGE.apply("SSLParameters"));
    }

    @Override
    public String[] getDefaultCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getSupportedCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ServerSocket createServerSocket() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ServerSocket createServerSocket(int port, int backlog) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ServerSocket createServerSocket(int port, int backlog, InetAddress ifAddress) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ServerSocket withSslParameters(ServerSocket socket) throws IOException {
        if (socket instanceof SSLServerSocket) {
            SSLServerSocket sslSocket = (SSLServerSocket) socket;
            sslSocket.setSSLParameters(SSLParametersUtils.copy(sslParameters));
            if (sslParameters instanceof HotSwappableSSLParameters) {
                return new FenixSSLServerSocket(sslSocket, sslParameters);
            }
        }
        return socket;
    }
}
