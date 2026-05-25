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
import nl.altindag.ssl.exception.GenericTrustManagerException;
import nl.altindag.ssl.model.TrustManagerParameters;
import nl.altindag.ssl.trustmanager.CertificateCapturingX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.AggregatedX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.DelegatingX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.DummyX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.EnhanceableX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.HotSwappableX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.InflatableX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.JdkX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.LoggingX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.SystemX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.TrustManagerFactoryWrapper;
import nl.altindag.ssl.trustmanager.UnsafeX509ExtendedTrustManager;
import nl.altindag.ssl.trustmanager.X509TrustManagerWrapper;
import nl.altindag.sude.Logger;
import nl.altindag.sude.LoggerFactory;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import static nl.altindag.laleler.CollectorsUtils.toListAndThen;
import static nl.altindag.laleler.ValidationUtils.requireNotEmpty;

/**
 * @author Hakan Altindag
 */
public final class TrustManagerUtils {

    private TrustManagerUtils() {
    }

    public static X509ExtendedTrustManager combine(X509TrustManager... trustManagers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager combine(List<? extends X509TrustManager> trustManagers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends X509TrustManager> X509ExtendedTrustManager[] toArray(T trustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManagerWithJdkTrustedCertificates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Optional<X509ExtendedTrustManager> createTrustManagerWithSystemTrustedCertificates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(List<X509Certificate> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(KeyStore... trustStores) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(KeyStore trustStore) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(KeyStore trustStore, String trustManagerFactoryAlgorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(KeyStore trustStore, String trustManagerFactoryAlgorithm, String securityProviderName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(KeyStore trustStore, String trustManagerFactoryAlgorithm, Provider securityProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(KeyStore trustStore, TrustManagerFactory trustManagerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(ManagerFactoryParameters... managerFactoryParameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(ManagerFactoryParameters managerFactoryParameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(ManagerFactoryParameters managerFactoryParameters, String trustManagerFactoryAlgorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(ManagerFactoryParameters managerFactoryParameters, String trustManagerFactoryAlgorithm, String securityProviderName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(ManagerFactoryParameters managerFactoryParameters, String trustManagerFactoryAlgorithm, Provider securityProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createTrustManager(ManagerFactoryParameters managerFactoryParameters, TrustManagerFactory trustManagerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createUnsafeTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createDummyTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createLoggingTrustManager(X509TrustManager baseTrustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createCertificateCapturingTrustManager(Map<String, List<X509Certificate>> certificatesCollector) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createCertificateCapturingTrustManager(X509TrustManager baseTrustManager, Map<String, List<X509Certificate>> certificatesCollector) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager wrapIfNeeded(X509TrustManager trustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static TrustManagerFactory createTrustManagerFactory(TrustManager trustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends TrustManagerFactory> X509ExtendedTrustManager getTrustManager(T trustManagerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createInflatableTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createInflatableTrustManager(Path trustStorePath, char[] trustStorePassword, String trustStoreType, Predicate<TrustManagerParameters> trustManagerParametersPredicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds a new to be trusted certificate to the existing TrustManager.
     * The provided TrustManager should be an instance of {@link InflatableX509ExtendedTrustManager}
     * and it is allowed that it is wrapped in a {@link AggregatedX509ExtendedTrustManager}
     */
    public static void addCertificate(X509ExtendedTrustManager trustManager, X509Certificate certificate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds a new to be trusted certificate to the existing TrustManager.
     * The provided TrustManager should be an instance of {@link InflatableX509ExtendedTrustManager}
     * and it is allowed that it is wrapped in a {@link AggregatedX509ExtendedTrustManager}
     */
    public static void addCertificate(X509ExtendedTrustManager trustManager, List<X509Certificate> certificates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static boolean addCertificateIfPossible(X509ExtendedTrustManager trustManager, List<X509Certificate> certificates) {
        if (trustManager instanceof InflatableX509ExtendedTrustManager) {
            ((InflatableX509ExtendedTrustManager) trustManager).addCertificates(certificates);
            return true;
        }
        if (trustManager instanceof DelegatingX509ExtendedTrustManager) {
            X509ExtendedTrustManager innerTrustManager = ((DelegatingX509ExtendedTrustManager) trustManager).getInnerTrustManager();
            return addCertificateIfPossible(innerTrustManager, certificates);
        }
        if (trustManager instanceof AggregatedX509ExtendedTrustManager) {
            List<X509ExtendedTrustManager> innerTrustManagers = ((AggregatedX509ExtendedTrustManager) trustManager).getInnerTrustManagers();
            Optional<InflatableX509ExtendedTrustManager> inflatableX509ExtendedTrustManager = innerTrustManagers.stream().filter(InflatableX509ExtendedTrustManager.class::isInstance).map(InflatableX509ExtendedTrustManager.class::cast).findFirst();
            if (inflatableX509ExtendedTrustManager.isPresent()) {
                return addCertificateIfPossible(inflatableX509ExtendedTrustManager.get(), certificates);
            }
        }
        return false;
    }

    /**
     * Wraps the given TrustManager into an instance of a Hot Swappable TrustManager.
     * This type of TrustManager has the capability of swapping in and out different TrustManagers at runtime.
     *
     * @param trustManager To be wrapped TrustManager
     * @return Swappable TrustManager
     */
    public static X509ExtendedTrustManager createSwappableTrustManager(X509TrustManager trustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Swaps the internal TrustManager instance with the given trustManager object.
     * The baseTrustManager should be an instance of {@link HotSwappableX509ExtendedTrustManager}
     * and can be created with {@link TrustManagerUtils#createSwappableTrustManager(X509TrustManager)}
     *
     * @param baseTrustManager an instance of {@link HotSwappableX509ExtendedTrustManager}
     * @param newTrustManager  to be injected instance of a TrustManager
     * @throws GenericTrustManagerException if {@code baseTrustManager} is not instance of {@link HotSwappableX509ExtendedTrustManager}
     */
    public static void swapTrustManager(X509TrustManager baseTrustManager, X509TrustManager newTrustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void computeMappersForNewTrustManager(X509ExtendedTrustManager trustManager, List<UnaryOperator<X509ExtendedTrustManager>> mappers) {
        if (trustManager instanceof LoggingX509ExtendedTrustManager) {
            LoggingX509ExtendedTrustManager loggingTrustManager = (LoggingX509ExtendedTrustManager) trustManager;
            mappers.add(LoggingX509ExtendedTrustManager::new);
            computeMappersForNewTrustManager(loggingTrustManager.getInnerTrustManager(), mappers);
        }
        if (trustManager instanceof EnhanceableX509ExtendedTrustManager) {
            EnhanceableX509ExtendedTrustManager existingEnhanceableTrustManager = (EnhanceableX509ExtendedTrustManager) trustManager;
            mappers.add(newTrustManager -> new EnhanceableX509ExtendedTrustManager(TrustManagerUtils.wrapIfNeeded(newTrustManager), existingEnhanceableTrustManager.getTrustManagerParametersValidator(), existingEnhanceableTrustManager.isTrustedCertificatesConcealed()));
            computeMappersForNewTrustManager(existingEnhanceableTrustManager.getInnerTrustManager(), mappers);
        }
    }

    public static X509ExtendedTrustManager createEnhanceableTrustManager(X509ExtendedTrustManager trustManager, Predicate<TrustManagerParameters> trustManagerParametersValidator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static X509ExtendedTrustManager createEnhanceableTrustManager(X509ExtendedTrustManager trustManager, Predicate<TrustManagerParameters> trustManagerParametersValidator, boolean shouldTrustedCertificatesBeConcealed) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<X509ExtendedTrustManager> unwrapIfPossible(X509ExtendedTrustManager trustManager) {
        if (trustManager instanceof AggregatedX509ExtendedTrustManager) {
            List<X509ExtendedTrustManager> trustManagers = new ArrayList<>();
            for (X509ExtendedTrustManager innerTrustManager : ((AggregatedX509ExtendedTrustManager) trustManager).getInnerTrustManagers()) {
                List<X509ExtendedTrustManager> unwrappedTrustManagers = TrustManagerUtils.unwrapIfPossible(innerTrustManager);
                trustManagers.addAll(unwrappedTrustManagers);
            }
            return trustManagers;
        } else {
            return Collections.singletonList(trustManager);
        }
    }

    public static TrustManagerBuilder trustManagerBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static final class TrustManagerBuilder {

        private static final Logger LOGGER = LoggerFactory.getLogger(TrustManagerBuilder.class);

        private static final String EMPTY_TRUST_MANAGER_EXCEPTION = "Input does not contain TrustManager";

        private TrustManagerBuilder() {
        }

        private final List<X509ExtendedTrustManager> trustManagers = new ArrayList<>();

        private boolean swappableTrustManagerEnabled = false;

        private boolean loggingTrustManagerEnabled = false;

        private Predicate<TrustManagerParameters> trustManagerParametersValidator;

        private boolean shouldTrustedCertificatesBeConcealed;

        public <T extends X509TrustManager> TrustManagerBuilder withTrustManagers(T... trustManagers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509TrustManager> TrustManagerBuilder withTrustManagers(List<T> trustManagers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends X509TrustManager> TrustManagerBuilder withTrustManager(T trustManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends KeyStore> TrustManagerBuilder withTrustStores(T... trustStores) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public TrustManagerBuilder withTrustStores(List<? extends KeyStore> trustStores) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends KeyStore> TrustManagerBuilder withTrustStore(T trustStore) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T extends KeyStore> TrustManagerBuilder withTrustStore(T trustStore, String trustManagerAlgorithm) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public TrustManagerBuilder withSwappableTrustManager(boolean swappableTrustManagerEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public TrustManagerBuilder withLoggingTrustManager(boolean loggingTrustManagerEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public TrustManagerBuilder withTrustEnhancer(Predicate<TrustManagerParameters> trustManagerParametersValidator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public TrustManagerBuilder withTrustEnhancer(boolean shouldTrustedCertificatesBeConcealed) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public X509ExtendedTrustManager build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Optional<X509ExtendedTrustManager> getUnsafeOrDummyTrustManagerIfConfigured(List<X509ExtendedTrustManager> trustManagers) {
            Optional<X509ExtendedTrustManager> maybeUnsafeTrustManager = trustManagers.stream().filter(UnsafeX509ExtendedTrustManager.class::isInstance).findAny();
            if (maybeUnsafeTrustManager.isPresent()) {
                if (trustManagers.size() > 1) {
                    LOGGER.debug("Unsafe TrustManager is being used therefore other trust managers will not be included for constructing the base trust manager");
                }
                return maybeUnsafeTrustManager;
            }
            Optional<X509ExtendedTrustManager> maybeDummyTrustManager = trustManagers.stream().filter(DummyX509ExtendedTrustManager.class::isInstance).findAny();
            if (maybeDummyTrustManager.isPresent()) {
                if (trustManagers.size() > 1) {
                    LOGGER.debug("Dummy TrustManager is being used therefore other trust managers will not be included for constructing the base trust manager");
                }
                return maybeDummyTrustManager;
            }
            return Optional.empty();
        }

        private X509ExtendedTrustManager combine(List<X509ExtendedTrustManager> trustManagers) {
            if (trustManagers.size() == 1) {
                return trustManagers.get(0);
            }
            return trustManagers.stream().map(TrustManagerUtils::unwrapIfPossible).flatMap(Collection::stream).collect(CollectorsUtils.toListAndThen(AggregatedX509ExtendedTrustManager::new));
        }

        private Optional<X509ExtendedTrustManager> createEnhanceableTrustManagerIfEnabled(X509ExtendedTrustManager baseTrustManager) {
            if (trustManagerParametersValidator == null && !shouldTrustedCertificatesBeConcealed) {
                return Optional.empty();
            }
            Predicate<TrustManagerParameters> aTrustManagerParametersValidator;
            if (trustManagerParametersValidator != null) {
                aTrustManagerParametersValidator = trustManagerParametersValidator;
            } else {
                aTrustManagerParametersValidator = trustManagerParameters -> false;
            }
            X509ExtendedTrustManager enhanceableTrustManager = TrustManagerUtils.createEnhanceableTrustManager(baseTrustManager, aTrustManagerParametersValidator, shouldTrustedCertificatesBeConcealed);
            return Optional.of(enhanceableTrustManager);
        }
    }
}
