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
package nl.altindag.ssl.socket;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

/**
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 *
 * @author Hakan Altindag
 */
class DelegatingSSLSocket extends SSLSocket {

    final SSLSocket socket;

    public DelegatingSSLSocket(SSLSocket socket) {
        this.socket = socket;
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
    public String[] getSupportedCipherSuites() {
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
    public void addHandshakeCompletedListener(HandshakeCompletedListener listener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void removeHandshakeCompletedListener(HandshakeCompletedListener listener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void startHandshake() throws IOException {
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
    public void setUseClientMode(boolean mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getUseClientMode() {
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
    public SSLParameters getSSLParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSSLParameters(SSLParameters params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void bind(SocketAddress endpoint) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public InetAddress getInetAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getLocalPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized void close() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketChannel getChannel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized int getSoTimeout() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setReuseAddress(boolean on) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getReuseAddress() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized void setReceiveBufferSize(int size) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized int getReceiveBufferSize() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLSession getHandshakeSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void connect(SocketAddress endpoint) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public InetAddress getLocalAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public InputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setTcpNoDelay(boolean on) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getTcpNoDelay() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSoLinger(boolean on, int linger) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getSoLinger() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendUrgentData(int data) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setOOBInline(boolean on) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getOOBInline() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized void setSendBufferSize(int size) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized int getSendBufferSize() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setKeepAlive(boolean on) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean getKeepAlive() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setTrafficClass(int tc) throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getTrafficClass() throws SocketException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void shutdownInput() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void shutdownOutput() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isInputShutdown() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isOutputShutdown() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
