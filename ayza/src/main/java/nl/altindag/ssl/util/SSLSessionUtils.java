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

import nl.altindag.ssl.SSLFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import static nl.altindag.laleler.CollectorsUtils.toUnmodifiableList;

/**
 * @author Hakan Altindag
 */
public final class SSLSessionUtils {

    private static final LongFunction<ZonedDateTime> EPOCH_TIME_MAPPER = epochTime -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochTime), ZoneOffset.UTC);

    private SSLSessionUtils() {
    }

    public static void invalidateCaches(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateServerCaches(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateClientCaches(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCaches(SSLContext sslContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateServerCaches(SSLContext sslContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateClientCaches(SSLContext sslContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCaches(SSLSessionContext sslSessionContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesBefore(SSLFactory sslFactory, ZonedDateTime upperBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesBefore(SSLContext sslContext, ZonedDateTime upperBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesBefore(SSLSessionContext sslSessionContext, ZonedDateTime upperBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesAfter(SSLFactory sslFactory, ZonedDateTime lowerBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesAfter(SSLContext sslContext, ZonedDateTime lowerBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesAfter(SSLSessionContext sslSessionContext, ZonedDateTime lowerBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesBetween(SSLFactory sslFactory, ZonedDateTime lowerBoundary, ZonedDateTime upperBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesBetween(SSLContext sslContext, ZonedDateTime lowerBoundary, ZonedDateTime upperBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void invalidateCachesBetween(SSLSessionContext sslSessionContext, ZonedDateTime lowerBoundary, ZonedDateTime upperBoundary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void invalidateCachesWithTimeStamp(SSLSessionContext sslSessionContext, Predicate<ZonedDateTime> timeStampPredicate) {
        SSLSessionUtils.getSslSessions(sslSessionContext).stream().filter(sslSession -> timeStampPredicate.test(EPOCH_TIME_MAPPER.apply(sslSession.getCreationTime()))).forEach(SSLSession::invalidate);
    }

    public static void updateSessionTimeout(SSLFactory sslFactory, int timeoutInSeconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void updateSessionTimeout(SSLContext sslContext, int timeoutInSeconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void updateSessionCacheSize(SSLFactory sslFactory, int cacheSizeInBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void updateSessionCacheSize(SSLContext sslContext, int cacheSizeInBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void validateSessionTimeout(int timeoutInSeconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void validateSessionCacheSize(int cacheSizeInBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<SSLSession> getServerSslSessions(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<SSLSession> getServerSslSessions(SSLContext sslContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<SSLSession> getClientSslSessions(SSLFactory sslFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<SSLSession> getClientSslSessions(SSLContext sslContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<SSLSession> getSslSessions(SSLSessionContext sslSessionContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
