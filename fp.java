package hw3;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * @author Kevin Sass
 * 
 *
 */
public class fp {
	
	/**
	 * Given a List of objects return a List of hashCodes using the BiFunction f.
	 * @param l list containing objects of type U
	 * @param f BiFunction to be passed
	 * @return a list of BiFunction results
	 */
	public static <U, V> List<V> map(Iterable<U> l, Function<U, V> f) {
		List<V> result = new ArrayList<V>(); // Stores the values after hashing with f

		// Loop to hash each function with f and store into result
		for (Iterator<U> iter = l.iterator(); iter.hasNext();) {
			result.add(f.apply(iter.next()));
		}

		return result;
	} // map()

	/**
	 * Given a list of objects perform the given BiFunction f on it
	 * @param e Value to 
	 * @param l List to perform operation on
	 * @param f BiFunction to use in operation
	 * @return a concatenated/summed result depending on BiFunction
	 */
	public static <U, V> V foldLeft(V e, Iterable<U> l, BiFunction<V, U, V> f) {
		if (l != null) {
			if (l.iterator().hasNext() && l != null) { // Determines if there is a next element
				List<U> tail = new ArrayList<>(); // Will store the remaining elements in the iterable
				l.forEach(tail::add); // Populates list with remaining

				// Does the foldLeft operation
				return foldLeft(f.apply(e, l.iterator().next()), tail.subList(1, tail.size()), f);
			} else
				return e;
		} else
			return e;
	}

	/**
	 * Given a list of objects, perform the given BiFunction f on it
	 * @param e Value to 
	 * @param l List to perform operation on
	 * @param f BiFunction to use in operation
	 * @return a concatenated/summed result depending on BiFunction
	 */
	public static <U, V> V foldRight(V e, List<U> l, BiFunction<U, V, V> f) {
		if (l != null) {
			if (l.iterator().hasNext()) {
				U head = l.iterator().next();
				return f.apply(head, foldRight(e, l.subList(1, l.size()), f));
			} else
				return e;
		} else
			return e;
	}

    /**
     * Given a list of objects, filter() will filter out those that pass the Predicate p tests
     * @param l, list to perform the Predicate tests on
     * @param p, Predicate to test with
     * @return a list containing the objects that were not filtered out
     */
	public static <U> List<U> filter(List<U> l, Predicate<U> p) {

		// Loop through each object
		for (Iterator<U> iter = l.iterator(); iter.hasNext();) {
			U value = iter.next(); // Gets object
			if (p.test(value)) { // Test value and removes if necessary
				iter.remove();
			}
		}

		return l; // Filtered list
	}
	
	/**
	* Given a List of objects return the minimum of a List of Integers
	*/
	static <U> U minVal(Iterable<U> l, Comparator<U> c)
	{
		// write using fold.  No other loops permitted. 
	
		Iterator<U> i = l.iterator();
		U minVal = i.next();
		U res = foldLeft(minVal, l, new BiFunction<U,U,U>()
		{
			@Override
			public U apply(U t, U u) {
				// TODO Auto-generated method stub
				U minVal = t;
				if(c.compare(t, u) > 0)
				{
					minVal = u;
				}
			
		
				return minVal;

			}
		
		});
		return  res;	
	}

	/**
	* Given a List of objects return the position of the minimum in  a List of  Integers
	*/
	static <U extends Comparable<U>> int minPos(Iterable<U> l)
	{
		// write using fold.  No other loops permitted. 
		BiFunction<Integer,U,Integer> minPos = new BiFunction<Integer,U,Integer>()
		{
			@Override
			public Integer apply(Integer x, U y) 
			{
				// TODO Auto-generated method stub
				ArrayList<U> list = new ArrayList<>();
				l.forEach(list::add);
				if(list.get(x).compareTo(y) < 0)
				{
					return x;
				}
				else if(list.get(x).compareTo(y) > 0)
				{
					return list.indexOf(y);
				}
				else
					return x;
			}
		};
		int res = foldLeft(0,l,minPos);
		return res;
	}
	
	
	
	public static void main(String[] args) {
		// (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
		// names = ['Mary', 'Isla', 'Sam']
		// for i in range(len(names)):
		       // names[i] = hash(names[i])
		
		// (2) Use foldleft to calculate the sum of a list of integers.
		// i.e write a method: int sum(List<Integer> l)
		
		// (3) Use foldRight to concatenate a list of strings i.e write a method
		// String s (List<String> l)
		
		// (4) consider an array of Persons. Use filter to 
		// print the names of the Persons whose salary is
		// greater than 100000
		
		// (5) Use minVal to calculate the minimum of a List of 
		//       Integers
		
        // (6) Use minVal to calculate the maximum of a List of 
		//       Integers
		
		// (7)  Use minPos to calculate the position of the
		// minimum in  a List of  Integers

		// (8)  Use minPos to calculate the position of the
		// minimum in  a List of  String

	}

}

class Person {
	final int salary;
	final String name;

	Person(int salary, String name) {
		this.salary = salary;
		this.name = name;
	}

	int getSalary() {
		return salary;
	}

	String name() {
		return name;
	}

}