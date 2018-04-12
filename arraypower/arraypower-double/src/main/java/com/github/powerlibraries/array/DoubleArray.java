package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;



import com.github.powerlibraries.primitive.collections.DoubleCollection;
import com.github.powerlibraries.primitive.collections.DoubleList;

public interface DoubleArray extends DoubleList, Array<Double>, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static DoubleArray EMPTY = new DefaultDoubleArray(new double[0]);
	
	@SuppressWarnings("unchecked")
	public static  DoubleArray empty() {
		return EMPTY;
	}
	
	public static  DoubleArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultDoubleArray(new double[size]);
	}
	
	public static  DoubleArray copy(Collection<? extends Double> c) {
		DoubleArray result = ofSize(c.size());
		int i=0;
		for(Double v:c)
			result.setDouble(i++, v);
		return result;
	}
	
	public static  DoubleArray copy(DoubleArray arr) {
		return new DefaultDoubleArray(arr.toDoubleArray());
	}
	
	public static  DoubleArray copy(double[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray copy(Double[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray copy(Double[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray wrap(double[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray wrap(DoubleArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray copy(double[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray wrap(double[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray wrap(DoubleArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(DoubleArray arr, double element) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(DoubleArray arr, double... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(double element, DoubleArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(double[] elements, DoubleArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(DoubleArray arr, Collection<? extends Double> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(Collection<? extends Double> elements, DoubleArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray concat(DoubleArray arr, DoubleArray arr2) {
		// TODO Auto-generated method stub
	}
	
	public static  DoubleArray reverse(DoubleArray arr) {
		double[] a = new double[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getDouble(arr.size()-i-1);
		return new DefaultDoubleArray(a);
	}



	//Interface methods
	
	@Override
	public default boolean addDouble(double e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllDoubles(DoubleCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default DoubleArray subList(int fromIndex, int toIndex) {
		return DoubleArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default DoubleArray subArray(int offset, int length) {
		return DoubleArray.wrap(this, offset, length);
	}
	
	
	public int binarySearch(double key);
	
	public void fill(double val);
	
	public double[] getInternalArray();
	
	public int getInternalOffset();
}