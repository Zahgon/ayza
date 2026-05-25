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

import nl.altindag.laleler.IOUtils;
import nl.altindag.ssl.exception.GenericCertificateException;
import nl.altindag.ssl.exception.GenericIOException;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static nl.altindag.laleler.CollectorsUtils.toModifiableList;
import static nl.altindag.laleler.CollectorsUtils.toUnmodifiableList;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

/**
 * @author Hakan Altindag
 */
public final class CertificateUtils {

    private static final String CERTIFICATE_TYPE = "X.509";

    private static final String P7B_HEADER = "-----BEGIN PKCS7-----";

    private static final String P7B_FOOTER = "-----END PKCS7-----";

    private static final String PEM_HEADER = "-----BEGIN CERTIFICATE-----";

    private static final String PEM_FOOTER = "-----END CERTIFICATE-----";

    private static final Pattern PEM_PATTERN = Pattern.compile(PEM_HEADER + "(.*?)" + PEM_FOOTER, Pattern.DOTALL);

    private static final Pattern P7B_PATTERN = Pattern.compile(P7B_HEADER + "(.*?)" + P7B_FOOTER, Pattern.DOTALL);

    private static final String EMPTY_INPUT_STREAM_EXCEPTION_MESSAGE = "Failed to load the certificate from the provided InputStream because it is null";

    private static final UnaryOperator<String> CERTIFICATE_NOT_FOUND_EXCEPTION_MESSAGE = certificatePath -> String.format("Failed to load the certificate from the classpath for the given path: [%s]", certificatePath);

    private static final String MAX_64_CHARACTER_LINE_SPLITTER = "(?<=\\G.{64})";

    private static final String EMPTY = "";

    private CertificateUtils() {
    }

    public static <T extends Certificate> String generateAlias(T certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> Map<String, T> generateAliases(List<T> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> String generateUniqueAlias(T certificate, Predicate<String> aliasPredicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> void write(Path destination, T certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from the classpath and maps it into a list of {@link Certificate}.
     * <br>
     * Supported input format: PEM, P7B and DER
     */
    public static List<Certificate> loadCertificate(String... certificatePaths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from the filesystem and maps it into a list of {@link Certificate}.
     * <br>
     * Supported input format: PEM, P7B and DER
     */
    public static List<Certificate> loadCertificate(Path... certificatePaths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Loads certificates from multiple InputStreams and maps it into a list of {@link Certificate}.
     * <br>
     * Supported input format: PEM, P7B and DER
     */
    public static List<Certificate> loadCertificate(InputStream... certificateStreams) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static <T> List<Certificate> loadCertificate(Function<T, InputStream> resourceMapper, T[] resources) {
        List<Certificate> certificates = new ArrayList<>();
        for (T resource : resources) {
            try (InputStream certificateStream = resourceMapper.apply(resource)) {
                certificates.addAll(parseCertificate(certificateStream));
            } catch (Exception e) {
                throw new GenericIOException(e);
            }
        }
        return Collections.unmodifiableList(certificates);
    }

    /**
     * Tries to map the InputStream to a list of {@link Certificate}.
     * It assumes that the content of the InputStream is either PEM, P7B or DER.
     * The InputStream will copied into an OutputStream so it can be read multiple times.
     */
    private static List<Certificate> parseCertificate(InputStream certificateStream) {
        List<Certificate> certificates;
        byte[] certificateData = IOUtils.copyToByteArray(certificateStream, GenericIOException::new);
        String certificateContent = new String(certificateData, StandardCharsets.UTF_8);
        if (isPemFormatted(certificateContent)) {
            certificates = parsePemCertificate(certificateContent);
        } else if (isP7bFormatted(certificateContent)) {
            certificates = parseP7bCertificate(certificateContent);
        } else {
            certificates = parseDerCertificate(new ByteArrayInputStream(certificateData));
        }
        return certificates;
    }

    private static boolean isPemFormatted(String certificateContent) {
        return PEM_PATTERN.matcher(certificateContent).find();
    }

    private static boolean isP7bFormatted(String certificateContent) {
        return P7B_PATTERN.matcher(certificateContent).find();
    }

    /**
     * Parses PEM formatted certificates containing a
     * header as -----BEGIN CERTIFICATE----- and footer as -----END CERTIFICATE-----
     * or header as -----BEGIN PKCS7----- and footer as -----END PKCS7-----
     * with a base64 encoded data between the header and footer.
     */
    public static List<Certificate> parsePemCertificate(String certificateContent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses P7B formatted certificates containing a
     * header as -----BEGIN PKCS7----- and footer as -----END PKCS7-----
     * with a base64 encoded data between the header and footer.
     */
    public static List<Certificate> parseP7bCertificate(String certificateContent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<Certificate> parseCertificate(Matcher certificateMatcher) {
        List<Certificate> certificates = new ArrayList<>();
        while (certificateMatcher.find()) {
            String certificate = certificateMatcher.group(1);
            String sanitizedCertificate = certificate.replaceAll("[\\n|\\r]+", EMPTY).trim();
            byte[] decodedCertificate = Base64.getDecoder().decode(sanitizedCertificate);
            ByteArrayInputStream certificateAsInputStream = new ByteArrayInputStream(decodedCertificate);
            List<Certificate> parsedCertificates = CertificateUtils.parseDerCertificate(certificateAsInputStream);
            certificates.addAll(parsedCertificates);
            IOUtils.closeSilently(certificateAsInputStream);
        }
        return Collections.unmodifiableList(certificates);
    }

    public static List<Certificate> parseDerCertificate(InputStream certificateStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<X509Certificate> getJdkTrustedCertificates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<X509Certificate> getSystemTrustedCertificates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<X509Certificate> getCertificatesFromExternalSource(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<X509Certificate> getCertificatesFromExternalSource(Proxy proxy, String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<X509Certificate> getCertificatesFromExternalSource(Proxy proxy, PasswordAuthentication passwordAuthentication, String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> getCertificatesFromExternalSourceAsPem(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> getCertificatesFromExternalSourceAsPem(Proxy proxy, String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> getCertificatesFromExternalSourceAsPem(Proxy proxy, PasswordAuthentication passwordAuthentication, String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<X509Certificate>> getCertificatesFromExternalSources(String... urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<X509Certificate>> getCertificatesFromExternalSources(Proxy proxy, String... urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<X509Certificate>> getCertificatesFromExternalSources(Proxy proxy, PasswordAuthentication passwordAuthentication, String... urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<X509Certificate>> getCertificatesFromExternalSources(List<String> urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<X509Certificate>> getCertificatesFromExternalSources(Proxy proxy, List<String> urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<X509Certificate>> getCertificatesFromExternalSources(Proxy proxy, PasswordAuthentication passwordAuthentication, List<String> urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> getCertificatesFromExternalSourcesAsPem(String... urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> getCertificatesFromExternalSourcesAsPem(Proxy proxy, String... urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> getCertificatesFromExternalSourcesAsPem(Proxy proxy, PasswordAuthentication passwordAuthentication, String... urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> getCertificatesFromExternalSourcesAsPem(List<String> urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> getCertificatesFromExternalSourcesAsPem(Proxy proxy, List<String> urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> getCertificatesFromExternalSourcesAsPem(Proxy proxy, PasswordAuthentication passwordAuthentication, List<String> urls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> convertToPem(List<X509Certificate> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String convertToPem(Certificate certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Certificate> boolean isSelfSigned(T certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
