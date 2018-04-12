package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Objects;

import com.github.powerlibraries.primitive.collections.AbstractShortList;
import com.github.powerlibraries.primitive.collections.ShortCollection;
import com.github.powerlibraries.primitive.common.ShortPointer;

public class DefaultShortArray extends AbstractShortList implements ShortArray {

	private final short[] elementData;
	private final int offset;
	private final int length;

	DefaultShortArray(short[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultShortArray(short[] elementData, int offset, int length) {
		this.elementData = elementData;
		this.offset = offset;
		this.length = length;
	}

	/**
	 * Checks if the given index is in range.  If not, throws an appropriate
	 * runtime exception.
	 */
	private void rangeCheck(int index) {
		if (index >= length || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
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
	public short getShort(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public short setShort(int index, short element) {
		rangeCheck(index);
		short old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public int indexOfShort(short o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfShort(short o) {
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
				elementData[i] = ((short)0);
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(((short)0));
	}
	
	@Override
	public void fill(short value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = ((short)0);
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
	public int binarySearch(short key) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsShort(short o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Short remove(int index) {
		rangeCheck(index);
		short old = elementData[offset+index];
		elementData[offset+index] = ((short)0);
		return old;
	}

	@Override
	public short[] toShortArray() {
		short[] result = new short[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public short[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeShort(short o) {
		for(int i=0;i<length;i++) {
			if(elementData[i] == o) {
				elementData[i] = ((short)0);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ListIterator<Short> listIterator(int index) {
		return new ArrayIterator<Short>(this, index);
	}

	@Override
	public Iterable<ShortPointer> primitiveIterable(int index) {
		return new ShortPrimitiveIterable(this, index);
	}
	
	public ShortArray reverse() {
		short[] a = new short[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getShort(arr.size()-i-1);
		return new DefaultShortArray(a);
	}
}