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

import nl.altindag.laleler.HostUtils;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.exception.GenericIOException;
import nl.altindag.ssl.model.ClientConfig;
import nl.altindag.sude.Logger;
import nl.altindag.sude.LoggerFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static nl.altindag.laleler.CollectorsUtils.toUnmodifiableList;

/**
 * @author Hakan Altindag
 */
public class CertificateExtractingClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateExtractingClient.class);

    private static final Pattern CA_ISSUERS_AUTHORITY_INFO_ACCESS = Pattern.compile("(?s)^AuthorityInfoAccess\\h+\\[\\R\\s*\\[\\R.*?accessMethod:\\h+caIssuers\\R\\h*accessLocation: URIName:\\h+(https?://\\S+)", Pattern.MULTILINE);

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(1);

    private static CertificateExtractingClient instance;

    private final boolean shouldResolveRootCa;

    private final Proxy proxy;

    private final SSLFactory sslFactoryForCertificateCapturing;

    private final SSLFactory unsafeSslFactory;

    private final SSLSocketFactory unsafeSslSocketFactory;

    private final SSLSocketFactory certificateCapturingSslSocketFactory;

    private final Map<String, List<X509Certificate>> certificatesCollector;

    private final Duration timeout;

    private final ClientConfig clientConfig;

    private final ClientRunnable clientRunnable;

    private CertificateExtractingClient(boolean shouldResolveRootCa, Proxy proxy, PasswordAuthentication passwordAuthentication, Duration timeout, ClientRunnable clientRunnable) {
        this.shouldResolveRootCa = shouldResolveRootCa;
        this.proxy = proxy;
        this.timeout = timeout;
        this.clientRunnable = clientRunnable;
        if (passwordAuthentication != null) {
            Authenticator authenticator = AuthenticatorUtils.create(passwordAuthentication);
            Authenticator.setDefault(authenticator);
        }
        certificatesCollector = new ConcurrentHashMap<>();
        X509ExtendedTrustManager certificateCapturingTrustManager = TrustManagerUtils.createCertificateCapturingTrustManager(certificatesCollector);
        sslFactoryForCertificateCapturing = SSLFactory.builder().withTrustMaterial(certificateCapturingTrustManager).build();
        unsafeSslFactory = SSLFactory.builder().withUnsafeTrustMaterial().build();
        certificateCapturingSslSocketFactory = sslFactoryForCertificateCapturing.getSslSocketFactory();
        unsafeSslSocketFactory = unsafeSslFactory.getSslSocketFactory();
        clientConfig = new ClientConfig(sslFactoryForCertificateCapturing, proxy, passwordAuthentication, timeout);
    }

    static CertificateExtractingClient getInstance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<X509Certificate> get(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void call(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void call(String url, ClientRunnable clientRunnable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    URLConnection createConnection(URL url) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<X509Certificate> getRootCaFromChainIfPossible(List<X509Certificate> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<X509Certificate> getRootCaIfPossible(X509Certificate x509Certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<X509Certificate> getRootCaFromAuthorityInfoAccessExtensionIfPresent(X509Certificate certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<X509Certificate> getCertificatesFromRemoteFile(String issuerLocation, X509Certificate intermediateCertificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<X509Certificate> getRootCaFromJdkTrustedCertificates(X509Certificate intermediateCertificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isIssuerOfIntermediateCertificate(X509Certificate intermediateCertificate, X509Certificate issuer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Extracts certificates based on a list of DNS Names from the Subject Alternative Name extension.
     */
    public Map<String, List<X509Certificate>> getSiblings(List<X509Certificate> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map<String, List<X509Certificate>> getCertificatesCollector() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearCertificatesCollector() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Builder {

        private Proxy proxy = null;

        private PasswordAuthentication passwordAuthentication = null;

        private boolean shouldResolveRootCa = true;

        private Duration timeout = DEFAULT_TIMEOUT;

        private ClientRunnable clientRunnable = null;

        public Builder withProxy(Proxy proxy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withPasswordAuthentication(PasswordAuthentication passwordAuthentication) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withResolvedRootCa(boolean shouldResolveRootCa) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTimeout(int timeoutInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withTimeout(Duration timeout) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withClientRunnable(ClientRunnable clientRunnable) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public CertificateExtractingClient build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
