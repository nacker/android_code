package com.devlei.demo;

import android.test.AndroidTestCase;

/**
 * 单元测试可以测试一个方法 也可以测试多个方法
 * 1.写一个类继承AndroidTestCase
 * */
public class CalculatorTest extends AndroidTestCase{

	/**
	 * 1.写一个方法 用来测试想要测试业务方法
	 * 测试的时候可能会报错 要把错误告诉系统
	 * */
	public void testAdd()throws Exception{
		int result = MockCalculator.add(3, 5);
		//expected 期望的值  actual实际得到的结果
		assertEquals(8, result);
	}

	//2. 单元测试 does not specify a
	//android.test.InstrumentationTestRunner instrumentation
	// or does not declare uses-library android.test.runner
	//in its AndroidManifest.xml

	public void testMulti()throws Exception{
		int result = MockCalculator.multi(3, 5);
		//expected 期望的值  actual实际得到的结果
		assertEquals(15, result);
	}
}
