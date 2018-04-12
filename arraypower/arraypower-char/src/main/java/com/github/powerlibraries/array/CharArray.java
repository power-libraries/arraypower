package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;



import com.github.powerlibraries.primitive.collections.CharCollection;
import com.github.powerlibraries.primitive.collections.CharList;

public interface CharArray extends CharList, Array<Character>, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static CharArray EMPTY = new DefaultCharArray(new char[0]);
	
	@SuppressWarnings("unchecked")
	public static  CharArray empty() {
		return EMPTY;
	}
	
	public static  CharArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultCharArray(new char[size]);
	}
	
	public static  CharArray copy(Collection<? extends Character> c) {
		CharArray result = ofSize(c.size());
		int i=0;
		for(Character v:c)
			result.setChar(i++, v);
		return result;
	}
	
	public static  CharArray copy(CharArray arr) {
		return new DefaultCharArray(arr.toCharArray());
	}
	
	public static  CharArray copy(char[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray copy(Character[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray copy(Character[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray wrap(char[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray wrap(CharArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray copy(char[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray wrap(char[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray wrap(CharArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(CharArray arr, char element) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(CharArray arr, char... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(char element, CharArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(char[] elements, CharArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(CharArray arr, Collection<? extends Character> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(Collection<? extends Character> elements, CharArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray concat(CharArray arr, CharArray arr2) {
		// TODO Auto-generated method stub
	}
	
	public static  CharArray reverse(CharArray arr) {
		char[] a = new char[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getChar(arr.size()-i-1);
		return new DefaultCharArray(a);
	}



	//Interface methods
	
	@Override
	public default boolean addChar(char e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllChars(CharCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default CharArray subList(int fromIndex, int toIndex) {
		return CharArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default CharArray subArray(int offset, int length) {
		return CharArray.wrap(this, offset, length);
	}
	
	
	public int binarySearch(char key);
	
	public void fill(char val);
	
	public char[] getInternalArray();
	
	public int getInternalOffset();
}