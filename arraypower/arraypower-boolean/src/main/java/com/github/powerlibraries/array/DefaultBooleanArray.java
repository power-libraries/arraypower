package com.github.powerlibraries.array;

import java.nio.BooleanBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.github.powerlibraries.primitive.collections.AbstractBooleanList;
import com.github.powerlibraries.primitive.collections.BooleanListIterator;
import com.github.powerlibraries.primitive.common.DefaultBooleanPointer;
import com.github.powerlibraries.primitive.common.BooleanPointer;

public class DefaultBooleanArray extends AbstractBooleanList implements BooleanArray {

	private final boolean[] elementData;
	private final int offset;
	private final int length;

	DefaultBooleanArray(boolean[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultBooleanArray(boolean[] elementData, int offset, int length) {
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
	public boolean getBoolean(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public boolean setBoolean(int index, boolean element) {
		rangeCheck(index);
		boolean old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public int indexOfBoolean(boolean o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfBoolean(boolean o) {
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
				elementData[i] = false;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(false);
	}
	
	@Override
	public void fill(boolean value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = false;
		}
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[length];
		for(int i=offset;i<offset+length;i++)
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
	public boolean containsBoolean(boolean o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean removeAt(int index) {
		rangeCheck(index);
		boolean old = elementData[offset+index];
		elementData[offset+index] = false;
		return old;
	}

	@Override
	public boolean[] toBooleanArray() {
		boolean[] result = new boolean[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public boolean[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeBoolean(boolean o) {
		for(int i=offset;i<offset+length;i++) {
			if(elementData[i] == o) {
				elementData[i] = false;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public BooleanListIterator listIterator(int index) {
		return new BooleanArrayIterator(this, index);
	}

	@Override
	public Iterable<BooleanPointer> primitiveIterable(int index) {
		return new BooleanPrimitiveIterable(index);
	}
	
	@Override
	public void reverse() {
		for(int i = 0; i < length / 2; i++) {
			boolean temp = elementData[offset+i];
			elementData[i] = elementData[offset + length - i - 1];
			elementData[offset + length - i - 1] = temp;
		}
	}
	
	@Override
	public BooleanBuffer asBuffer() {
		return BooleanBuffer.wrap(elementData, offset, length);
	}
	
	private class BooleanPrimitiveIterable implements Iterable<BooleanPointer> {

		private int initialPosition;

		public BooleanPrimitiveIterable(int initialPosition) {
			this.initialPosition = initialPosition;
		}

		@Override
		public Iterator<BooleanPointer> iterator() {
			return new BooleanPointerIterator(initialPosition);
		}
	}
	
	private class BooleanPointerIterator implements Iterator<BooleanPointer> {

		private int position;
		private DefaultBooleanPointer pointer;
		
		public BooleanPointerIterator(int position) {
			this.position = position;
			this.pointer = new DefaultBooleanPointer();
		}

		@Override
		public boolean hasNext() {
			return position+1<length;
		}

		@Override
		public BooleanPointer next() {
			position++;
			if(position>=length)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(position));
			pointer.set(getBoolean(position));
			return pointer;
		}
		
	}
}