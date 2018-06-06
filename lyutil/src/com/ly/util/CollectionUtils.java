package com.ly.util;

import java.util.Collection;

public class CollectionUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Collection<E>, E> T fill(Collection<E> t, E... objs) {
		for (Object o : objs) {
			t.add((E) o);
		}
		return (T) t;
	}

}
