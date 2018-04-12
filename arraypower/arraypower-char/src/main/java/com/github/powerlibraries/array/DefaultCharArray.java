package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.AbstractCharList;
import com.github.powerlibraries.primitive.collections.CharCollection;
import com.github.powerlibraries.primitive.common.CharPointer;

public class DefaultCharArray extends AbstractCharList implements CharArray {

	private final char[] elementData;
	private final int offset;
	private final int length;

	DefaultCharArray(char[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultCharArray(char[] elementData, int offset, int length) {
		this.elementData = elementData;
		this.offset = offset;
		this.length = length;
	}

	/**
     * Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.
     */
    private void rangeCheck(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+elementData.length;
    }

	@Override
	public char getChar(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public char setChar(int index, char element) {
		rangeCheck(index);
		char old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public void addChar(int index, char element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOfChar(char o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfChar(char o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean addAll(Collection<? extends Character> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Character> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for(Object o:c)
			changed |= this.remove(o);
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		for(int i=offset; i<offset+length; i++) {
			if(!c.contains(elementData[i])) {
				elementData[i] = '\u0000';
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill('\u0000');
	}
	
	@Override
	public void fill(char value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = '\u0000';
		}
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if(a.length != length)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), length);
		for(int i=0;i<length;i++)
			a[i] = (T)(Object)elementData[i+offset];
		return a;
	}
	
	@Override
	public void sort() {
		Arrays.sort(elementData, offset, offset+length);
	}
	
	@Override
	public void parallelSort() {
		Arrays.parallelSort(elementData, offset, offset+length);
	}
	
	@Override
	public int binarySearch(char key) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsChar(char o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Character remove(int index) {
		rangeCheck(index);
		char old = elementData[offset+index];
		elementData[offset+index] = '\u0000';
		return old;
	}

	@Override
	public char[] toCharArray() {
		char[] result = new char[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public char[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeChar(char o) {
		for(int i=0;i<length;i++) {
			if(elementData[i] == o) {
				elementData[i] = '\u0000';
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ListIterator<Character> listIterator(int index) {
		return new ArrayIterator<Character>(this, index);
	}

	@Override
	public Iterable<CharPointer> primitiveIterable(int index) {
		return new CharPrimitiveIterable(this, index);
	}
	
	public CharArray reverse() {
		char[] a = new char[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getChar(arr.size()-i-1);
		return new DefaultCharArray(a);
	}
}