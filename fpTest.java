package hw3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class fpTest {
	
	@Test
	void testMap() {
		List<String> names = new ArrayList<String>();
		
		names.add("Terry");
		names.add("Torry");
		names.add("Tarry");
		names.add("Trart");
		
		List<Integer> hashCodes = new ArrayList<Integer>();
		for(int x = 0; x < names.size(); x++)
			hashCodes.add(names.get(x).hashCode());
			
		Function<String, Integer> f = (str) ->{
			return(str.hashCode());
		};
		
		List<Integer> result = new ArrayList<Integer>();
		result = fp.map(names, f);
		
		for(int x = 0; x < hashCodes.size(); x++)
			assertEquals(hashCodes.get(x), result.get(x));
	}
	
	@Test
    void testFoldLeft(){
        List<Integer> l = new ArrayList<Integer>();
        
        for(int x = 0; x < 5; x++)
        	l.add(x);
        
        BiFunction<String, Integer, String> f = (p, q) ->{
        	return (p + "[" + q + "]");
        };
        
        assertEquals("@[0][1][2][3][4]", fp.foldLeft("@", l, f));
        assertEquals("@", fp.foldLeft("@", null, f));
    }
	
	@Test
	void testFoldRight() {
		List<Integer> a = new ArrayList<Integer>();
		
		for (int x = 0; x < 5; x++) {
			a.add(x);
		}
		
		BiFunction<Integer, String, String> f = (p, q) -> {
			return (q + "[" + p + "]");
		};

        assertEquals("@", fp.foldRight("@", null, f));
        assertEquals("@[4][3][2][1][0]", fp.foldRight("@", a, f));
	}
	
	 @Test
	    void testFilter(){
	        List<Person> employees = new ArrayList<Person>();
	        
	        employees.add(new Person(1, "Under1"));
	        employees.add(new Person(2, "Under2"));
	        employees.add(new Person(100001, "Over1"));
	        employees.add(new Person(111111, "Over2"));

	        Predicate<Person> greaterThan100k = (Person person) -> person.getSalary() < 100000;
	        employees = fp.filter(employees, greaterThan100k);
	        
	        String result = "";
	        for(Person p : employees){
	            result += p.name();
	        }
	        
	        assertEquals("Over1Over2", result);
	    }
	
	 @Test
	 void testMinVal()
	 {
		 List<Integer> intarray = new ArrayList<Integer>();
		 intarray.add(1);
		 intarray.add(2);
		 intarray.add(-1);
		 intarray.add(19);
		 intarray.add(5);
		 intarray.add(11);
		 //getting the min of the list
		 Integer min = fp.minVal(intarray, new Comparator<Integer>()
				 {

					@Override
					public int compare(Integer x, Integer y) {
						// TODO Auto-generated method stub
						
						if(x<y)
							return -1;
						if(x > y)
							return 1;
						else 
							return 0;
				
					}
			 
				 });
		 assertTrue(min == -1);
		 //getting the max of the list
		 Integer max = fp.minVal(intarray, new Comparator<Integer>()
		 {

			@Override
			public int compare(Integer x, Integer y) {
				// TODO Auto-generated method stub
				
				if(x>y)
					return -1;
				if(x < y)
					return 1;
				else 
					return 0;
		
			}
	 
		 });
		 assertTrue(max == 19);
		
	 }
	 
	 @Test
	 void testMinPos()
	 {
		 List<Integer> intarray = new ArrayList<Integer>();
		 List<String> strarray = new ArrayList<String>();
		 int intMinPos;
		 int strMinPos;
		 
		 intarray.add(-3);
		 intarray.add(2);
		 intarray.add(-2);
		 intarray.add(19);
		 intarray.add(5);
		 intarray.add(11);
		 
		 strarray.add("Frank");
		 strarray.add("Tom");
		 strarray.add("Amy");
		 strarray.add("Becca");
		 
		 intMinPos = fp.minPos(intarray);
		 strMinPos = fp.minPos(strarray);
		 assertTrue(intMinPos == 0);
		 assertTrue(strMinPos == 2);
		 
		 intarray.add(-5);
		 strarray.add("Able");
		 intMinPos = fp.minPos(intarray);
		 strMinPos = fp.minPos(strarray);
		 assertTrue(intMinPos == 6);
		 assertTrue(strMinPos == 4);
		 
		 
		 
	 }
	
	

}