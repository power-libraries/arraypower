package com.github.powerlibraries.array;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.powerlibraries.array.ShortArray;

public class ShortArrayTest {

	@Test
	public void test() {
		ShortArray arr = ShortArray.ofSize(17);
		Random r = new Random(7);
		List<Short> comparison = new ArrayList<>();
		
		for(int i=0; i<17; i++) {
			Short value = ((short)r.nextInt(9));
			arr.setShort(i, value);
			comparison.add(value);
		}
		
		assertThat(arr)
			.containsExactlyElementsOf(comparison)
			.isEqualTo(comparison)
			.hasSameSizeAs(comparison)
			.doesNotContainNull();
		
	}
}