package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class RadixSort
{
    /*
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have same length
     */
    public static void radixSortA( String [ ] arr, int stringLen )
    {
        final int BUCKETS = 256;
        
        ArrayList<ArrayList<String>> buckets = new ArrayList<ArrayList<String>>(BUCKETS);
        //ArrayList<String[]> buckets = new ArrayList<String[]>(BUCKETS);
        
        for( int i = 0; i < BUCKETS; i++ )
        	//this.get(index).add(element);
        	//ArrayList<String> element = new ArrayList<String>();
        	buckets.add(new ArrayList<String>());
        	//buckets.set(i, new ArrayList<String>());
            //buckets[i] = new ArrayList<String>();
        
        for( int pos = stringLen - 1; pos >= 0; pos-- )
        {
            for( String s : arr )
                //buckets[ s.charAt( pos ) ].add( s );
            	buckets.get(s.charAt(pos)).add(s);

            int idx = 0;
            for( ArrayList<String> thisBucket : buckets )
            {
                for( String s : thisBucket )
                    arr[ idx++ ] = s;
                
                thisBucket.clear( );
            }
        }
    }
       
    /*
     * Counting radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have same length
     */
    public static void countingRadixSort( String [ ] arr, int stringLen )
    {
        final int BUCKETS = 256;
        
        int N = arr.length;
        String [ ] buffer = new String[ N ];

        String [ ] in = arr;
        String [ ] out = buffer;
        
        for( int pos = stringLen - 1; pos >= 0; pos-- )
        {
            int[ ] count = new int [ BUCKETS + 1 ];
            
            for( int i = 0; i < N; i++ )
                count[ in[ i ].charAt( pos ) + 1 ]++;

            for( int b = 1; b <= BUCKETS; b++ )
                count[ b ] += count[ b - 1 ];

            for( int i = 0; i < N; i++ )
                out[ count[ in[ i ].charAt( pos ) ]++ ] = in[ i ];
            
              // swap in and out roles
            String [ ] tmp = in;
            in = out;
            out = tmp;
        }
        
           // if odd number of passes, in is buffer, out is arr; so copy back
        if( stringLen % 2 == 1 )
            for( int i = 0; i < arr.length; i++ )
                out[ i ] = in[ i ];
    }
    
    /*
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have length bounded by maxLen
     */
    /*
    public static void radixSort( String [ ] arr, int maxLen )
    {
        final int BUCKETS = 256;
        
        ArrayList<String> [ ] wordsByLength = new ArrayList<>[ maxLen + 1 ];
        ArrayList<String> [ ] buckets = new ArrayList<>[ BUCKETS ];
        
        for( int i = 0; i < wordsByLength.length; i++ )
            wordsByLength[ i ] = new ArrayList<>( );
        
        for( int i = 0; i < BUCKETS; i++ )
            buckets[ i ] = new ArrayList<>( );
        
        for( String s : arr )
            wordsByLength[ s.length( ) ].add( s );
       
        int idx = 0;
        for( ArrayList<String> wordList : wordsByLength )
            for( String s : wordList )
                arr[ idx++ ] = s;
        
        int startingIndex = arr.length;    
        for( int pos = maxLen - 1; pos >= 0; pos-- )
        {
            startingIndex -= wordsByLength[ pos + 1 ].size( );
            
            for( int i = startingIndex; i < arr.length; i++ )
                buckets[ arr[ i ].charAt( pos ) ].add( arr[ i ] );
            
            idx = startingIndex;
            for( ArrayList<String> thisBucket : buckets )
            {
                for( String s : thisBucket )
                    arr[ idx++ ] = s;
                
                thisBucket.clear( );
            }
        }
    }*/

    // Print the array
    private static void print( String[] a)
    {
        for( int i = 0; i < a.length; i++ )
        	System.out.print(a[i] + " ");
        System.out.println();
    }
    

    private static final int NUM_ITEMS = 100000;
    // Do some tests
    // Note: radix sort works only for fixed length strings (radixSortA)
    // For variable length strings, radixSort has to be modified
    
    public static void task3a( int LEN) {
    	List<String> lst = new ArrayList<>( );
        Random r = new Random( );
        
        for( int i = 0; i < NUM_ITEMS; i++ )
        {
            String str = "";
            int len = LEN; //3 + r.nextInt( 7 ); // between 3 and 9 characters

            for( int j = 0; j < len; j++ )
                str += (char) ( 'a' + r.nextInt( 26 ) );

            lst.add( str );
        }

        String [ ] mergeSort = new String[ lst.size( ) ];
        String [ ] quickSort = new String[ lst.size( ) ];
        String [ ] heapSort = new String[ lst.size( ) ];
        String [ ] dualPivot = new String[ lst.size( ) ];
        String [ ] radixSort = new String[ lst.size( ) ];

        lst.toArray( mergeSort );
        lst.toArray( quickSort );
        lst.toArray( heapSort );
        lst.toArray( dualPivot );
        lst.toArray( radixSort );
      
        long start, end;
        System.out.println("For Random String Length: " +LEN);
        
//        merge sort
        start = System.currentTimeMillis( );
        Sort.mergeSort(mergeSort);
        end = System.currentTimeMillis( );
        System.out.println( "Total time for Mergesort in milis: " + ( end - start ) );
        
//        quick sort
        start = System.currentTimeMillis( );
        Sort.quicksort(quickSort);
        end = System.currentTimeMillis( );
        System.out.println( "Total time for Quicksort in milis: " + ( end - start ) );
        

//       heap sort
        start = System.currentTimeMillis( );
        Sort.heapsort(heapSort);
        end = System.currentTimeMillis( );
        System.out.println( "Total time for Heapsort in milis: " + ( end - start ) );

//        dual pivot
        start = System.currentTimeMillis( );
        Arrays.sort( dualPivot );
        end = System.currentTimeMillis( );
        System.out.println( "Total time for Dual Pivot in milis: " + ( end - start ) );
        
//        radix sort
        start = System.currentTimeMillis( );
        radixSortA( radixSort, LEN );
        end = System.currentTimeMillis( );
        System.out.println( "Total time for Radix sort in milis: " + ( end - start ) );
        System.out.println('\n');

    }
    
    public static void task3b(int LEN) {
    	 int repeat = 10;
    	 
    	 long mergeSortTime=0;
    	 long quickSortTime=0;
    	 long heapSortTime=0;
    	 long dualPivotTime=0;
    	 long radixSortTime=0;
    	
    	for (int rep = 0; rep < repeat; rep++) {
    		List<String> lst = new ArrayList<>( );
            Random r = new Random( );
            
            System.out.println( "Rep Number: " + (rep+1) );
            
            for( int i = 0; i < NUM_ITEMS; i++ )
            {
                String str = "";
                int len = LEN; //3 + r.nextInt( 7 ); // between 3 and 9 characters

                for( int j = 0; j < len; j++ )
                    str += (char) ( 'a' + r.nextInt( 26 ) );

                lst.add( str );
            }

            String [ ] mergeSort = new String[ lst.size( ) ];
            String [ ] quickSort = new String[ lst.size( ) ];
            String [ ] heapSort = new String[ lst.size( ) ];
            String [ ] dualPivot = new String[ lst.size( ) ];
            String [ ] radixSort = new String[ lst.size( ) ];

            lst.toArray( mergeSort );
            lst.toArray( quickSort );
            lst.toArray( heapSort );
            lst.toArray( dualPivot );
            lst.toArray( radixSort );
          
            long start, end;
            
//            merge sort
            start = System.currentTimeMillis( );
            Sort.mergeSort(mergeSort);
            end = System.currentTimeMillis( );
            mergeSortTime += ( end - start );
            System.out.println( "Total time for Mergesort in milis: " + ( end - start ) );
            
//            quick sort
            start = System.currentTimeMillis( );
            Sort.quicksort(quickSort);
            end = System.currentTimeMillis( );
            quickSortTime += ( end - start );
            System.out.println( "Total time for Quicksort in milis: " + ( end - start ) );
            

//           heap sort
            start = System.currentTimeMillis( );
            Sort.heapsort(heapSort);
            end = System.currentTimeMillis( );
            heapSortTime += ( end - start );
            System.out.println( "Total time for Heapsort in milis: " + ( end - start ) );

//            dual pivot
            start = System.currentTimeMillis( );
            Arrays.sort( dualPivot );
            end = System.currentTimeMillis( );
            dualPivotTime += ( end - start );
            System.out.println( "Total time for Dual Pivot in milis: " + ( end - start ) );
            
//            radix sort
            start = System.currentTimeMillis( );
            radixSortA( radixSort, LEN );
            end = System.currentTimeMillis( );
            radixSortTime += ( end - start );
            System.out.println( "Total time for Radix sort in milis: " + ( end - start ) );
            System.out.println('\n');
    	}
    	
        System.out.println("For Random String Length: " +LEN);
        System.out.println("Avg time for Merge Sort in Milis: " +mergeSortTime/repeat);
        System.out.println("Avg time for Quick Sort in Milis: " +quickSortTime/repeat);
        System.out.println("Avg time for Heap Sort in Milis: " +heapSortTime/repeat);
        System.out.println("Avg time for Dual Pivot in Milis: " +dualPivotTime/repeat);
        System.out.println("Avg time for Radix Sort in Milis: " +radixSortTime/repeat);
        System.out.println('\n');
    	
    }
    
    public static void task3c( int LEN) {
    	task3a(LEN);
    	task3b(LEN);
    }
    
    
    public static void main( String [ ] args )
    {
    	int string_length;
    	int [] len = {6, 8, 10};
    	System.out.println( "Task 3a" );
        task3a(string_length=4);
        System.out.println( "Task 3b" );
        System.out.println('\n');
        task3b(string_length=4);
        System.out.println( "Task 3c" );
        System.out.println('\n');
        for (int i=0; i<len.length; i++) {
        		task3c(len[i]);
        		System.out.println("---------------------------------");
        }
        
    }
    
}
