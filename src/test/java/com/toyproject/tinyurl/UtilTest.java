package com.toyproject.tinyurl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.toyproject.tinyurl.util.Base62Util;

@SpringBootTest
public class UtilTest {
	@Test
	void base62UtilTest() {
		Base62Util util = new Base62Util();
		System.out.println(util.encoding(123456789L));
		assertEquals("2tx", util.encoding(11157L));
		assertEquals("8M0kX", util.encoding(123456789L));
	}

}
