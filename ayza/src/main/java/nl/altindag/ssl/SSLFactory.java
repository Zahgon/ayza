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
package nl.altindag.ssl;

import nl.altindag.laleler.StringUtils;
import nl.altindag.laleler.UriUtils;
import nl.altindag.ssl.exception.GenericKeyStoreException;
import nl.altindag.ssl.exception.GenericSecurityException;
import nl.altindag.ssl.model.HostnameVerifierParameters;
import nl.altindag.ssl.model.KeyStoreHolder;
import nl.altindag.ssl.model.TrustManagerParameters;
import nl.altindag.ssl.model.internal.SSLMaterial;
import nl.altindag.ssl.sslcontext.FenixSSLContext;
import nl.altindag.ssl.trustmanager.trustoptions.TrustAnchorTrustOptions;
import nl.altindag.ssl.trustmanager.trustoptions.TrustStoreTrustOptions;
import nl.altindag.ssl.util.HostnameVerifierUtils;
import nl.altindag.ssl.util.Function;
import nl.altindag.ssl.util.KeyManagerUtils;
import nl.altindag.ssl.util.KeyStoreUtils;
import nl.altindag.ssl.util.Box;
import nl.altindag.ssl.util.SSLContextUtils;
import nl.altindag.ssl.util.SSLParametersUtils;
import nl.altindag.ssl.util.SSLSessionUtils;
import nl.altindag.ssl.util.TrustManagerUtils;
import nl.altindag.sude.Logger;
import nl.altindag.sude.LoggerFactory;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static nl.altindag.laleler.CollectorsUtils.toStringArray;
import static nl.altindag.laleler.ValidationUtils.requireNotBlank;
import static nl.altindag.laleler.ValidationUtils.requireNotEmpty;

/**
 * @author Hakan Altindag
 */
public final class SSLFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSLFactory.class);

    private final SSLMaterial sslMaterial;

    private SSLFactory(SSLMaterial sslMaterial) {
        this.sslMaterial = sslMaterial;
    }

    public SSLContext getSslContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SSLSocketFactory getSslSocketFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SSLServerSocketFactory getSslServerSocketFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<X509ExtendedKeyManager> getKeyManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<KeyManagerFactory> getKeyManagerFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<X509ExtendedTrustManager> getTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<TrustManagerFactory> getTrustManagerFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<X509Certificate> getTrustedCertificates() {
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

    public SSLParameters getSslParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SSLEngine getSSLEngine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SSLEngine getSSLEngine(String peerHost, Integer peerPort) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a cardboard box to further process the SSLFactory instance.
     * The helper {@link Box} class provides a mapping method to map the
     * source in a functional way.
     */
    public <T> Box<T> map(Function<SSLFactory, T> mapper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Builder {

        private static final String TRUST_STORE_VALIDATION_EXCEPTION_MESSAGE = "TrustStore details are empty, which are required to be present when SSL/TLS is enabled";

        private static final String IDENTITY_VALIDATION_EXCEPTION_MESSAGE = "Identity details are empty, which are required to be present when SSL/TLS is enabled";

        private static final String IDENTITY_AND_TRUST_MATERIAL_VALIDATION_EXCEPTION_MESSAGE = "Could not create instance of SSLFactory because Identity " + "and Trust material are not present. Please provide at least a Trust material.";

        private static final String CERTIFICATE_VALIDATION_EXCEPTION_MESSAGE = "Failed to load the certificate(s). No certificate has been provided.";

        private static final String SYSTEM_PROPERTY_VALIDATION_EXCEPTION_MESSAGE = "Failed to load the System property for [%s] because it does not contain any value";

        private String sslContextAlgorithm = "TLS";

        private Provider securityProvider = null;

        private String securityProviderName = null;

        private SecureRandom secureRandom = null;

        private HostnameVerifier hostnameVerifier = HostnameVerifierUtils.createDefault();

        private Predicate<HostnameVerifierParameters> hostnameVerifierEnhancer = null;

        private final List<KeyStoreHolder> identities = new ArrayList<>();

        private final List<KeyStore> trustStores = new ArrayList<>();

        private final List<X509ExtendedKeyManager> identityManagers = new ArrayList<>();

        private final List<X509ExtendedTrustManager> trustManagers = new ArrayList<>();

        private final SSLParameters sslParameters = new SSLParameters();

        private final Map<String, List<URI>> preferredAliasToHost = new HashMap<>();

        private final List<String> protocols = new ArrayList<>();

        private final List<String> ciphers = new ArrayList<>();

        private final List<String> excludedProtocols = new ArrayList<>();

        private final List<String> excludedCiphers = new ArrayList<>();

        private boolean swappableKeyManagerEnabled = false;

        private boolean swappableTrustManagerEnabled = false;

        private boolean swappableSslParametersEnabled = false;

        private boolean loggingKeyManagerEnabled = false;

        private boolean loggingTrustManagerEnabled = false;

        private boolean inflatableKeyManagerEnabled = false;

        private int sessionTimeoutInSeconds = -1;

        private int sessionCacheSizeInBytes = -1;

        private Predicate<TrustManagerParameters> trustManagerParametersValidator = null;

        private boolean shouldTrustedCertificatesBeConcealed = false;

        private Builder() {
        }

        public Builder withSystemTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withDefaultTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSystemPropertyDerivedTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * A shorter method for using the unsafe trust material
         *
         * @see Builder#withTrustingAllCertificatesWithoutValidation()
         * @return {@link Builder}
         */
        public Builder withUnsafeTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withDummyTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Enables the possibility to swap the underlying TrustManager at runtime.
         * After this option has been enabled the TrustManager can be swapped
         * with {@link TrustManagerUtils#swapTrustManager(X509TrustManager, X509TrustManager) TrustManagerUtils#swapTrustManager(swappableTrustManager, newTrustManager)}
         *
         * @return {@link Builder}
         */
        public Builder withSwappableTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withLoggingTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509TrustManager> Builder withTrustMaterial(T trustManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends ManagerFactoryParameters> Builder withTrustMaterial(T managerFactoryParameters) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509TrustManager> Builder withTrustMaterial(T trustManager, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends TrustManagerFactory> Builder withTrustMaterial(T trustManagerFactory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, String trustStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, String trustStoreType, Provider provider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, String trustStoreType, String providerName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withTrustMaterial(String trustStorePath, String trustStoreType, Supplier<KeyStore> trustStoreSupplier) {
            if (StringUtils.isBlank(trustStorePath) || StringUtils.isBlank(trustStoreType)) {
                throw new GenericKeyStoreException(TRUST_STORE_VALIDATION_EXCEPTION_MESSAGE);
            }
            KeyStore trustStore = trustStoreSupplier.get();
            trustStores.add(trustStore);
            return this;
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, String trustStoreType, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, String trustStoreType, Provider provider, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(String trustStorePath, char[] trustStorePassword, String trustStoreType, String providerName, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withTrustMaterial(String trustStorePath, String trustStoreType, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions, Supplier<KeyStore> trustStoreSupplier) {
            if (StringUtils.isBlank(trustStorePath) || StringUtils.isBlank(trustStoreType)) {
                throw new GenericKeyStoreException(TRUST_STORE_VALIDATION_EXCEPTION_MESSAGE);
            }
            KeyStore trustStore = trustStoreSupplier.get();
            return withTrustMaterial(trustStore, trustOptions);
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType, Provider provider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType, String providerName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType, Provider provider, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType, String providerName, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, String trustStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, String trustStoreType, Provider provider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, String trustStoreType, String providerName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withTrustMaterial(Object trustStoreSource, String trustStoreType, Supplier<KeyStore> trustStoreSupplier) {
            if (isNull(trustStoreSource) || StringUtils.isBlank(trustStoreType)) {
                throw new GenericKeyStoreException(TRUST_STORE_VALIDATION_EXCEPTION_MESSAGE);
            }
            KeyStore trustStore = trustStoreSupplier.get();
            trustStores.add(trustStore);
            return this;
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, String trustStoreType, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, String trustStoreType, Provider provider, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(InputStream trustStoreStream, char[] trustStorePassword, String trustStoreType, String providerName, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withTrustMaterial(Object trustStoreSource, String trustStoreType, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions, Supplier<KeyStore> trustStoreSupplier) {
            if (isNull(trustStoreSource) || StringUtils.isBlank(trustStoreType)) {
                throw new GenericKeyStoreException(TRUST_STORE_VALIDATION_EXCEPTION_MESSAGE);
            }
            KeyStore trustStore = trustStoreSupplier.get();
            return withTrustMaterial(trustStore, trustOptions);
        }

        public Builder withTrustMaterial(KeyStore trustStore) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(KeyStore trustStore, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustMaterial(Set<X509Certificate> certificates, TrustAnchorTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SafeVarargs
        public final <T extends Certificate> Builder withTrustMaterial(T... certificates) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public final <T extends Certificate> Builder withTrustMaterial(T[] certificates, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends Certificate> Builder withTrustMaterial(List<T> certificates) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends Certificate> Builder withTrustMaterial(List<T> certificates, TrustStoreTrustOptions<? extends CertPathTrustManagerParameters> trustOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSystemPropertyDerivedIdentityMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(String identityStorePath, char[] identityStorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(String identityStorePath, char[] identityStorePassword, char[] identityPassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(String identityStorePath, char[] identityStorePassword, String identityStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(String identityStorePath, char[] identityStorePassword, char[] identityPassword, String identityStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(String identityStorePath, char[] identityStorePassword, char[] identityPassword, String identityStoreType, Provider provider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(String identityStorePath, char[] identityStorePassword, char[] identityPassword, String identityStoreType, String providerName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withIdentityMaterial(String identityStorePath, String identityStoreType, Supplier<KeyStoreHolder> keyStoreHolderSupplier) {
            if (StringUtils.isBlank(identityStorePath) || StringUtils.isBlank(identityStoreType)) {
                throw new GenericKeyStoreException(IDENTITY_VALIDATION_EXCEPTION_MESSAGE);
            }
            KeyStoreHolder identityHolder = keyStoreHolderSupplier.get();
            identities.add(identityHolder);
            return this;
        }

        public Builder withIdentityMaterial(Path identityStorePath, char[] identityStorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(Path identityStorePath, char[] identityStorePassword, char[] identityPassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(Path identityStorePath, char[] identityStorePassword, String identityStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(Path identityStorePath, char[] identityStorePassword, char[] identityPassword, String identityStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(Path identityStorePath, char[] identityStorePassword, char[] identityPassword, String identityStoreType, Provider provider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(Path identityStorePath, char[] identityStorePassword, char[] identityPassword, String identityStoreType, String providerName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(InputStream identityStream, char[] identityStorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(InputStream identityStream, char[] identityStorePassword, char[] identityPassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(InputStream identityStream, char[] identityStorePassword, String identityStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(InputStream identityStream, char[] identityStorePassword, char[] identityPassword, String identityStoreType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(InputStream identityStream, char[] identityStorePassword, char[] identityPassword, String identityStoreType, Provider provider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityMaterial(InputStream identityStream, char[] identityStorePassword, char[] identityPassword, String identityStoreType, String providerName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withIdentityMaterial(Object identitySource, String identityStoreType, Supplier<KeyStoreHolder> keyStoreHolderSupplier) {
            if (isNull(identitySource) || StringUtils.isBlank(identityStoreType)) {
                throw new GenericKeyStoreException(IDENTITY_VALIDATION_EXCEPTION_MESSAGE);
            }
            KeyStoreHolder identityHolder = keyStoreHolderSupplier.get();
            identities.add(identityHolder);
            return this;
        }

        public Builder withIdentityMaterial(KeyStore identityStore, char[] identityPassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SafeVarargs
        public final <T extends Certificate> Builder withIdentityMaterial(Key privateKey, char[] privateKeyPassword, T... certificateChain) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SafeVarargs
        public final <T extends Certificate> Builder withIdentityMaterial(Key privateKey, char[] privateKeyPassword, String alias, T... certificateChain) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public final <T extends Certificate> Builder withIdentityMaterial(Key privateKey, char[] privateKeyPassword, List<T> certificateChain) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public final <T extends Certificate> Builder withIdentityMaterial(Key privateKey, char[] privateKeyPassword, String alias, List<T> certificateChain) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509KeyManager> Builder withIdentityMaterial(T keyManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends KeyManagerFactory> Builder withIdentityMaterial(T keyManagerFactory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withDummyIdentityMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Enables the possibility to swap the underlying KeyManager at runtime.
         * After this option has been enabled the KeyManager can be swapped
         * with {@link KeyManagerUtils#swapKeyManager(X509KeyManager, X509KeyManager) KeyManagerUtils#swapKeyManager(swappableKeyManager, newKeyManager)}
         *
         * @return {@link Builder}
         */
        public Builder withSwappableIdentityMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withLoggingIdentityMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withInflatableIdentityMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withInflatableTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withInflatableTrustMaterial(Path trustStorePath, char[] trustStorePassword, String trustStoreType, Predicate<TrustManagerParameters> trustManagerParametersPredicate) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private void validateKeyStore(KeyStore keyStore, String exceptionMessage) {
            if (isNull(keyStore)) {
                throw new GenericKeyStoreException(exceptionMessage);
            }
        }

        public Builder withIdentityRoute(String alias, String... hosts) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withIdentityRoute(Map<String, List<String>> aliasesToHosts) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder withIdentityRoute(String alias, List<URI> hosts) {
            if (StringUtils.isBlank(alias)) {
                throw new IllegalArgumentException("alias should be present");
            }
            requireNotEmpty(hosts, String.format("At least one host should be present. No host(s) found for the given alias: [%s]", alias));
            for (URI host : hosts) {
                UriUtils.validate(host);
                if (preferredAliasToHost.containsKey(alias)) {
                    preferredAliasToHost.get(alias).add(host);
                } else {
                    preferredAliasToHost.put(alias, new ArrayList<>(Collections.singletonList(host)));
                }
            }
            return this;
        }

        public <T extends HostnameVerifier> Builder withHostnameVerifier(T hostnameVerifier) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withUnsafeHostnameVerifier() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withHostnameVerifierEnhancer(Predicate<HostnameVerifierParameters> hostnameVerifierParametersValidator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withCiphers(String... ciphers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withExcludedCiphers(String... ciphers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSystemPropertyDerivedCiphers() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withProtocols(String... protocols) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withExcludedProtocols(String... protocols) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSystemPropertyDerivedProtocols() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private List<String> extractPropertyValues(String systemProperty) {
            String propertyValue = requireNotBlank(System.getProperty(systemProperty), String.format(SYSTEM_PROPERTY_VALIDATION_EXCEPTION_MESSAGE, systemProperty));
            List<String> propertyValues = Arrays.stream(propertyValue.split(",")).map(String::trim).filter(StringUtils::isNotBlank).distinct().collect(Collectors.toList());
            return requireNotEmpty(propertyValues, String.format(SYSTEM_PROPERTY_VALIDATION_EXCEPTION_MESSAGE, systemProperty));
        }

        public Builder withNeedClientAuthentication() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withNeedClientAuthentication(boolean needClientAuthentication) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withWantClientAuthentication() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withWantClientAuthentication(boolean wantClientAuthentication) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSessionTimeout(int timeoutInSeconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSessionCacheSize(int cacheSizeInBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSslContextAlgorithm(String sslContextAlgorithm) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSwappableSslParameters() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends Provider> Builder withSecurityProvider(T securityProvider) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSecurityProvider(String securityProviderName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends SecureRandom> Builder withSecureRandom(T secureRandom) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustingAllCertificatesWithoutValidation() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTrustEnhancer(Predicate<TrustManagerParameters> validator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withConcealedTrustMaterial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public SSLFactory build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private boolean isTrustMaterialPresent() {
            return !trustStores.isEmpty() || !trustManagers.isEmpty();
        }

        private boolean isIdentityMaterialPresent() {
            return !identities.isEmpty() || !identityManagers.isEmpty();
        }

        private X509ExtendedKeyManager createKeyManager() {
            return KeyManagerUtils.keyManagerBuilder().withKeyManagers(identityManagers).withIdentities(identities).withSwappableKeyManager(swappableKeyManagerEnabled).withLoggingKeyManager(loggingKeyManagerEnabled).withInflatableKeyManager(inflatableKeyManagerEnabled).withIdentityRoute(preferredAliasToHost).build();
        }

        private X509ExtendedTrustManager createTrustManager() {
            return TrustManagerUtils.trustManagerBuilder().withTrustManagers(trustManagers).withTrustStores(trustStores).withSwappableTrustManager(swappableTrustManagerEnabled).withLoggingTrustManager(loggingTrustManagerEnabled).withTrustEnhancer(trustManagerParametersValidator).withTrustEnhancer(shouldTrustedCertificatesBeConcealed).build();
        }

        private SSLParameters createSslParameters(SSLContext sslContext) {
            SSLParameters defaultSSLParameters = sslContext.getDefaultSSLParameters();
            List<String> defaultCiphers = Arrays.asList(defaultSSLParameters.getCipherSuites());
            List<String> defaultProtocols = Arrays.asList(defaultSSLParameters.getProtocols());
            String[] preferredCiphers = ciphers.stream().distinct().filter(StringUtils::isNotBlank).filter(defaultCiphers::contains).collect(toStringArray());
            String[] preferredProtocols = protocols.stream().distinct().filter(StringUtils::isNotBlank).filter(defaultProtocols::contains).collect(toStringArray());
            sslParameters.setCipherSuites(preferredCiphers);
            sslParameters.setProtocols(preferredProtocols);
            SSLParameters mergedSslParameters = SSLParametersUtils.merge(sslParameters, defaultSSLParameters, excludedCiphers, excludedProtocols);
            return swappableSslParametersEnabled ? SSLParametersUtils.createSwappableSslParameters(mergedSslParameters) : mergedSslParameters;
        }
    }
}
