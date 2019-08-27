package com.github.powerlibraries.array;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.powerlibraries.array.IntArray;

public class IntArrayTest {

	@Test
	public void test() {
		IntArray arr = IntArray.ofSize(17);
		Random r = new Random(7);
		List<Integer> comparison = new ArrayList<>();
		
		for(int i=0; i<17; i++) {
			Integer value = ((int)r.nextInt(9));
			arr.setInt(i, value);
			comparison.add(value);
		}
		
		assertThat(arr)
			.containsExactlyElementsOf(comparison)
			.isEqualTo(comparison)
			.hasSameSizeAs(comparison)
			.doesNotContainNull();
		
	}
}