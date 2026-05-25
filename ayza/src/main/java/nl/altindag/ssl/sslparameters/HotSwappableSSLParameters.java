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
package nl.altindag.ssl.sslparameters;

import javax.net.ssl.SSLParameters;
import java.security.AlgorithmConstraints;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;
import static nl.altindag.laleler.ValidationUtils.GENERIC_EXCEPTION_MESSAGE;
import static nl.altindag.laleler.ValidationUtils.requireNotNull;

public final class HotSwappableSSLParameters extends DelegatingSSLParameters {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    public HotSwappableSSLParameters(SSLParameters sslParameters) {
        super(sslParameters);
    }

    @Override
    public void setSslParameters(SSLParameters sslParameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLParameters getInnerSslParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getWantClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getNeedClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AlgorithmConstraints getAlgorithmConstraints() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getEndpointIdentificationAlgorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setCipherSuites(String[] cipherSuites) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setProtocols(String[] protocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setWantClientAuth(boolean wantClientAuth) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setNeedClientAuth(boolean needClientAuth) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setAlgorithmConstraints(AlgorithmConstraints constraints) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEndpointIdentificationAlgorithm(String algorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <V> V getSafely(Supplier<V> supplier) {
        readLock.lock();
        try {
            return supplier.get();
        } finally {
            readLock.unlock();
        }
    }

    private void setSafely(SSLParametersRunnable sslParametersRunnable) {
        writeLock.lock();
        try {
            sslParametersRunnable.run();
        } finally {
            writeLock.unlock();
        }
    }
}
