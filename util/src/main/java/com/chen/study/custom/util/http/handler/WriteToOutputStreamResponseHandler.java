package com.chen.study.custom.util.http.handler;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 陈添明
 * @date 2018/12/24
 */
public class WriteToOutputStreamResponseHandler extends AbstractResponseHandler<Void> {

    private OutputStream outputStream;

    public WriteToOutputStreamResponseHandler(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public Void handleEntity(HttpEntity entity) throws IOException {
         entity.writeTo(outputStream);
        return null;
    }
}
