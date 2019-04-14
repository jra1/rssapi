package com.rss.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

public class MapUtils {

	private MapUtils() {
	    throw new IllegalStateException("This is a utility class");
	  }
	
	public static <T, U> List<U> map(final Mapper mapper, final List<T> sourceList, final Class<U> destType) {
		final List<U> dest = new ArrayList<>();
		for (T element : sourceList) {
			dest.add(mapper.map(element, destType));
		}
		return dest;
	}

	public static <T> List<T> castList(Class<T> clase, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clase.cast(o));
		return r;
	}
}
