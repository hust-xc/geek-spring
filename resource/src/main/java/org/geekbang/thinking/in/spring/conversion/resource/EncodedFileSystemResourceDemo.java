package org.geekbang.thinking.in.spring.conversion.resource;


import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResource} 示例
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        System.out.println(currentFilePath);
        FileSystemResource fileSystemResource = new FileSystemResource(currentFilePath);
        EncodedResource resource = new EncodedResource(fileSystemResource, "UTF-8");
        try (Reader reader = resource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
