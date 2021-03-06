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
package tain.kr.test.junit.v01;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestJunit01.java
 *   -. Package    : tain.kr.test.junit.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 13. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class TestJunit01 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TestJunit01.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private interface ImpRequest {
		public abstract String getName();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private interface ImpResponse {
		public abstract String getName();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private interface ImpRequestHandler {
		public abstract ImpResponse process(ImpRequest request) throws Exception;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private interface ImpController {
		
		public abstract void addHandler(ImpRequest request, ImpRequestHandler handler);
		public abstract ImpRequestHandler getHandler(ImpRequest request);
		
		public abstract ImpResponse getResponse(ImpRequest request);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class Request implements ImpRequest {
		
		private String name;
		
		public Request(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class Response implements ImpResponse {
		
		private String name;
		
		public Response(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return this.name;
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
		
		@SuppressWarnings("unused")
		public ImpRequest getRequest() {
			return this.request;
		}
		
		@SuppressWarnings("unused")
		public Exception getException() {
			return this.exception;
		}
		
		@Override
		public String getName() {
			return "ResponseError";
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class RequestHandler implements ImpRequestHandler {
		
		@Override
		public ImpResponse process(ImpRequest request) throws Exception {
			return new Response(request.getName());
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
	
	private final class Controller implements ImpController {
		
		private Map<String, ImpRequestHandler> map = new HashMap<String, ImpRequestHandler>();
		
		@Override
		public void addHandler(ImpRequest request, ImpRequestHandler handler) {
			
			if (this.map.containsKey(request.getName())) {
				throw new RuntimeException(String.format("a request handler has already been registered for request name [%s].", request.getName()));
			} else {
				this.map.put(request.getName(), handler);
			}
		}
		
		@Override
		public ImpRequestHandler getHandler(ImpRequest request) {
			
			if (!this.map.containsKey(request.getName())) {
				throw new RuntimeException(String.format("Cannot find handler for request name [%s].", request.getName()));
			}
			
			return this.map.get(request.getName());
		}
		
		@Override
		public ImpResponse getResponse(ImpRequest request) {
			
			ImpResponse response;
			
			try {
				response = this.getHandler(request).process(request);
			} catch (Exception e) {
				response = new ErrorResponse(request, e);
			}
			
			return response;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ImpController controller;
	private ImpRequest request;
	private ImpRequestHandler handler;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Before
	public void initialize() throws Exception {
		this.controller = new Controller();
		this.request = new Request("Hello, world!!!");
		this.handler = new RequestHandler();
		
		this.controller.addHandler(request, handler);
		
		if (!flag) log.debug("@Before initialize() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testAddHandler() {
		ImpRequestHandler handler2 = this.controller.getHandler(request);
		
		assertEquals(handler2, this.handler);
		
		if (!flag) log.debug("testAddHandler() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testProcessResponse() {
		ImpResponse response = this.controller.getResponse(this.request);
		
		assertNotNull(response);
		assertEquals(Response.class, response.getClass());
		
		if (!flag) log.debug("testProcessResponse() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testProcessRequestAnswerErrorResponse() {
		ImpRequest request = new Request("testError");
		ImpRequestHandler handler = new ExceptionHandler();
		this.controller.addHandler(request, handler);
		
		ImpResponse response = this.controller.getResponse(request);
		
		assertNotNull(response);
		assertEquals(ErrorResponse.class, response.getClass());
		
		if (!flag) log.debug("testProcessRequestAnswerErrorResponse() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test ( expected = RuntimeException.class )
	public void testGetHandlerNotDefined() {
		ImpRequest request = new Request("testNotDefined");
		
		// occurred the event RuntimeException
		this.controller.getHandler(request);
		
		if (!flag) log.debug("testGetHandlerNotDefined() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test ( expected = RuntimeException.class )
	public void testAddRequestDuplication() {
		ImpRequest request = new Request("Hello, world!!!");
		ImpRequestHandler handler = new RequestHandler();
		
		// occurred the event RuntimeException
		this.controller.addHandler(request, handler);
		
		if (!flag) log.debug("testAddRequestDuplication() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Ignore
	@Test ( timeout = 10 )   // millisecond
	public void testProcessMultipleRequestsTimeout() {
		ImpRequest request;
		ImpRequestHandler handler = new RequestHandler();
		ImpResponse response;
		
		for (int i=0; i < 99999; i++) {
			request = new Request(String.valueOf(i));
			this.controller.addHandler(request, handler);
			response = this.controller.getResponse(request);
			
			assertNotNull(response);
			assertNotSame(ErrorResponse.class, response.getClass());
		}
		
		if (!flag) log.debug("testProcessMultipleRequestsTimeout() be done!!!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
}
