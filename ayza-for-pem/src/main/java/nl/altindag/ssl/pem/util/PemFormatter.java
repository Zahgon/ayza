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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static nl.altindag.laleler.CollectorsUtils.toModifiableList;

final class PemFormatter {

    private static final Pattern PEM_PATTERN = Pattern.compile("(-----BEGIN.*?-----)(.*?)(-----END.*?-----)");

    private static final String INNER_ENCRYPTED_HEADER = "Proc-Type: 4,ENCRYPTED";

    private static final Map<String, Integer> ENCRYPTION_ALGORITHMS_AND_SALT_TO_FIELD_LENGTH;

    private static final String MAX_64_CHARACTER_LINE_SPLITTER = "(?<=\\G.{64})";

    private static final String EMPTY = "";

    static {
        Map<String, Integer> encryptionAlgorithmsAndSaltToFieldLength = new HashMap<>();
        encryptionAlgorithmsAndSaltToFieldLength.put("AES-256-CBC", 54);
        encryptionAlgorithmsAndSaltToFieldLength.put("DES-EDE3-CBC", 39);
        ENCRYPTION_ALGORITHMS_AND_SALT_TO_FIELD_LENGTH = Collections.unmodifiableMap(encryptionAlgorithmsAndSaltToFieldLength);
    }

    private PemFormatter() {
    }

    /**
     * It will try to format the provided input if it is a one-liner pem formatted certificate,
     * or else it will return the original input
     */
    static String reformatIfNeeded(String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Extracts the inner header of a pem formatted certificate. It currently supports two encryption algorithms DES-EDE3-CBC and AES-256-CBC. See below for the examples:
     *
     * example
     * <pre>
     * {@code
     * Proc-Type: 4,ENCRYPTED
     * DEK-Info: DES-EDE3-CBC,9A4511D01C56B15D
     * }
     * </pre>
     * or
     * <pre>
     * {@code
     * Proc-Type: 4,ENCRYPTED
     * DEK-Info: AES-256-CBC,AB8E2B5B2D989271273F6730B6F9C687
     * }
     * </pre>
     */
    private static List<String> extractInnerEncryptionHeaderIfPossible(String value) {
        if (!value.contains(INNER_ENCRYPTED_HEADER)) {
            return Collections.emptyList();
        }
        for (Map.Entry<String, Integer> encryptionAlgorithmAndSaltToFieldLength : ENCRYPTION_ALGORITHMS_AND_SALT_TO_FIELD_LENGTH.entrySet()) {
            if (value.contains(encryptionAlgorithmAndSaltToFieldLength.getKey())) {
                String encryptionAlgorithmValue = value.substring(INNER_ENCRYPTED_HEADER.length(), INNER_ENCRYPTED_HEADER.length() + encryptionAlgorithmAndSaltToFieldLength.getValue());
                List<String> innerHeader = new ArrayList<>();
                innerHeader.add(INNER_ENCRYPTED_HEADER);
                innerHeader.add(encryptionAlgorithmValue);
                innerHeader.add(EMPTY);
                return innerHeader;
            }
        }
        throw new IllegalArgumentException(String.format("The provided encrypted private key is not supported. Supported formats are: [%s]", String.join(",", ENCRYPTION_ALGORITHMS_AND_SALT_TO_FIELD_LENGTH.keySet())));
    }
}
