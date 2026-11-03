package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.xddf.usermodel.chart.Grouping;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface;

import lombok.Synchronized;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Eager;
import net.bytebuddy.description.type.TypeDescription.Generic;
import utilities.Dog.InvalidAgeException;

// 1. OOP Concepts

	// Inheritance & Polymorphism
	//java
	// Base class
	public abstract class Animal {
	    protected String name;
	    
	    public Animal(String name) {
	        this.name = name;
	    }
	    
	    // Abstract method (must be implemented by subclasses)
	    public abstract void makeSound();
	    
	    // Concrete method
	    public void sleep() {
	        System.out.println(name + " is sleeping");
	    }
	}

	// Concrete implementation
	public class Dog extends Animal {
	    public Dog(String name) {
	        super(name);
	    }
	    
	    @Override
	    public void makeSound() {
	        System.out.println(name + " says: Woof!");
	    }
	}

	// Usage
	Animal animal = new Dog("Buddy");
	animal.makeSound(); // Output: Buddy says: Woof!
	animal.sleep();     // Output: Buddy is sleeping
	```

	### Encapsulation with Getter/Setter
	```java
	public class Person {
	    private String name;
	    private int age;
	    
	    public String getName() {
	        return name;
	    }
	    
	    public void setName(String name) {
	        if (name != null && !name.isEmpty()) {
	            this.name = name;
	        }
	    }
	    
	    public int getAge() {
	        return age;
	    }
	    
	    public void setAge(int age) {
	        if (age > 0 && age < 150) {
	            this.age = age;
	        }
	    }
	}
	```

	### Interface Implementation
	```java
	public interface PaymentProcessor {
	    void processPayment(double amount);
	    boolean refund(double amount);
	}

	public class CreditCardProcessor implements PaymentProcessor {
	    @Override
	    public void processPayment(double amount) {
	        System.out.println("Processing credit card payment: $" + amount);
	    }
	    
	    @Override
	    public boolean refund(double amount) {
	        System.out.println("Refunding $" + amount);
	        return true;
	    }
	}
	```

	---

	## 2. Multithreading

	### Thread Creation - Extends Thread
	```java
	public class MyThread extends Thread {
	    private String threadName;
	    
	    public MyThread(String name) {
	        this.threadName = name;
	    }
	    
	    @Override
	    public void run() {
	        for (int i = 0; i < 5; i++) {
	            System.out.println(threadName + " - Count: " + i);
	            try {
	                Thread.sleep(1000); // Sleep for 1 second
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	// Usage
	MyThread thread1 = new MyThread("Thread-1");
	thread1.start(); // Don't call run() directly
	```

	### Thread Creation - Implements Runnable (Preferred)
	```java
	public class RunnableTask implements Runnable {
	    private String taskName;
	    
	    public RunnableTask(String name) {
	        this.taskName = name;
	    }
	    
	    @Override
	    public void run() {
	        System.out.println(taskName + " is running");
	    }
	}

	// Usage
	Thread thread = new Thread(new RunnableTask("Task-1"));
	thread.start();
	```

	### Synchronized Method (Thread-Safe Counter)
	```java
	public class Counter {
	    private int count = 0;
	    
	    // Synchronized to prevent race conditions
	    public synchronized void increment() {
	        count++;
	    }
	    
	    public synchronized int getCount() {
	        return count;
	    }
	}
	```

	### Synchronized Block
	```java
	public class SharedResource {
	    private int value = 0;
	    
	    public void setValue(int val) {
	        // Only this block is synchronized
	        synchronized (this) {
	            this.value = val;
	        }
	    }
	}
	```

	### Producer-Consumer Pattern
	```java
	public class Buffer {
	    private Queue<Integer> queue = new LinkedList<>();
	    private final int MAX_SIZE = 10;
	    
	    public synchronized void produce(int value) throws InterruptedException {
	        while (queue.size() == MAX_SIZE) {
	            wait(); // Wait if buffer is full
	        }
	        queue.add(value);
	        System.out.println("Produced: " + value);
	        notifyAll(); // Notify consumers
	    }
	    
	    public synchronized int consume() throws InterruptedException {
	        while (queue.isEmpty()) {
	            wait(); // Wait if buffer is empty
	        }
	        int value = queue.poll();
	        System.out.println("Consumed: " + value);
	        notifyAll(); // Notify producers
	        return value;
	    }
	}
	```

	---

	## 3. Collections

	### ArrayList vs LinkedList
	```java
	// ArrayList - Best for random access
	List<String> arrayList = new ArrayList<>();
	arrayList.add("Apple");
	arrayList.add("Banana");
	System.out.println(arrayList.get(0)); // O(1) - Fast

	// LinkedList - Best for frequent insertions/deletions
	List<String> linkedList = new LinkedList<>();
	linkedList.add("Apple");
	linkedList.addFirst("Mango"); // O(1)
	linkedList.remove(0); // O(1)
	```

	### HashMap vs TreeMap vs LinkedHashMap
	```java
	// HashMap - No order
	Map<String, Integer> hashMap = new HashMap<>();
	hashMap.put("apple", 5);
	hashMap.put("banana", 3);

	// TreeMap - Sorted by key
	Map<String, Integer> treeMap = new TreeMap<>();
	treeMap.put("zebra", 1);
	treeMap.put("apple", 5); // Will be ordered alphabetically

	// LinkedHashMap - Insertion order
	Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
	linkedHashMap.put("apple", 5);
	linkedHashMap.put("banana", 3); // Order maintained as insertion
	```

	### HashSet vs TreeSet
	```java
	// HashSet - No guaranteed order, O(1) operations
	Set<String> hashSet = new HashSet<>();
	hashSet.add("Java");
	hashSet.add("Python");

	// TreeSet - Sorted, O(log n) operations
	Set<String> treeSet = new TreeSet<>();
	treeSet.add("Java");
	treeSet.add("Python"); // Will be sorted
	```

	### Iterate Collections Safely
	```java
	List<String> list = new ArrayList<>();
	list.add("A");
	list.add("B");

	// Method 1: Enhanced for loop
	for (String item : list) {
	    System.out.println(item);
	}

	// Method 2: Iterator (Safe for removal)
	Iterator<String> iterator = list.iterator();
	while (iterator.hasNext()) {
	    String item = iterator.next();
	    if (item.equals("A")) {
	        iterator.remove(); // Safe removal
	    }
	}

	// Method 3: Stream API
	list.forEach(System.out::println);
	```

	---

	## 4. Exception Handling

	### Try-Catch-Finally
	```java
	public void readFile(String filename) {
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(filename));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }
	    } catch (FileNotFoundException e) {
	        System.err.println("File not found: " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error reading file: " + e.getMessage());
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	```

	### Try-With-Resources (Recommended)
	```java
	public void readFileAutoClose(String filename) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }
	    } catch (IOException e) {
	        System.err.println("Error: " + e.getMessage());
	    }
	    // Resource automatically closed
	}
	```

	### Custom Exception
	```java
	public class InvalidAgeException extends Exception {
	    public InvalidAgeException(String message) {
	        super(message);
	    }
	}

	public class Person {
	    private int age;
	    
	    public void setAge(int age) throws InvalidAgeException {
	        if (age < 0 || age > 150) {
	            throw new InvalidAgeException("Age must be between 0 and 150");
	        }
	        this.age = age;
	    }
	}
	```

	---

	## 5. Generics

	### Generic Class
	```java
	public class Container<T> {
	    private T value;
	    
	    public void set(T value) {
	        this.value = value;
	    }
	    
	    public T get() {
	        return value;
	    }
	}

	// Usage
	Container<String> stringContainer = new Container<>();
	stringContainer.set("Hello");
	String result = stringContainer.get(); // No casting needed
	```

	### Generic Method
	```java
	public class Utility {
	    public static <T> void printArray(T[] array) {
	        for (T element : array) {
	            System.out.println(element);
	        }
	    }
	}

	// Usage
	Utility.printArray(new Integer[]{1, 2, 3});
	Utility.printArray(new String[]{"a", "b", "c"});
	```

	### Bounded Type Parameters
	```java
	public class MaxFinder {
	    public static <T extends Comparable<T>> T findMax(T[] array) {
	        T max = array[0];
	        for (T element : array) {
	            if (element.compareTo(max) > 0) {
	                max = element;
	            }
	        }
	        return max;
	    }
	}

	// Usage
	Integer max = MaxFinder.findMax(new Integer[]{3, 5, 1, 9, 2});
	System.out.println(max); // Output: 9
	```

	---

	## 6. Streams & Functional Programming

	### Stream Operations
	```java
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

	// Filter and map
	List<Integer> evenSquares = numbers.stream()
	    .filter(n -> n % 2 == 0)
	    .map(n -> n * n)
	    .collect(Collectors.toList());
	// Output: [4, 16, 36, 64, 100]

	// Reduce
	int sum = numbers.stream()
	    .reduce(0, Integer::sum);
	// Output: 55

	// Count
	long evenCount = numbers.stream()
	    .filter(n -> n % 2 == 0)
	    .count();
	// Output: 5
	```

	### Sorting with Streams
	```java
	List<String> fruits = Arrays.asList("Banana", "Apple", "Mango", "Orange");

	// Ascending order
	List<String> sorted = fruits.stream()
	    .sorted()
	    .collect(Collectors.toList());

	// Descending order
	List<String> sortedDesc = fruits.stream()
	    .sorted(Collections.reverseOrder())
	    .collect(Collectors.toList());
	```

	### Grouping and Partitioning
	```java
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

	// Partition by even/odd
	Map<Boolean, List<Integer>> partition = numbers.stream()
	    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
	// {false: [1, 3, 5], true: [2, 4, 6]}

	// Group by modulo 3
	Map<Integer, List<Integer>> grouped = numbers.stream()
	    .collect(Collectors.groupingBy(n -> n % 3));
	```

	---

	## 7. Common Algorithms

	### Fibonacci (Recursive with Memoization)
	```java
	public class Fibonacci {
	    private Map<Integer, Long> memo = new HashMap<>();
	    
	    public long fibonacci(int n) {
	        if (n <= 1) return n;
	        if (memo.containsKey(n)) return memo.get(n);
	        
	        long result = fibonacci(n - 1) + fibonacci(n - 2);
	        memo.put(n, result);
	        return result;
	    }
	}

	// Usage
	Fibonacci fib = new Fibonacci();
	System.out.println(fib.fibonacci(10)); // Output: 55
	```

	### Binary Search
	```java
	public class BinarySearch {
	    public static int binarySearch(int[] arr, int target) {
	        int left = 0, right = arr.length - 1;
	        
	        while (left <= right) {
	            int mid = left + (right - left) / 2;
	            
	            if (arr[mid] == target) {
	                return mid;
	            } else if (arr[mid] < target) {
	                left = mid + 1;
	            } else {
	                right = mid - 1;
	            }
	        }
	        return -1; // Not found
	    }
	}

	// Usage
	int[] arr = {1, 3, 5, 7, 9, 11};
	System.out.println(binarySearch(arr, 7)); // Output: 3
	```

	### Bubble Sort
	```java
	public class BubbleSort {
	    public static void bubbleSort(int[] arr) {
	        int n = arr.length;
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (arr[j] > arr[j + 1]) {
	                    // Swap
	                    int temp = arr[j];
	                    arr[j] = arr[j + 1];
	                    arr[j + 1] = temp;
	                }
	            }
	        }
	    }
	}
	```

	### Merge Sort
	```java
	public class MergeSort {
	    public static void mergeSort(int[] arr) {
	        if (arr.length < 2) return;
	        
	        int mid = arr.length / 2;
	        int[] left = Arrays.copyOfRange(arr, 0, mid);
	        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
	        
	        mergeSort(left);
	        mergeSort(right);
	        merge(arr, left, right);
	    }
	    
	    private static void merge(int[] arr, int[] left, int[] right) {
	        int i = 0, j = 0, k = 0;
	        
	        while (i < left.length && j < right.length) {
	            if (left[i] <= right[j]) {
	                arr[k++] = left[i++];
	            } else {
	                arr[k++] = right[j++];
	            }
	        }
	        
	        while (i < left.length) {
	            arr[k++] = left[i++];
	        }
	        
	        while (j < right.length) {
	            arr[k++] = right[j++];
	        }
	    }
	}
	```

	### Reverse a String
	```java
	// Method 1: Using StringBuilder
	String reversed = new StringBuilder("Hello").reverse().toString();

	// Method 2: Using loop
	public static String reverseString(String str) {
	    String reversed = "";
	    for (int i = str.length() - 1; i >= 0; i--) {
	        reversed += str.charAt(i);
	    }
	    return reversed;
	}

	// Method 3: Recursive
	public static String reverseRecursive(String str) {
	    if (str.isEmpty()) return str;
	    return reverseRecursive(str.substring(1)) + str.charAt(0);
	}
	```

	### Check Palindrome
	```java
	public class Palindrome {
	    public static boolean isPalindrome(String str) {
	        String cleaned = str.toLowerCase().replaceAll("[^a-z0-9]", "");
	        String reversed = new StringBuilder(cleaned).reverse().toString();
	        return cleaned.equals(reversed);
	    }
	}

	// Usage
	System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
	```

	---

	## 8. Singleton Pattern

	### Thread-Safe Singleton
	```java
	public class Singleton {
	    private static Singleton instance;
	    
	    private Singleton() {
	        // Private constructor
	    }
	    
	    public static synchronized Singleton getInstance() {
	        if (instance == null) {
	            instance = new Singleton();
	        }
	        return instance;
	    }
	}
	```

	### Eager Initialization (Preferred)
	```java
	public class SingletonEager {
	    private static final SingletonEager instance = new SingletonEager();
	    
	    private SingletonEager() {
	    }
	    
	    public static SingletonEager getInstance() {
	        return instance;
	    }
	}
	```

	### Lazy Initialization with Double-Checked Locking
	```java
	public class SingletonLazy {
	    private static volatile SingletonLazy instance;
	    
	    private SingletonLazy() {
	    }
	    
	    public static SingletonLazy getInstance() {
	        if (instance == null) {
	            synchronized (SingletonLazy.class) {
	                if (instance == null) {
	                    instance = new SingletonLazy();
	                }
	            }
	        }
	        return instance;
	    }
	}
	```

	---

	## 9. Immutable Class

	```java
	public final class ImmutablePerson {
	    private final String name;
	    private final int age;
	    private final List<String> hobbies;
	    
	    public ImmutablePerson(String name, int age, List<String> hobbies) {
	        this.name = name;
	        this.age = age;
	        // Defensive copy to ensure immutability
	        this.hobbies = Collections.unmodifiableList(new ArrayList<>(hobbies));
	    }
	    
	    public String getName() {
	        return name;
	    }
	    
	    public int getAge() {
	        return age;
	    }
	    
	    public List<String> getHobbies() {
	        return new ArrayList<>(hobbies); // Return defensive copy
	    }
	}
	```

	---

	## 10. String Operations

	### String Comparison
	```java
	String s1 = "Hello";
	String s2 = "Hello";
	String s3 = new String("Hello");

	System.out.println(s1 == s2);           // true (same reference)
	System.out.println(s1 == s3);           // false (different objects)
	System.out.println(s1.equals(s3));      // true (same content)
	System.out.println(s1.equalsIgnoreCase("hello")); // true
	```

	### String Methods
	```java
	String str = "Hello World";

	System.out.println(str.length());           // 11
	System.out.println(str.charAt(0));          // H
	System.out.println(str.substring(0, 5));    // Hello
	System.out.println(str.toUpperCase());      // HELLO WORLD
	System.out.println(str.toLowerCase());      // hello world
	System.out.println(str.contains("World"));  // true
	System.out.println(str.startsWith("Hello")); // true
	System.out.println(str.replace("World", "Java")); // Hello Java
	System.out.println(str.split(" "));         // [Hello, World]
	```

	### String vs StringBuilder vs StringBuffer
	```java
	// String - Immutable, thread-safe
	String s = "Hello" + " " + "World"; // Creates multiple objects

	// StringBuilder - Mutable, NOT thread-safe (faster)
	StringBuilder sb = new StringBuilder();
	sb.append("Hello").append(" ").append("World");
	System.out.println(sb.toString());

	// StringBuffer - Mutable, thread-safe (slower)
	StringBuffer sbf = new StringBuffer();
	sbf.append("Hello").append(" ").append("World");
	System.out.println(sbf.toString());
	```

	---

	## Key Interview Tips

	1. **Understand Trade-offs**: ArrayList vs LinkedList, HashMap vs TreeMap
	2. **Thread Safety**: Know synchronized, volatile, and concurrent collections
	3. **Complexity Analysis**: Time and space complexity of operations
	4. **Design Patterns**: Singleton, Factory, Observer, Strategy
	5. **Collections**: Hierarchy, when to use what, performance characteristics
	6. **Exception Handling**: Checked vs Unchecked exceptions
	7. **OOP Principles**: Encapsulation, Inheritance, Polymorphism, Abstraction
	8. **Generics**: Bounded types, wildcards, type erasure
	9. **Streams**: Functional programming, lazy evaluation
	10. **Immutability**: Benefits and implementation strategies


}
