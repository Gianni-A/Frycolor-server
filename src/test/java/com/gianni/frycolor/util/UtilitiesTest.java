package com.gianni.frycolor.util;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

public class UtilitiesTest {
	
	@Test
	public void encodeBase64Test() {
		String textEncoded = Utilities.encodeOrDecodeBase64("test", true);
		Assert.assertEquals("dGVzdA==", textEncoded);
	}
	
	@Test
	public void decodeBase64Test() {
		String textDecoded = Utilities.encodeOrDecodeBase64("dGVzdA==", false);
		Assert.assertEquals("test", textDecoded);
	}
	
	@Test
	public void validateEmailFormatTest() {
		String emailTest = "testing@prueba.com";
		Assert.assertTrue(Utilities.validateEmailFormat(emailTest));
	}

}
