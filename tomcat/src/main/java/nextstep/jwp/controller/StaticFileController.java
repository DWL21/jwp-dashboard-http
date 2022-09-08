package nextstep.jwp.controller;

import java.net.URL;
import nextstep.jwp.http.ContentType;
import nextstep.jwp.http.HttpRequest;
import nextstep.jwp.http.HttpResponse;
import nextstep.jwp.http.StatusCode;
import nextstep.jwp.utils.FileUtils;
import org.apache.controller.AbstractController;

public class StaticFileController extends AbstractController {

    private static final String FILE_EXTENSION_SEPARATOR = ".";

    @Override
    protected HttpResponse handleGet(HttpRequest request) {
        URL resource = getResource(request);
        if (resource == null) {
            return notfound();
        }
        return HttpResponse.of(StatusCode.OK, request.getFileExtension(), FileUtils.readFile(resource));
    }

    public URL getResource(HttpRequest request) {
        String path = request.getPath();
        if (request.matches(ContentType.TEXT_PLAIN)) {
            String filePath =
                path + FILE_EXTENSION_SEPARATOR + ContentType.TEXT_HTML.getFileExtension();
            return FileUtils.getResource(filePath);
        }
        return FileUtils.getResource(path);
    }
}