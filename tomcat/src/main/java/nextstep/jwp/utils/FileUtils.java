package nextstep.jwp.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import nextstep.jwp.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static final String FILE_NAME_DELIMITER = ".";
    private static final int NO_EXISTING = -1;

    public static String extractFileExtension(String path) {
        int fileNameDelimiterIndex = path.lastIndexOf(FILE_NAME_DELIMITER);
        if (fileNameDelimiterIndex == NO_EXISTING) {
            return ContentType.TEXT_HTML.getFileExtension();
        }
        return path.substring(fileNameDelimiterIndex + 1);
    }

    public static String readFile(URL resource){
        try {
            return new String(Files.readAllBytes(new File(resource.getFile())
                .toPath()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }
}