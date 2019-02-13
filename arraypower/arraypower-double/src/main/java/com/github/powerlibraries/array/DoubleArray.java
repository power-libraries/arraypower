package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.DoubleCollection;
import com.github.powerlibraries.primitive.collections.DoubleList;

public interface DoubleArray extends DoubleList, Array<Double>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static DoubleArray EMPTY = new DefaultDoubleArray(new double[0]);
	
	@SuppressWarnings("unchecked")
	static  DoubleArray empty() {
		return EMPTY;
	}
	
	static  DoubleArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultDoubleArray(new double[size]);
	}
	
	static  DoubleArray copy(Collection<? extends Double> c) {
		DoubleArray result = ofSize(c.size());
		int i=0;
		for(Double v:c)
			result.setDouble(i++, v);
		return result;
	}
	
	static  DoubleArray copy(DoubleArray arr) {
		return new DefaultDoubleArray(arr.toDoubleArray());
	}
	
	static  DoubleArray copy(double[] arr) {
		return new DefaultDoubleArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  DoubleArray copy(double[] arr, int offset, int length) {
		return new DefaultDoubleArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  DoubleArray copy(Double[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  DoubleArray copy(Double[] arr, int offset, int length) {
		double[] copy = new double[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultDoubleArray(copy);
	}
	
	static  DoubleArray wrap(double... arr) {
		return new DefaultDoubleArray(arr);
	}
	
	static  DoubleArray wrap(DoubleArray arr) {
		return new DefaultDoubleArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  DoubleArray wrap(double[] arr, int offset, int length) {
		return new DefaultDoubleArray(arr, offset, length);
	}

	static  DoubleArray wrap(DoubleArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultDoubleArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  DoubleArray concat(DoubleArray arr, double element) {
		double[] result = new double[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  DoubleArray concat(double element, DoubleArray arr) {
		double[] result = new double[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  DoubleArray concat(DoubleArray arr, Collection<? extends Double> elements) {
		DoubleArray result = DoubleArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setDouble(i, arr.getDouble(i));
		}
		int index = arr.size();
		for(double value : elements) {
			result.setDouble(index++, value);
		}
		return result;
	}
	
	static  DoubleArray concat(Collection<? extends Double> elements, DoubleArray arr) {
		DoubleArray result = DoubleArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(double value : elements) {
			result.setDouble(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setDouble(i + index, arr.getDouble(i));
		}
		return result;
	}
	
	static  DoubleArray concat(DoubleArray arr, DoubleArray arr2) {
		DoubleArray result = DoubleArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setDouble(i, arr.getDouble(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setDouble(i + arr2.size(), arr2.getDouble(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addDouble(double e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addDouble(int index, double element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllDoubles(DoubleCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Double> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Double> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default DoubleArray subList(int fromIndex, int toIndex) {
		return DoubleArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default DoubleArray subArray(int offset, int length) {
		return DoubleArray.wrap(this, offset, length);
	}
	
	default void copyTo(double[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(double[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(double key);
	
	void fill(double val);
	
	double[] getInternalArray();
	
	int getInternalOffset();
}