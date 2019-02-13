package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.ByteCollection;
import com.github.powerlibraries.primitive.collections.ByteList;

public interface ByteArray extends ByteList, Array<Byte>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static ByteArray EMPTY = new DefaultByteArray(new byte[0]);
	
	@SuppressWarnings("unchecked")
	static  ByteArray empty() {
		return EMPTY;
	}
	
	static  ByteArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultByteArray(new byte[size]);
	}
	
	static  ByteArray copy(Collection<? extends Byte> c) {
		ByteArray result = ofSize(c.size());
		int i=0;
		for(Byte v:c)
			result.setByte(i++, v);
		return result;
	}
	
	static  ByteArray copy(ByteArray arr) {
		return new DefaultByteArray(arr.toByteArray());
	}
	
	static  ByteArray copy(byte[] arr) {
		return new DefaultByteArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  ByteArray copy(byte[] arr, int offset, int length) {
		return new DefaultByteArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  ByteArray copy(Byte[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  ByteArray copy(Byte[] arr, int offset, int length) {
		byte[] copy = new byte[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultByteArray(copy);
	}
	
	static  ByteArray wrap(byte... arr) {
		return new DefaultByteArray(arr);
	}
	
	static  ByteArray wrap(ByteArray arr) {
		return new DefaultByteArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  ByteArray wrap(byte[] arr, int offset, int length) {
		return new DefaultByteArray(arr, offset, length);
	}

	static  ByteArray wrap(ByteArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultByteArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  ByteArray concat(ByteArray arr, byte element) {
		byte[] result = new byte[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  ByteArray concat(byte element, ByteArray arr) {
		byte[] result = new byte[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  ByteArray concat(ByteArray arr, Collection<? extends Byte> elements) {
		ByteArray result = ByteArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setByte(i, arr.getByte(i));
		}
		int index = arr.size();
		for(byte value : elements) {
			result.setByte(index++, value);
		}
		return result;
	}
	
	static  ByteArray concat(Collection<? extends Byte> elements, ByteArray arr) {
		ByteArray result = ByteArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(byte value : elements) {
			result.setByte(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setByte(i + index, arr.getByte(i));
		}
		return result;
	}
	
	static  ByteArray concat(ByteArray arr, ByteArray arr2) {
		ByteArray result = ByteArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setByte(i, arr.getByte(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setByte(i + arr2.size(), arr2.getByte(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addByte(byte e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addByte(int index, byte element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllBytes(ByteCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Byte> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Byte> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default ByteArray subList(int fromIndex, int toIndex) {
		return ByteArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default ByteArray subArray(int offset, int length) {
		return ByteArray.wrap(this, offset, length);
	}
	
	default void copyTo(byte[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(byte[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(byte key);
	
	void fill(byte val);
	
	byte[] getInternalArray();
	
	int getInternalOffset();
}