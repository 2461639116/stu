package com.tedu.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
@Component//包扫面
public class HelloFallback implements ZuulFallbackProvider{

	@Override
	public String getRoute() {
		return "provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		// 设置返回值，通常用json，utf-8解决中文乱码
		return new ClientHttpResponse() {
			
			@Override//请求响应头信息
			public HttpHeaders getHeaders() {
				// 返回类型是json，字符类型是utf-8
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return header;
			}
			
			@Override//响应头
			public InputStream getBody() throws IOException {
				//放回具体的响应内容
				String returnValue="tony888";
				return new ByteArrayInputStream(returnValue.getBytes());
			}
			
			@Override//返回文字描述
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}
			
			@Override//返回的状态码
			public HttpStatus getStatusCode() throws IOException {
				
				return HttpStatus.BAD_REQUEST;
			}
			
			@Override//返回的二进制
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override//关闭资源
			public void close() {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
