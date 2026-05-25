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
package nl.altindag.ssl.pem.util;

import nl.altindag.laleler.IOUtils;
import nl.altindag.laleler.ValidationUtils;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.exception.GenericIOException;
import nl.altindag.ssl.pem.decryptor.PemDecryptor;
import nl.altindag.ssl.pem.decryptor.Pkcs8Decryptor;
import nl.altindag.ssl.pem.exception.CertificateParseException;
import nl.altindag.ssl.pem.exception.PemParseException;
import nl.altindag.ssl.pem.exception.PrivateKeyParseException;
import nl.altindag.ssl.pem.exception.PublicKeyParseException;
import nl.altindag.ssl.util.KeyManagerUtils;
import nl.altindag.ssl.util.TrustManagerUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.X509TrustedCertificateBlock;
import org.bouncycastle.openssl.jcajce.JcaMiscPEMGenerator;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import static nl.altindag.ssl.pem.util.PemType.CERTIFICATE;
import static nl.altindag.ssl.pem.util.PemType.KEY;
import static nl.altindag.laleler.CollectorsUtils.toListAndThen;
import static nl.altindag.laleler.CollectorsUtils.toUnmodifiableList;
import static nl.altindag.laleler.ValidationUtils.requireNotEmpty;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

/**
 * Reads PEM formatted private keys and certificates
 * as identity material and trust material and maps it
 * to either a {@link X509ExtendedKeyManager} or {@link X509ExtendedTrustManager}.
 * <br>
 * <br>
 * The PemUtils provides also other methods for example to:
 * <pre>
 * - load trusted certificates and map it into a list of {@link X509Certificate}
 * - load identity material and map it into a {@link PrivateKey}
 * </pre>
 * <p>
 * The PemUtils serves mainly as a helper class to easily supply the PEM formatted SSL material
 * for the {@link SSLFactory}, but can also be used for other purposes.
 *
 * @author Hakan Altindag
 */
public final class PemUtils {

    private static final String EMPTY_INPUT_STREAM_EXCEPTION_MESSAGE = "Failed to load the certificate from the provided InputStream because it is null";

    private static final UnaryOperator<String> CERTIFICATE_NOT_FOUND_EXCEPTION_MESSAGE = certificatePath -> String.format("Failed to load the certificate from the classpath for the given path: [%s]", certificatePath);

    private static final char[] NO_PASSWORD = null;

    private static final PemUtils INSTANCE = new PemUtils(new BouncyCastleProvider(), new JcaPEMKeyConverter(), new JcaX509CertificateConverter());

    private final JcaPEMKeyConverter keyConverter;

    private final JcaX509CertificateConverter certificateConverter;

    PemUtils(BouncyCastleProvider bouncyCastleProvider, JcaPEMKeyConverter keyConverter, JcaX509CertificateConverter certificateConverter) {
        Security.addProvider(bouncyCastleProvider);
        this.keyConverter = keyConverter;
        this.certificateConverter = certificateConverter;
    }

    /**
     * Loads certificates from the classpath and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedTrustManager loadTrustMaterial(String... certificatePaths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from the filesystem and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedTrustManager loadTrustMaterial(Path... certificatePaths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from multiple InputStreams and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedTrustManager loadTrustMaterial(InputStream... certificateStreams) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from the classpath and maps it to a list of {@link X509Certificate}
     */
    public static List<X509Certificate> loadCertificate(String... certificatePaths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from the filesystem and maps it to a list of {@link X509Certificate}
     */
    public static List<X509Certificate> loadCertificate(Path... certificatePaths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from multiple InputStreams and maps it to a list of {@link X509Certificate}
     */
    public static List<X509Certificate> loadCertificate(InputStream... certificateStreams) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static <T> List<X509Certificate> loadCertificate(T[] resources, Function<T, InputStream> resourceMapper) {
        return Arrays.stream(resources).map(resourceMapper).map(inputStream -> IOUtils.getContent(inputStream, GenericIOException::new)).map(PemUtils::parseCertificate).flatMap(Collection::stream).collect(toUnmodifiableList());
    }

    public static List<X509Certificate> parseCertificate(String certContent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<Object> parsePemContent(String pemContent, Predicate<PemType> predicate) {
        String formattedPemContent = PemFormatter.reformatIfNeeded(pemContent);
        try (Reader stringReader = new StringReader(formattedPemContent);
            PEMParser pemParser = new PEMParser(stringReader)) {
            List<Object> objects = new ArrayList<>();
            for (Object object = pemParser.readObject(); object != null; object = pemParser.readObject()) {
                PemType pemType = PemType.from(object);
                if (predicate.test(pemType)) {
                    objects.add(object);
                }
            }
            return objects;
        } catch (IOException e) {
            throw new PemParseException(e);
        }
    }

    static Optional<X509Certificate> extractCertificate(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses one or more certificates as a string representation
     * and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedTrustManager parseTrustMaterial(String... certificateContents) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a certificate chain and a private key
     * from the classpath and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(String certificateChainPath, String privateKeyPath) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a certificate chain and a private key from
     * the classpath and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(String certificateChainPath, String privateKeyPath, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a certificate chain and a private key
     * as an InputStream and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(InputStream certificateChainStream, InputStream privateKeyStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a certificate chain and a private key
     * as an InputStream and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(InputStream certificateChainStream, InputStream privateKeyStream, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a certificate chain and a private key
     * from the filesystem and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(Path certificateChainPath, Path privateKeyPath) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a certificate chain and a private key
     * from the filesystem and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(Path certificateChainPath, Path privateKeyPath, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a combined file containing the certificate chain and the private key
     * from the classpath and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(String identityPath) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a combined file containing the certificate chain and the private key
     * from the classpath and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(String identityPath, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a combined file containing the certificate chain and the private key
     * from the filesystem and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(Path identityPath) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a combined file containing the certificate chain and the private key
     * from the filesystem and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(Path identityPath, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a combined entity containing the certificate chain and the private key
     * from an InputStream and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(InputStream identityStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the identity material based on a combined entity containing the certificate chain and the private key
     * from an InputStream and maps it to an instance of {@link X509ExtendedKeyManager}
     */
    public static X509ExtendedKeyManager loadIdentityMaterial(InputStream identityStream, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static <T> X509ExtendedKeyManager loadIdentityMaterial(T certificateChain, T privateKey, char[] keyPassword, Function<T, InputStream> resourceMapper) {
        try (InputStream certificateChainStream = resourceMapper.apply(certificateChain);
            InputStream privateKeyStream = resourceMapper.apply(privateKey)) {
            String certificateChainContent = IOUtils.getContent(certificateChainStream, GenericIOException::new);
            String privateKeyContent = IOUtils.getContent(privateKeyStream, GenericIOException::new);
            return parseIdentityMaterial(certificateChainContent, privateKeyContent, keyPassword);
        } catch (IOException exception) {
            throw new GenericIOException(exception);
        }
    }

    private static <T> X509ExtendedKeyManager loadIdentityMaterial(T identity, char[] keyPassword, Function<T, InputStream> resourceMapper) {
        try (InputStream identityStream = resourceMapper.apply(identity)) {
            String identityContent = IOUtils.getContent(identityStream, GenericIOException::new);
            return parseIdentityMaterial(identityContent, identityContent, keyPassword);
        } catch (IOException exception) {
            throw new GenericIOException(exception);
        }
    }

    /**
     * Parses the identity material based on a string representation containing the certificate chain and the private key
     * and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedKeyManager parseIdentityMaterial(String identityContent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses the identity material based on a string representation containing the certificate chain and the private key
     * and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedKeyManager parseIdentityMaterial(String identityContent, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses the identity material based on a string representation of the certificate chain and the private key
     * and maps it to an instance of {@link X509ExtendedTrustManager}
     */
    public static X509ExtendedKeyManager parseIdentityMaterial(String certificateChainContent, String privateKeyContent, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the private key from the classpath and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey loadPrivateKey(String identityPath) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the private key from the classpath and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey loadPrivateKey(String identityPath, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the private key from the filesystem and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey loadPrivateKey(Path identityPath) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the private key from the filesystem and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey loadPrivateKey(Path identityPath, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the private key from an InputStream and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey loadPrivateKey(InputStream identityStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads the private key from an InputStream and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey loadPrivateKey(InputStream identityStream, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static <T> PrivateKey loadPrivateKey(T privateKey, char[] keyPassword, Function<T, InputStream> resourceMapper) {
        try (InputStream privateKeyStream = resourceMapper.apply(privateKey)) {
            String privateKeyContent = IOUtils.getContent(privateKeyStream);
            return parsePrivateKey(privateKeyContent, keyPassword);
        } catch (IOException exception) {
            throw new GenericIOException(exception);
        }
    }

    /**
     * Parses the private key based on a string representation of the private key
     * and maps it to an instance of {@link PrivateKey}
     */
    public static PrivateKey parsePrivateKey(String identityContent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses the private key based on a string representation of the private key
     * and maps it to an instance of {@link PrivateKey}. If the identity content
     * contains multiple private keys it will use only the first one.
     */
    public static PrivateKey parsePrivateKey(String identityContent, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Optional<PrivateKeyInfo> extractPrivateKeyInfo(Object object, char[] keyPassword) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static PrivateKey extractPrivateKey(PrivateKeyInfo privateKeyInfo) {
        try {
            return PemUtils.getInstance().getKeyConverter().getPrivateKey(privateKeyInfo);
        } catch (PEMException exception) {
            throw new PrivateKeyParseException(exception);
        }
    }

    public static PublicKey extractPublicKey(PrivateKey privateKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static PemUtils getInstance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private JcaPEMKeyConverter getKeyConverter() {
        return keyConverter;
    }

    private JcaX509CertificateConverter getCertificateConverter() {
        return certificateConverter;
    }
}
