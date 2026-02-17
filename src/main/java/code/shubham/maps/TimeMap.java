//package code.shubham.maps;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.TreeMap;
//
//public class TimeMap {
//
//    private final Map<String, Map<String, ArrayList<TimedValue>>> data = new HashMap<>();
//
//    public void set(String key, String field, String value) {
//        data.computeIfAbsent(key, k -> new TreeMap<>())
//                .computeIfAbsent(field, f -> new ArrayList<>())
//                .add(new TimedValue(value));
//    }
//
//    public Optional<String> get(String key, String field) {
//        return Optional.ofNullable(data.get(key))
//                .map(fields -> fields.get(field))
//                .orElse(new ArrayList<>())
//                .stream()
//                .filter(tv -> tv.isDefault)
//                .findFirst()
//                .map(tv -> tv.value);
//    }
//
//    public boolean delete(String key, String field) {
//        return Optional.ofNullable(data.get(key))
//                .map(fields -> fields.get(field))
//                .isPresent();
//    }
//
//    public boolean scan(String key) {
//        return Optional.ofNullable(data.get(key))
//                .map(fields -> fields
//                        .entrySet()
//                        .stream()
//                        .map()
//                    )
//                .isPresent();
//    }
//
//}
//
//class TimedValue {
//    String value;
//    boolean isDefault;
//
//    int start = Integer.MIN_VALUE , end =Integer.MAX_VALUE;
//
//    TimedValue(String value) {
//        this.value = value;
//        this.isDefault = true;
//    }
//
//    TimedValue(String value, int start) {
//        this.value = value;
//        this.start = start;
//    }
//
//    TimedValue(String value, int start, int ttl) {
//        this.value = value;
//        this.start = start;
//        this.end = this.start + ttl;
//    }
//}
