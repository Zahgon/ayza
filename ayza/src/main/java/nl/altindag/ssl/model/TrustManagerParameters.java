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
package nl.altindag.ssl.model;

import javax.net.ssl.SSLEngine;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Hakan Altindag
 */
public class TrustManagerParameters {

    private final X509Certificate[] chain;

    private final String authType;

    private final Socket socket;

    private final SSLEngine sslEngine;

    public TrustManagerParameters(X509Certificate[] chain, String authType, Socket socket, SSLEngine sslEngine) {
        this.chain = chain;
        this.authType = authType;
        this.socket = socket;
        this.sslEngine = sslEngine;
    }

    public X509Certificate[] getChain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getAuthType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<Socket> getSocket() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<SSLEngine> getSslEngine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> getHostname() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Supplier<Optional<String>> getHostnameFromSslEngine() {
        return () -> getSslEngine().map(SSLEngine::getPeerHost);
    }

    private Supplier<Optional<String>> getHostnameFromSocket() {
        return () -> getSocket().map(socket -> socket.getInetAddress().getHostName());
    }

    public Optional<Integer> getPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Supplier<Optional<Integer>> getPortFromSslEngine() {
        return () -> getSslEngine().map(SSLEngine::getPeerPort);
    }

    private Supplier<Optional<Integer>> getPortFromSocket() {
        return () -> getSocket().map(Socket::getPort);
    }

    @SafeVarargs
    private static <T> Optional<T> findFirst(Supplier<Optional<T>>... suppliers) {
        return Stream.of(suppliers).map(Supplier::get).filter(Optional::isPresent).map(Optional::get).findFirst();
    }
}
