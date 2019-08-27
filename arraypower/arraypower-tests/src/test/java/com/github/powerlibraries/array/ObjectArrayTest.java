package com.github.powerlibraries.array;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.powerlibraries.array.ObjectArray;

public class ObjectArrayTest<E> {

	@Test
	public void test() {
		ObjectArray<E> arr = ObjectArray.ofSize(17);
		Random r = new Random(7);
		List<E> comparison = new ArrayList<>();
		
		for(int i=0; i<17; i++) {
			E value = (E)TimeUnit.values()[r.nextInt(7)];
			arr.setObject(i, value);
			comparison.add(value);
		}
		
		assertThat(arr)
			.containsExactlyElementsOf(comparison)
			.isEqualTo(comparison)
			.hasSameSizeAs(comparison)
			.doesNotContainNull();
		
	}
}