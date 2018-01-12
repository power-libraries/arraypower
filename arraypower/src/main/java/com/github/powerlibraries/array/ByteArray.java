package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;



import com.github.powerlibraries.primitive.collections.ByteList;

public interface ByteArray extends ByteList, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static ByteArray EMPTY = new DefaultByteArray(new byte[0]);
	
	@SuppressWarnings("unchecked")
	public static  ByteArray empty() {
		return EMPTY;
	}
	
	public static  ByteArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultByteArray(new byte[size]);
	}
	
	public static  ByteArray copy(Collection<? extends Byte> c) {
		ByteArray result = ofSize(c.size());
		int i=0;
		for(Byte v:c)
			result.setByte(i++, v);
		return result;
	}
	
	public static  ByteArray copy(ByteArray arr) {
		return new DefaultByteArray(arr.toByteArray());
	}
	
	public static  ByteArray copy(byte[] arr) {
		...
	}
	
	public static  ByteArray copy(Byte[] arr) {
		...
	}
	
	public static  ByteArray copy(Byte[] arr, int offset, int length) {
		...
	}
	
	public static  ByteArray wrap(byte[] arr) {
		...
	}
	
	public static  ByteArray wrap(ByteArray arr) {
		...
	}
	
	public static  ByteArray copy(byte[] arr, int offset, int length) {
		...
	}
	
	public static  ByteArray wrap(byte[] arr, int offset, int length) {
		...
	}
	
	public static  ByteArray wrap(ByteArray arr, int offset, int lengt) {
		...
	}
	
	public static  ByteArray concat(ByteArray arr, byte element) {
		...
	}
	
	public static  ByteArray concat(ByteArray arr, byte... elements) {
		...
	}
	
	public static  ByteArray concat(byte element, ByteArray arr) {
		...
	}
	
	public static  ByteArray concat(byte[] elements, ByteArray arr) {
		...
	}
	
	public static  ByteArray concat(ByteArray arr, Collection<? extends Byte> elements) {
		...
	}
	
	public static  ByteArray concat(Collection<? extends Byte> elements, ByteArray arr) {
		...
	}
	
	public static  ByteArray concat(ByteArray arr, ByteArray arr2) {
		...
	}
	
	public static  ByteArray reverse(ByteArray arr) {
		byte[] a = new byte[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getByte(arr.size()-i-1);
		return new DefaultByteArray(a);
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
	public default ByteArray subList(int fromIndex, int toIndex) {
		return ByteArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default ByteArray subArray(int offset, int length) {
		return ByteArray.wrap(this, offset, length);
	}
	
	
	public int binarySearch(byte key);
	
	public void fill(byte val);
}