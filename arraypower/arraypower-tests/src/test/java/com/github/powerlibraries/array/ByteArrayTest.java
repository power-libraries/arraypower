package com.github.powerlibraries.array;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.powerlibraries.array.ByteArray;

public class ByteArrayTest {

	@Test
	public void test() {
		ByteArray arr = ByteArray.ofSize(17);
		Random r = new Random(7);
		List<Byte> comparison = new ArrayList<>();
		
		for(int i=0; i<17; i++) {
			Byte value = ((byte)r.nextInt(9));
			arr.setByte(i, value);
			comparison.add(value);
		}
		
		assertThat(arr)
			.containsExactlyElementsOf(comparison)
			.isEqualTo(comparison)
			.hasSameSizeAs(comparison)
			.doesNotContainNull();
		
	}
}