package com.wxm.security.wrapper;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/8/8 13:57
 * @since 1.0.0
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private String body = null;

    public MyHttpServletRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
        super(request);
        body = new String(IOUtils.toByteArray(request.getInputStream()), "UTF-8");
    }

    @Override
    public String getMethod() {
        return super.getMethod();
    }

    public String getBody() {
        return this.body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes("UTF-8"));
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), Charsets.UTF_8));
    }
}
