package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.AbstractBooleanList;
import com.github.powerlibraries.primitive.collections.BooleanCollection;
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
	public void addBoolean(int index, boolean element) {
		throw new UnsupportedOperationException();
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
	public boolean containsAll(Collection<?> c) {
		for(Object o:c) {
			if(!this.contains(o))
				return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Boolean> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Boolean> c) throws UnsupportedOperationException {
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
	public boolean containsBoolean(boolean o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean remove(int index) {
		rangeCheck(index);
		boolean old = elementData[offset+index];
		elementData[offset+index] = false;
		return old;
	}

	@Override
	public ListIterator<Boolean> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] toBooleanArray() {
		boolean[] result = new boolean[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}

	@Override
	public boolean removeBoolean(boolean o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAllBooleans(BooleanCollection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllBooleans(BooleanCollection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAllBooleans(BooleanCollection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<BooleanPointer> primitiveIterable() {
		// TODO Auto-generated method stub
		return null;
	}
}