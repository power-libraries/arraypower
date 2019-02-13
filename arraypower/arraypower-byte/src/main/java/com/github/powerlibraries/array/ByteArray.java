package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.ByteCollection;
import com.github.powerlibraries.primitive.collections.ByteList;

public interface ByteArray extends ByteList, Array<Byte>, RandomAccess {

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
		return new DefaultByteArray(Arrays.copyOf(arr, arr.length));
	}
	
	public static  ByteArray copy(byte[] arr, int offset, int length) {
		return new DefaultByteArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	public static  ByteArray copy(Byte[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	public static  ByteArray copy(Byte[] arr, int offset, int length) {
		byte[] copy = new byte[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultByteArray(copy);
	}
	
	public static  ByteArray wrap(byte... arr) {
		return new DefaultByteArray(arr);
	}
	
	public static  ByteArray wrap(ByteArray arr) {
		return new DefaultByteArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static  ByteArray wrap(byte[] arr, int offset, int length) {
		return new DefaultByteArray(arr, offset, length);
	}

	public static  ByteArray wrap(ByteArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultByteArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static  ByteArray concat(ByteArray arr, byte element) {
		byte[] result = new byte[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	public static  ByteArray concat(byte element, ByteArray arr) {
		byte[] result = new byte[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	public static  ByteArray concat(ByteArray arr, Collection<? extends Byte> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray concat(Collection<? extends Byte> elements, ByteArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray concat(ByteArray arr, ByteArray arr2) {
		// TODO Auto-generated method stub
	}



	//Interface methods
	
	@Override
	public default boolean addByte(byte e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default void addByte(int index, byte element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllBytes(ByteCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAll(Collection<? extends Byte> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public default boolean addAll(int index, Collection<? extends Byte> c) throws UnsupportedOperationException {
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
	
	public byte[] getInternalArray();
	
	public int getInternalOffset();
}