package com.neps.groovydatamapper.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ScriptUtils {
	private ScriptUtils() {}
	
	public static Map<String, Object> toMap(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(object, new TypeReference<LinkedHashMap<String, Object>>() {
		});
	}
	
	public static Object toClass(Map<String, Object> map, Class<?> toClazz) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(map, toClazz);
	}
	
	public static List<KvTuple> readKvTuples(Object object) {
		AtomicInteger sequence = new AtomicInteger();
		return flatKvTuple(object, sequence).collect(Collectors.toCollection(LinkedList::new));
	}
	
	public static List<String> readKeys(List<KvTuple> tuples) {
		return tuples.stream().sequential()
				.map(kv -> (kv.getDisplay() != null && !kv.getDisplay().isEmpty()) ? kv.getDisplay() : kv.getKey())
				.collect(Collectors.toList());
	}
	
	public static List<Object> readValues(List<KvTuple> tuples) {
		return tuples.stream().sequential().map(KvTuple::getValue).collect(Collectors.toList());
	}
	
	public static String toCSVLine(List<?> list) {
		if (list == null) throw new RuntimeException("Must be valid argument list to convert csv line");
		
		return list.stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
	}
	
	private static Stream<KvTuple> flatKvTuple(Object o, AtomicInteger sequence) {
		Map<String, AlternativeColumn> annotationMap = findAlternativeColumnAnnotations(o);
		return ScriptUtils.toMap(o).entrySet().stream().sequential().flatMap(e -> {
			return Stream.of(new KvTuple(
					sequence.getAndIncrement(), 
					e.getKey(), 
					e.getValue(), 
					annotationMap.get(e.getKey()) != null ? annotationMap.get(e.getKey()).name() : null));
		});
	}
	
	private static Map<String, AlternativeColumn> findAlternativeColumnAnnotations(Object o) {
		return Arrays.stream(o.getClass().getDeclaredFields())
				.filter(f -> f.isAnnotationPresent(AlternativeColumn.class))
				.collect(Collectors.toMap(Field::getName, f -> f.getAnnotation(AlternativeColumn.class)));
	}
}
