package com.github.powerlibraries.array;

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
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray copy(byte[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray copy(Byte[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray copy(Byte[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray wrap(byte[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray wrap(ByteArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray wrap(byte[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray wrap(ByteArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray concat(ByteArray arr, byte element) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray concat(ByteArray arr, byte... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray concat(byte element, ByteArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ByteArray concat(byte[] elements, ByteArray arr) {
		// TODO Auto-generated method stub
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
	public default boolean addAllBytes(ByteCollection c) throws UnsupportedOperationException {
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
	
	@Override
	ByteArray reverse();
}