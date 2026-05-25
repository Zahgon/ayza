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

import nl.altindag.desidero.OperatingSystem;
import nl.altindag.laleler.CollectorsUtils;
import nl.altindag.laleler.IOUtils;
import nl.altindag.laleler.StringUtils;
import nl.altindag.ssl.exception.GenericIOException;
import nl.altindag.ssl.exception.GenericKeyStoreException;
import nl.altindag.sude.Logger;
import nl.altindag.sude.LoggerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import static nl.altindag.laleler.ValidationUtils.requireNotEmpty;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

/**
 * @author Hakan Altindag
 */
public final class KeyStoreUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyStoreUtils.class);

    public static final String DUMMY_PASSWORD = "dummy-password";

    private static final String KEYSTORE_TYPE = "PKCS12";

    private static final String EMPTY_INPUT_STREAM_EXCEPTION_MESSAGE = "Failed to load the keystore from the provided InputStream because it is null";

    private static final UnaryOperator<String> KEYSTORE_NOT_FOUND_EXCEPTION_MESSAGE = certificatePath -> String.format("Failed to load the keystore from the classpath for the given path: [%s]", certificatePath);

    private static final String EMPTY_TRUST_MANAGER_FOR_TRUSTSTORE_EXCEPTION = "Could not create TrustStore because the provided TrustManager does not contain any trusted certificates";

    private static final String EMPTY_CERTIFICATES_EXCEPTION = "Could not create TrustStore because certificate is absent";

    private KeyStoreUtils() {
    }

    public static KeyStore loadKeyStore(String keystorePath, char[] keystorePassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(String keystorePath, char[] keystorePassword, String keystoreType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(String keystorePath, char[] keystorePassword, String keystoreType, String providerName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(String keystorePath, char[] keystorePassword, String keystoreType, Provider provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static KeyStore loadKeyStore(String keystorePath, KeyStoreFunction<InputStream, KeyStore> keyStoreKeyStoreFunction) {
        try (InputStream keystoreInputStream = IOUtils.getResourceAsStream(keystorePath)) {
            requireNotNull(keystoreInputStream, KEYSTORE_NOT_FOUND_EXCEPTION_MESSAGE.apply(keystorePath));
            return keyStoreKeyStoreFunction.apply(keystoreInputStream);
        } catch (Exception e) {
            throw new GenericKeyStoreException(e);
        }
    }

    public static KeyStore loadKeyStore(Path keystorePath, char[] keystorePassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(Path keystorePath, char[] keystorePassword, String keystoreType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(Path keystorePath, char[] keystorePassword, String keystoreType, String providerName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(Path keystorePath, char[] keystorePassword, String keystoreType, Provider provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static KeyStore loadKeyStore(Path keystorePath, KeyStoreFunction<InputStream, KeyStore> mapper) {
        try (InputStream keystoreInputStream = IOUtils.getFileAsStream(keystorePath, GenericIOException::new)) {
            return mapper.apply(keystoreInputStream);
        } catch (Exception e) {
            throw new GenericKeyStoreException(e);
        }
    }

    public static KeyStore loadKeyStore(InputStream keystoreInputStream, char[] keystorePassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(InputStream keystoreInputStream, char[] keystorePassword, String keystoreType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(InputStream keystoreInputStream, char[] keystorePassword, String keystoreType, String providerName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadKeyStore(InputStream keystoreInputStream, char[] keystorePassword, String keystoreType, Provider provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static KeyStore loadKeyStore(InputStream keystoreInputStream, char[] keystorePassword, KeyStoreSupplier keyStoreSupplier) {
        try {
            KeyStore keystore = keyStoreSupplier.get();
            keystore.load(requireNotNull(keystoreInputStream, EMPTY_INPUT_STREAM_EXCEPTION_MESSAGE), keystorePassword);
            return keystore;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | NoSuchProviderException e) {
            throw new GenericKeyStoreException(e);
        }
    }

    public static KeyStore createIdentityStore(Key privateKey, char[] privateKeyPassword, String alias, List<? extends Certificate> certificateChain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore createIdentityStore(Key privateKey, char[] privateKeyPassword, List<? extends Certificate> certificateChain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SafeVarargs
    public static <T extends Certificate> KeyStore createIdentityStore(Key privateKey, char[] privateKeyPassword, T... certificateChain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SafeVarargs
    public static <T extends Certificate> KeyStore createIdentityStore(Key privateKey, char[] privateKeyPassword, String alias, T... certificateChain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore createKeyStore() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore createKeyStore(char[] keyStorePassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore createKeyStore(String keyStoreType, char[] keyStorePassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SafeVarargs
    public static <T extends X509TrustManager> KeyStore createTrustStore(T... trustManagers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SafeVarargs
    public static <T extends Certificate> KeyStore createTrustStore(T... certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> KeyStore createTrustStore(List<T> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadJdkKeyStore() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<KeyStore> loadSystemKeyStores() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadSystemPropertyDerivedKeyStore() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static KeyStore loadSystemPropertyDerivedTrustStore() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static KeyStore loadSystemPropertyDerivedKeyStore(String keyStorePathProperty, String keyStorePasswordProperty, String keyStoreTypeProperty, String keyStoreProviderProperty) {
        Path keyStorePath = Optional.ofNullable(System.getProperty(keyStorePathProperty)).map(String::trim).filter(StringUtils::isNotBlank).map(Paths::get).orElseThrow(() -> new GenericKeyStoreException(String.format("The value for the system property [%s] is absent", keyStorePathProperty)));
        char[] keystorePassword = Optional.ofNullable(System.getProperty(keyStorePasswordProperty)).map(String::trim).filter(StringUtils::isNotBlank).map(String::toCharArray).orElse(null);
        String keystoreType = Optional.ofNullable(System.getProperty(keyStoreTypeProperty)).map(String::trim).filter(StringUtils::isNotBlank).orElseGet(KeyStore::getDefaultType);
        String keyStoreProvider = Optional.ofNullable(System.getProperty(keyStoreProviderProperty)).map(String::trim).filter(StringUtils::isNotBlank).orElse(null);
        return KeyStoreUtils.loadKeyStore(keyStorePath, keystorePassword, keystoreType, keyStoreProvider);
    }

    public static List<Certificate> getCertificates(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Certificate> getAliasToCertificate(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> getAliases(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> boolean containsCertificate(KeyStore keyStore, T certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void write(Path destination, KeyStore keyStore, char[] password) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the provides list of certificates to the given keystore path on the filesystem if exists.
     * If the keystore is absent it will create it with the given password and also add the certificates.
     */
    public static <T extends Certificate> void add(Path keystorePath, char[] password, String keystoreType, List<T> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> void add(KeyStore keyStore, List<T> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int countAmountOfTrustMaterial(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int countAmountOfIdentityMaterial(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean containsTrustMaterial(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean containsIdentityMaterial(KeyStore keyStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static int amountOfSpecifiedMaterial(KeyStore keyStore, KeyStoreBiPredicate<KeyStore, String> predicate, int upperBoundaryForMaterialCounter) {
        try {
            int materialCounter = 0;
            List<String> aliases = getAliases(keyStore);
            for (String alias : aliases) {
                if (materialCounter < upperBoundaryForMaterialCounter && predicate.test(keyStore, alias)) {
                    materialCounter++;
                }
            }
            return materialCounter;
        } catch (KeyStoreException e) {
            throw new GenericKeyStoreException(e);
        }
    }

    private interface KeyStoreBiPredicate<T extends KeyStore, U> {

        boolean test(T t, U u) throws KeyStoreException;
    }

    private interface KeyStoreFunction<T, R extends KeyStore> {

        R apply(T t) throws Exception;
    }

    private interface KeyStoreSupplier {

        KeyStore get() throws KeyStoreException, NoSuchProviderException;
    }
}
