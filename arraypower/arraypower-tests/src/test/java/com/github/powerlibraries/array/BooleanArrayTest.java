package com.github.powerlibraries.array;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.powerlibraries.array.BooleanArray;

public class BooleanArrayTest {

	@Test
	public void test() {
		BooleanArray arr = BooleanArray.ofSize(17);
		Random r = new Random(7);
		List<Boolean> comparison = new ArrayList<>();
		
		for(int i=0; i<17; i++) {
			Boolean value = r.nextBoolean();
			arr.setBoolean(i, value);
			comparison.add(value);
		}
		
		assertThat(arr)
			.containsExactlyElementsOf(comparison)
			.isEqualTo(comparison)
			.hasSameSizeAs(comparison)
			.doesNotContainNull();
		
	}
}