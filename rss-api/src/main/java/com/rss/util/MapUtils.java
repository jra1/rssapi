package com.rss.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class MapUtils {

	public static <T, U> List<U> map(final Mapper mapper, final List<T> sourceList, final Class<U> destType) {
		final List<U> dest = new ArrayList<>();
		for (T element : sourceList) {
			dest.add(mapper.map(element, destType));
		}
		return dest;
	}
}
