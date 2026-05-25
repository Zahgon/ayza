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

import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 *
 * @author Hakan Altindag
 */
interface RoutableX509KeyManager extends CombinableX509KeyManager, X509KeyManager {

    Predicate<String> NON_NULL = Objects::nonNull;

    Map<String, List<URI>> getIdentityRoute();

    default <T> String chooseClientAlias(T object, Predicate<T> predicate, Function<T, Entry<String, Integer>> hostToPortExtractor, Function<X509ExtendedKeyManager, String> aliasExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default <T> String getPreferredClientAlias(T object, Predicate<T> predicate, Function<T, Entry<String, Integer>> hostToPortExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default String getPreferredClientAlias(String peerHost, int peerPort) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default <T> String chooseServerAlias(T object, Predicate<T> predicate, Function<T, SSLSession> sslSessionExtractor, Function<X509ExtendedKeyManager, String> aliasExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default <T> String getPreferredServerAlias(T object, Predicate<T> predicate, Function<T, SSLSession> sslSessionExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default String getPreferredServerAlias(Set<String> hostnames) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default String chooseAlias(Supplier<String> preferredAliasSupplier, Function<X509ExtendedKeyManager, String> aliasExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean containsInetSocketAddress(Socket socket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
