/*
Author: Samuel Shin
Project 1: Analysis of Comb Sort
 */

import java.util.Random;
import java.io.IOException;

public class CombSort {

    public static int[] anArray;

    // To find gap between elements
    int getNextGap(int gap) {
        // Shrink gap by Shrink factor
        gap = (gap * 10) / 13;
        if (gap < 1)
            return 1;
        return gap;
    }

    // Function to sort arr[] using Comb Sort
    void sort(int arr[]) {
        int n = arr.length;

        // initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped == true) {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;


            //test
            int loopCount = 0;
            // Compare all elements with current gap
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    // Swap arr[i] and arr[i+gap]
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;

                    // Set swapped
                    swapped = true;
                }


            }
        }
    }

    //fill the arrays with random numbers
    public static int randomFill(){
        Random rand = new Random();
        int randomNum = rand.nextInt();
        return randomNum;
    }

    //make an array of given size and fill with random numbers
    public static int[] list(int size){
        anArray = new int[size];
        for(int i = 0; i < anArray.length; i++){
            anArray[i] = randomFill();
        }
        return anArray;
    }


    // Driver method
    public static void main(String args[]) throws IOException
    {
        CombSort ob = new CombSort();

        int arr10[] = list(10);

        //print unsorted array
        System.out.println("array before sorting: ");
        for (int i=0; i<arr10.length; ++i)
            System.out.print(arr10[i] + " ");

        long start = System.nanoTime();
        ob.sort(arr10);
        long end = System.nanoTime();
        long duration = end - start;

        //print sorted array
        System.out.println("\nsorted array");
        for (int i=0; i<arr10.length; ++i)
            System.out.print(arr10[i] + " ");

        System.out.printf("\n\nDuration for %d element array:     \t\t%d nanoseconds\r\n", arr10.length, duration);

        //sorting array of size 100
        int arr100[] = list(100);
        long start100 = System.nanoTime();
        ob.sort(arr100);
        long end100 = System.nanoTime();
        long duration100 = end100 - start100;
        System.out.printf("Duration for %d element array: \t\t%d nanoseconds\r\n", arr100.length, end100 - start100);

        //sorting array of size 1,000
        int arr1000[] = list(1000);
        long start1000 = System.nanoTime();
        ob.sort(arr1000);
        long end1000 = System.nanoTime();
        long duration1000 = end1000 - start1000;
        System.out.printf("Duration for %d element array: \t\t%d nanoseconds\r\n", arr1000.length, duration1000);

        //sorting array of size 100,000
        int arr100000[] = list(100000);
        long start100000 = System.nanoTime();
        ob.sort(arr100000);
        long end100000 = System.nanoTime();
        long duration100000 = end100000 - start100000;
        System.out.printf("Duration for %d element array: \t\t%d nanoseconds\r\n", arr100000.length, duration100000);

        //sorting array of size 1,000,000
        int arr1000000[] = list(1000000);
        long start1000000 = System.nanoTime();
        ob.sort(arr1000000);
        long end1000000 = System.nanoTime();
        long duration1000000 = end1000000 - start1000000;
        System.out.printf("Duration for %d element array: \t%d nanoseconds\r\n", arr1000000.length, duration1000000);
        System.out.println();

        //all the calls for each of the different sized arrays to excel document
        int randomArray100 = 100;
        int randomArray1000 = 1000;
        int randomArray100000 = 100000;
        int randomArray1000000 = 1000000;

        writeXLSX XLSXWriter = new writeXLSX("./exportCombSort.xlsx");

        toXLSX(randomArray100, XLSXWriter, 0, 100);
        toXLSX(randomArray1000, XLSXWriter, 3, 100);
        toXLSX(randomArray100000, XLSXWriter, 6, 100);
        toXLSX(randomArray1000000, XLSXWriter, 9, 100);

        //close the excel writer
        XLSXWriter.close();

    }

    //this method formats the outputted excel sheet
    private static void toXLSX(int randomArraySize, writeXLSX XLSXWriter, int columnStart, int numIterations){
        long start, end;
        CombSort ob = new CombSort();
        for(int i = 0; i < numIterations; i++){
            int randomArray[];
            randomArray = list(randomArraySize);
            start = System.nanoTime();
            ob.sort(randomArray);
            end = System.nanoTime();
            XLSXWriter.writeSize(i, columnStart, randomArray.length);
            XLSXWriter.write(i, columnStart+1, end-start);
        }
        System.out.printf("%d element array finished writing to XLSX\r\n", randomArraySize);
    }
}