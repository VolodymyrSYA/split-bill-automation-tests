package com.split.bill.core.utils;

import com.google.common.io.Files;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    @SneakyThrows
    public static byte[] convertFileToByteArray(final File path) {
        return Files.asByteSource(path).read();
    }
}
