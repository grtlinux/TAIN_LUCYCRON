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
package tain.kr.test.etc.v00;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTest01.java
 *   -. Package    : tain.kr.com.test.generic.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTest01 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTest01.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unused")
	private static class Generic<T> {
		T radius;
		T area;
		public double get(T radius) {
			double r = (double) radius;
			double val = 2 * 3.14 * r;
			return val;
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static class Box<T> {
		// T stands for "Type"
		private T t;
		
		public void set(T t) {
			this.t = t;
		}
		
		public T get() {
			return t;
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static interface Pair<K, V> {
		public K getKey();
		public V getValue();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static class Util {
		
		public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {
			return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unused")
	private static class OrderedPair<K,V> implements Pair<K,V> {
		private K key;
		private V value;
		
		public OrderedPair() {
			this.key = null;
			this.value = null;
		}
		
		public OrderedPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return this.key;
		}
		
		@Override
		public V getValue() {
			return this.value;
		}
		
		public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {
			return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
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
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	@SuppressWarnings("unused")
	private static void test01(String[] args) throws Exception {

		if (flag) {
			/*
			 * begin
			 */
			Box<Integer> box = new Box<Integer>();
			box.set(10);
			box.get();
			
			// Pair<String, String> pair0 = new OrderedPair<String, String>();
			
			Pair<String, Integer> pair1 = new OrderedPair<String, Integer>(null, 0);
			
			Pair<String, Integer> pair2 = new OrderedPair<String, Integer>("Hello", 23);
			
			Pair<String, Integer> pair3 = new OrderedPair<>("Hello", 48);  // be equal to or more than jdk1.7
			
			Pair<Integer, String> p1 = new OrderedPair<Integer, String>(1, "apple");
			
			Pair<Integer, String> p2 = new OrderedPair<Integer, String>(3, "peer");
			
			boolean isSame = Util.<Integer,String>compare(p1, p2);
			
			System.out.printf("isSame = [%s]\n", isSame);
		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object() {
			}.getClass().getEnclosingClass().getName());

		if (flag)
			test01(args);
	}
}
























