/**
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc.
 *
 */
package tain.kr.test.junit.v00;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestController01.java
 *   -. Package    : tain.kr.test.junit.v00
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 12. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class TestController01 {

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private interface ImpTest {
		public abstract void test() throws Exception;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class RequestTest implements ImpRequest {
		
		private String name;
		
		public RequestTest(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int hashCode() {
			return this.name.hashCode();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class ResponseTest implements ImpResponse {
		
		private String name;
		
		public ResponseTest(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int hashCode() {
			return this.name.hashCode();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class ErrorResponse implements ImpResponse {
		
		private ImpRequest request;
		private Exception exception;
		
		public ErrorResponse(ImpRequest request, Exception exception) {
			this.request = request;
			this.exception = exception;
		}
		
		public ImpRequest getRequest() {
			return this.request;
		}
		
		public Exception getException() {
			return this.exception;
		}
		
		@Override
		public String getName() {
			return "ErrorResponse";
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class RequestHandlerTest implements ImpRequestHandler {
		
		@Override
		public ImpResponse process(ImpRequest request) throws Exception {
			return new ResponseTest(request.getName());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class ExceptionHandler implements ImpRequestHandler {
		
		@Override
		public ImpResponse process(ImpRequest request) throws Exception {
			throw new Exception("error event in processing request.");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class ControllerTest implements ImpController {
		
		private Map<String, ImpRequestHandler> map = new HashMap<String, ImpRequestHandler>();
		
		@Override
		public void addHandler(ImpRequest request, ImpRequestHandler handler) {
			
			if (this.map.containsKey(request.getName())) {
				throw new RuntimeException(String.format("a request handler has already been registered for request name [%s]", request.getName()));
			} else {
				this.map.put(request.getName(), handler);
			}
		}
		
		@Override
		public ImpRequestHandler getHandler(ImpRequest request) {
			
			if (!this.map.containsKey(request.getName())) {
				throw new RuntimeException(String.format("", request.getName()));
			}
			
			return this.map.get(request.getName());
		}
		
		@Override
		public ImpResponse getResponse(ImpRequest request) {
			
			ImpResponse response;
			
			try {
				response = getHandler(request).process(request);
			} catch (Exception e) {
				response = new ErrorResponse(request, e);
			}
			
			return response;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ImpController controller;
	private ImpRequest request;
	private ImpRequestHandler handler;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Before
	public void initialize() throws Exception {
		this.controller = new ControllerTest();
		this.request = new RequestTest("Hello, world!!!");
		this.handler = new RequestHandlerTest();
		
		this.controller.addHandler(this.request, this.handler);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testAddHandler() {
		ImpRequestHandler handler2 = this.controller.getHandler(this.request);
		
		assertEquals(handler2, this.handler);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testProcessResponse() {
		ImpResponse response = this.controller.getResponse(this.request);
		
		assertNotNull(response);
		assertEquals(ResponseTest.class, response.getClass());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testProcessRequestAnswerErrorResponse() {
		ImpRequest request = new RequestTest("testError");
		ImpRequestHandler handler = new ExceptionHandler();
		this.controller.addHandler(request, handler);
		ImpResponse response = this.controller.getResponse(request);
		
		assertNotNull(response);
		assertEquals(ErrorResponse.class, response.getClass());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test ( expected = RuntimeException.class )
	public void testGetHandlerNotDefined() {
		ImpRequest request = new RequestTest("testNotDefined");
		
		// occured the event RuntimeException
		this.controller.getHandler(request);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test ( expected = RuntimeException.class )
	public void testAddRequestDuplication() {
		ImpRequest request = new RequestTest("Hello, world!!!");
		ImpRequestHandler handler = new RequestHandlerTest();
		
		// occured the event RuntimeException
		this.controller.addHandler(request, handler);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Ignore
	@Test ( timeout = 10 )   // milliseconds
	public void testProcessMultipleRequestsTimeout() {
		ImpRequest request;
		ImpRequestHandler handler = new RequestHandlerTest();
		ImpResponse response;
		
		for (int i=0; i < 99999; i++) {
			request = new RequestTest(String.valueOf(i));
			this.controller.addHandler(request, handler);
			response = this.controller.getResponse(request);
			
			assertNotNull(response);
			assertNotSame(ErrorResponse.class, response.getClass());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
}
