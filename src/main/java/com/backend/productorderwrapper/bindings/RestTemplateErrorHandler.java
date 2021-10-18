package com.backend.productorderwrapper.bindings;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(final ClientHttpResponse httpResponse) throws IOException {
            if (httpResponse.getStatusCode().series()
                    == HttpStatus.Series.SERVER_ERROR || httpResponse.getBody().readAllBytes().length==0) {
                throw new HttpServerErrorException(HttpStatus.BAD_GATEWAY);
            } else if (httpResponse.getStatusCode().series()
                    == HttpStatus.Series.CLIENT_ERROR) {
                if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
                }
            }
    }
}
