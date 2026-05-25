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
package nl.altindag.ssl.sslengine;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import java.nio.ByteBuffer;

/**
 * @author Hakan Altindag
 */
class DelegatingSSLEngine extends SSLEngine {

    final SSLEngine sslEngine;

    public DelegatingSSLEngine(SSLEngine sslEngine) {
        this.sslEngine = sslEngine;
    }

    @Override
    public SSLEngineResult wrap(ByteBuffer[] srcs, int offset, int length, ByteBuffer dst) throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngineResult wrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngineResult wrap(ByteBuffer[] srcs, ByteBuffer dst) throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts, int offset, int length) throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts) throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Runnable getDelegatedTask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void closeInbound() throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isInboundDone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void closeOutbound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isOutboundDone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getSupportedCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getEnabledCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEnabledCipherSuites(String[] suites) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getSupportedProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getEnabledProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEnabledProtocols(String[] protocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLSession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void beginHandshake() throws SSLException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLSession getHandshakeSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setUseClientMode(boolean mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getUseClientMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setNeedClientAuth(boolean need) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getNeedClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setWantClientAuth(boolean want) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getWantClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEnableSessionCreation(boolean flag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getEnableSessionCreation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getPeerHost() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getPeerPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLParameters getSSLParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSSLParameters(SSLParameters params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
