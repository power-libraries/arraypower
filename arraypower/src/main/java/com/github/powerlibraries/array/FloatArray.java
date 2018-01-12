package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;



import com.github.powerlibraries.primitive.collections.FloatList;

public interface FloatArray extends FloatList, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static FloatArray EMPTY = new DefaultFloatArray(new float[0]);
	
	@SuppressWarnings("unchecked")
	public static  FloatArray empty() {
		return EMPTY;
	}
	
	public static  FloatArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultFloatArray(new float[size]);
	}
	
	public static  FloatArray copy(Collection<? extends Float> c) {
		FloatArray result = ofSize(c.size());
		int i=0;
		for(Float v:c)
			result.setFloat(i++, v);
		return result;
	}
	
	public static  FloatArray copy(FloatArray arr) {
		return new DefaultFloatArray(arr.toFloatArray());
	}
	
	public static  FloatArray copy(float[] arr) {
		...
	}
	
	public static  FloatArray copy(Float[] arr) {
		...
	}
	
	public static  FloatArray copy(Float[] arr, int offset, int length) {
		...
	}
	
	public static  FloatArray wrap(float[] arr) {
		...
	}
	
	public static  FloatArray wrap(FloatArray arr) {
		...
	}
	
	public static  FloatArray copy(float[] arr, int offset, int length) {
		...
	}
	
	public static  FloatArray wrap(float[] arr, int offset, int length) {
		...
	}
	
	public static  FloatArray wrap(FloatArray arr, int offset, int lengt) {
		...
	}
	
	public static  FloatArray concat(FloatArray arr, float element) {
		...
	}
	
	public static  FloatArray concat(FloatArray arr, float... elements) {
		...
	}
	
	public static  FloatArray concat(float element, FloatArray arr) {
		...
	}
	
	public static  FloatArray concat(float[] elements, FloatArray arr) {
		...
	}
	
	public static  FloatArray concat(FloatArray arr, Collection<? extends Float> elements) {
		...
	}
	
	public static  FloatArray concat(Collection<? extends Float> elements, FloatArray arr) {
		...
	}
	
	public static  FloatArray concat(FloatArray arr, FloatArray arr2) {
		...
	}
	
	public static  FloatArray reverse(FloatArray arr) {
		float[] a = new float[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getFloat(arr.size()-i-1);
		return new DefaultFloatArray(a);
	}



	//Interface methods
	
	@Override
	public default boolean addBoolean(boolean e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllBooleans(BooleanCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default FloatArray subList(int fromIndex, int toIndex) {
		return FloatArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default FloatArray subArray(int offset, int length) {
		return FloatArray.wrap(this, offset, length);
	}
	
	
	public int binarySearch(float key);
	
	public void fill(float val);
}