package org.geekbang.thinking.in.spring.conversion.resource;

import org.geekbang.thinking.in.spring.conversion.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String currentPackagePath = System.getProperty("user.dir") + "/resource/src/main/java/org/geekbang/thinking/in/spring/resource/";
        String locationPattern = currentPackagePath + "*.java";

        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

        resourcePatternResolver.setPathMatcher(new JavaFilePathMatch());

        Resource[] resources = resourcePatternResolver.getResources(locationPattern);

        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);

    }

    static class JavaFilePathMatch implements PathMatcher {
        @Override
        public boolean isPattern(String s) {
            return s.endsWith(".java");
        }

        @Override
        public boolean match(String s, String s1) {
            return s1.endsWith(".java");
        }

        @Override
        public boolean matchStart(String s, String s1) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String s, String s1) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String s, String s1) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String s) {
            return null;
        }

        @Override
        public String combine(String s, String s1) {
            return null;
        }
    }


}
