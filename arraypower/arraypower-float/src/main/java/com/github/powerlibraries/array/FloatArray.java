package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.FloatCollection;
import com.github.powerlibraries.primitive.collections.FloatList;

public interface FloatArray extends FloatList, Array<Float>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static FloatArray EMPTY = new DefaultFloatArray(new float[0]);
	
	@SuppressWarnings("unchecked")
	static  FloatArray empty() {
		return EMPTY;
	}
	
	static  FloatArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultFloatArray(new float[size]);
	}
	
	static  FloatArray copy(Collection<? extends Float> c) {
		FloatArray result = ofSize(c.size());
		int i=0;
		for(Float v:c)
			result.setFloat(i++, v);
		return result;
	}
	
	static  FloatArray copy(FloatArray arr) {
		return new DefaultFloatArray(arr.toFloatArray());
	}
	
	static  FloatArray copy(float[] arr) {
		return new DefaultFloatArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  FloatArray copy(float[] arr, int offset, int length) {
		return new DefaultFloatArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  FloatArray copy(Float[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  FloatArray copy(Float[] arr, int offset, int length) {
		float[] copy = new float[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultFloatArray(copy);
	}
	
	static  FloatArray wrap(float... arr) {
		return new DefaultFloatArray(arr);
	}
	
	static  FloatArray wrap(FloatArray arr) {
		return new DefaultFloatArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  FloatArray wrap(float[] arr, int offset, int length) {
		return new DefaultFloatArray(arr, offset, length);
	}

	static  FloatArray wrap(FloatArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultFloatArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  FloatArray concat(FloatArray arr, float element) {
		float[] result = new float[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  FloatArray concat(float element, FloatArray arr) {
		float[] result = new float[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  FloatArray concat(FloatArray arr, Collection<? extends Float> elements) {
		FloatArray result = FloatArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setFloat(i, arr.getFloat(i));
		}
		int index = arr.size();
		for(float value : elements) {
			result.setFloat(index++, value);
		}
		return result;
	}
	
	static  FloatArray concat(Collection<? extends Float> elements, FloatArray arr) {
		FloatArray result = FloatArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(float value : elements) {
			result.setFloat(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setFloat(i + index, arr.getFloat(i));
		}
		return result;
	}
	
	static  FloatArray concat(FloatArray arr, FloatArray arr2) {
		FloatArray result = FloatArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setFloat(i, arr.getFloat(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setFloat(i + arr2.size(), arr2.getFloat(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addFloat(float e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addFloat(int index, float element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllFloats(FloatCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Float> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Float> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default FloatArray subList(int fromIndex, int toIndex) {
		return FloatArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default FloatArray subArray(int offset, int length) {
		return FloatArray.wrap(this, offset, length);
	}
	
	default void copyTo(float[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(float[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(float key);
	
	void fill(float val);
	
	float[] getInternalArray();
	
	int getInternalOffset();
}