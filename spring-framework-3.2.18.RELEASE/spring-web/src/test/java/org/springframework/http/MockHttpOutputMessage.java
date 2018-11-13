/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static org.mockito.BDDMockito.*;

/**
 * @author Arjen Poutsma
 */
public class MockHttpOutputMessage implements HttpOutputMessage {

	private final HttpHeaders headers = new HttpHeaders();

	private final ByteArrayOutputStream body = spy(new ByteArrayOutputStream());

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}

	@Override
	public OutputStream getBody() throws IOException {
		return body;
	}

	public byte[] getBodyAsBytes() {
		return body.toByteArray();
	}

	public String getBodyAsString(Charset charset) {
		byte[] bytes = getBodyAsBytes();
		return new String(bytes, charset);
	}
}