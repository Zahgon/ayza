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

import nl.altindag.laleler.UriUtils;
import nl.altindag.ssl.exception.GenericKeyManagerException;
import nl.altindag.ssl.exception.GenericKeyStoreException;
import nl.altindag.ssl.keymanager.AggregatedX509ExtendedKeyManager;
import nl.altindag.ssl.keymanager.CombinableX509KeyManager;
import nl.altindag.ssl.keymanager.DelegatingX509ExtendedKeyManager;
import nl.altindag.ssl.keymanager.DummyX509ExtendedKeyManager;
import nl.altindag.ssl.keymanager.HotSwappableX509ExtendedKeyManager;
import nl.altindag.ssl.keymanager.InflatableX509ExtendedKeyManager;
import nl.altindag.ssl.keymanager.KeyManagerFactoryWrapper;
import nl.altindag.ssl.keymanager.LoggingX509ExtendedKeyManager;
import nl.altindag.ssl.keymanager.X509KeyManagerWrapper;
import nl.altindag.ssl.model.KeyStoreHolder;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import java.net.URI;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import static nl.altindag.laleler.CollectionUtils.toUnmodifiableList;
import static nl.altindag.laleler.CollectorsUtils.toListAndThen;
import static nl.altindag.laleler.CollectorsUtils.toMapAndThen;
import static nl.altindag.laleler.CollectorsUtils.toUnmodifiableList;
import static nl.altindag.laleler.ValidationUtils.GENERIC_EXCEPTION_MESSAGE;
import static nl.altindag.laleler.ValidationUtils.requireNotEmpty;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

/**
 * @author Hakan Altindag
 */
public final class KeyManagerUtils {

    private static final char[] DUMMY_PASSWORD = KeyStoreUtils.DUMMY_PASSWORD.toCharArray();

    private static final BiFunction<Class<?>, Class<?>, GenericKeyManagerException> KEY_MANAGER_TYPE_MISMATCH_EXCEPTION_PROVIDER = (expectedKeyManagerType, actualKeyManagerType) -> new GenericKeyManagerException(String.format("KeyManager should be an instance of: [%s], but received: [%s]", expectedKeyManagerType.getName(), actualKeyManagerType.getName()));

    private KeyManagerUtils() {
    }

    public static X509ExtendedKeyManager combine(X509KeyManager... keyManagers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager combine(List<? extends X509KeyManager> keyManagers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends X509KeyManager> X509ExtendedKeyManager[] toArray(T keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStoreHolder... keyStoreHolders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStore keyStore, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStore keyStore, char[] keyPassword, String keyManagerFactoryAlgorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStore keyStore, char[] keyPassword, String keyManagerFactoryAlgorithm, String securityProviderName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStore keyStore, char[] keyPassword, String keyManagerFactoryAlgorithm, Provider securityProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStore keyStore, char[] keyPassword, KeyManagerFactory keyManagerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(KeyStore keyStore, Map<String, char[]> aliasToPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager wrapIfNeeded(X509KeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyManagerFactory createKeyManagerFactory(KeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends KeyManagerFactory> X509ExtendedKeyManager getKeyManager(T keyManagerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createDummyKeyManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createLoggingKeyManager(X509KeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wraps the given KeyManager into an instance of a Hot Swappable KeyManager
     * This type of KeyManager has the capability of swapping in and out different KeyManagers at runtime.
     *
     * @param keyManager    To be wrapped KeyManager
     * @return              Swappable KeyManager
     */
    public static X509ExtendedKeyManager createSwappableKeyManager(X509KeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Swaps the internal KeyManager instance with the given keyManager object.
     * The baseKeyManager should be an instance of {@link HotSwappableX509ExtendedKeyManager}
     * and can be created with {@link KeyManagerUtils#createSwappableKeyManager(X509KeyManager)}
     *
     * @param baseKeyManager                an instance of {@link HotSwappableX509ExtendedKeyManager}
     * @param newKeyManager                 to be injected instance of a KeyManager
     * @throws GenericKeyManagerException   if {@code baseKeyManager} is not instance of {@link HotSwappableX509ExtendedKeyManager}
     */
    public static void swapKeyManager(X509KeyManager baseKeyManager, X509KeyManager newKeyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void addIdentityRoute(X509ExtendedKeyManager keyManager, String alias, String... hosts) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void overrideIdentityRoute(X509ExtendedKeyManager keyManager, String alias, String... hosts) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void removeIdentityRoute(X509ExtendedKeyManager keyManager, String alias) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void computeIdentityRoute(X509ExtendedKeyManager keyManager, String alias, String[] hosts, boolean overrideExistingRouteEnabled, boolean removeExistingRouteEnabled) {
        requireNotNull(keyManager, GENERIC_EXCEPTION_MESSAGE.apply("KeyManager"));
        requireNotNull(alias, GENERIC_EXCEPTION_MESSAGE.apply("Alias"));
        if (keyManager instanceof DelegatingX509ExtendedKeyManager) {
            computeIdentityRoute(((DelegatingX509ExtendedKeyManager) keyManager).getInnerKeyManager(), alias, hosts, overrideExistingRouteEnabled, removeExistingRouteEnabled);
            return;
        }
        if (keyManager instanceof AggregatedX509ExtendedKeyManager) {
            AggregatedX509ExtendedKeyManager aggregatedX509ExtendedKeyManager = (AggregatedX509ExtendedKeyManager) keyManager;
            Map<String, List<URI>> aliasToHosts = aggregatedX509ExtendedKeyManager.getIdentityRoute();
            if (removeExistingRouteEnabled) {
                aliasToHosts.remove(alias);
                return;
            }
            requireNotNull(hosts, GENERIC_EXCEPTION_MESSAGE.apply("Host"));
            List<URI> uris = new ArrayList<>();
            for (String host : hosts) {
                URI uri = URI.create(host);
                UriUtils.validate(uri);
                uris.add(uri);
            }
            if (overrideExistingRouteEnabled && aliasToHosts.containsKey(alias)) {
                aliasToHosts.get(alias).clear();
            }
            for (URI uri : uris) {
                if (aliasToHosts.containsKey(alias)) {
                    aliasToHosts.get(alias).add(uri);
                } else {
                    aliasToHosts.put(alias, new ArrayList<>(Collections.singleton(uri)));
                }
            }
        } else {
            throw KEY_MANAGER_TYPE_MISMATCH_EXCEPTION_PROVIDER.apply(AggregatedX509ExtendedKeyManager.class, keyManager.getClass());
        }
    }

    public static Map<String, List<String>> getIdentityRoute(X509ExtendedKeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<X509ExtendedKeyManager> unwrapIfPossible(X509ExtendedKeyManager keyManager) {
        if (keyManager instanceof AggregatedX509ExtendedKeyManager) {
            List<X509ExtendedKeyManager> keyManagers = new ArrayList<>();
            for (X509ExtendedKeyManager innerKeyManager : ((AggregatedX509ExtendedKeyManager) keyManager).getInnerKeyManagers().values()) {
                List<X509ExtendedKeyManager> unwrappedKeyManagers = KeyManagerUtils.unwrapIfPossible(innerKeyManager);
                keyManagers.addAll(unwrappedKeyManagers);
            }
            return keyManagers;
        } else {
            return Collections.singletonList(keyManager);
        }
    }

    public static KeyManagerBuilder keyManagerBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(PrivateKey privateKey, Certificate[] certificatesChain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createKeyManager(String alias, PrivateKey privateKey, Certificate[] certificatesChain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createInflatableKeyManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedKeyManager createInflatableKeyManager(String alias, X509ExtendedKeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds identity material tp a {@link InflatableX509ExtendedKeyManager}
     * If the provided keyManager is not of the type {@link InflatableX509ExtendedKeyManager} it will throw an exception
     */
    public static void addIdentityMaterial(X509ExtendedKeyManager keyManager, String alias, KeyStore keyStore, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds identity material tp a {@link InflatableX509ExtendedKeyManager}
     * If the provided baseKeyManager is not of the type {@link InflatableX509ExtendedKeyManager} it will throw an exception
     */
    public static void addIdentityMaterial(X509ExtendedKeyManager baseKeyManager, String alias, X509ExtendedKeyManager keyManagerToBeAdded) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes identity material from a {@link InflatableX509ExtendedKeyManager}
     */
    public static void removeIdentityMaterial(X509ExtendedKeyManager baseKeyManager, String alias) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Remove, add or other actions related to {@link InflatableX509ExtendedKeyManager}
     */
    private static boolean computeIdentityMaterialIfPossible(X509ExtendedKeyManager baseKeyManager, Consumer<InflatableX509ExtendedKeyManager> consumer) {
        if (baseKeyManager instanceof InflatableX509ExtendedKeyManager) {
            consumer.accept((InflatableX509ExtendedKeyManager) baseKeyManager);
            return true;
        }
        if (baseKeyManager instanceof DelegatingX509ExtendedKeyManager) {
            X509ExtendedKeyManager innerKeyManager = ((DelegatingX509ExtendedKeyManager) baseKeyManager).getInnerKeyManager();
            return computeIdentityMaterialIfPossible(innerKeyManager, consumer);
        }
        if (baseKeyManager instanceof AggregatedX509ExtendedKeyManager) {
            Map<String, X509ExtendedKeyManager> innerKeyManagers = ((AggregatedX509ExtendedKeyManager) baseKeyManager).getInnerKeyManagers();
            Optional<InflatableX509ExtendedKeyManager> inflatableKeyManager = innerKeyManagers.values().stream().filter(InflatableX509ExtendedKeyManager.class::isInstance).map(InflatableX509ExtendedKeyManager.class::cast).findFirst();
            if (inflatableKeyManager.isPresent()) {
                return computeIdentityMaterialIfPossible(inflatableKeyManager.get(), consumer);
            }
        }
        return false;
    }

    /**
     * Returns a list of aliases associated with the KeyManagers within a {@link CombinableX509KeyManager}
     */
    public static List<String> getAliases(X509ExtendedKeyManager keyManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static final class KeyManagerBuilder {

        private static final String EMPTY_KEY_MANAGER_EXCEPTION = "Input does not contain KeyManagers";

        private final List<X509ExtendedKeyManager> keyManagers = new ArrayList<>();

        private final Map<String, List<URI>> aliasToHost = new HashMap<>();

        private boolean swappableKeyManagerEnabled = false;

        private boolean loggingKeyManagerEnabled = false;

        private boolean inflatableKeyManagerEnabled = false;

        private KeyManagerBuilder() {
        }

        @SafeVarargs
        public final <T extends X509KeyManager> KeyManagerBuilder withKeyManagers(T... keyManagers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509KeyManager> KeyManagerBuilder withKeyManagers(List<T> keyManagers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509KeyManager> KeyManagerBuilder withKeyManager(T keyManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public KeyManagerBuilder withIdentities(KeyStoreHolder... identities) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public KeyManagerBuilder withIdentities(List<KeyStoreHolder> identities) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends KeyStore> KeyManagerBuilder withIdentity(T identity, char[] identityPassword, String keyManagerAlgorithm) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public KeyManagerBuilder withSwappableKeyManager(boolean swappableKeyManagerEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public KeyManagerBuilder withLoggingKeyManager(boolean loggingKeyManagerEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public KeyManagerBuilder withInflatableKeyManager(boolean inflatableKeyManagerEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public KeyManagerBuilder withIdentityRoute(Map<String, List<URI>> aliasToHost) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public X509ExtendedKeyManager build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
