/*
Alex Petralia
6/30/2017
Sorting Algorithms (Insertion, Quick, Merge and radix Sort)
CSCI 241






Sources Used:
---------------------
https://stackoverflow.com/questions/5287538/how-can-i-get-the-user-input-in-java   
https://stackoverflow.com/questions/16409155/merge-sort-comparison-counter
https://www.codeproject.com/Questions/632289/counting-quicksort-comparisions
---------------------
*/

import java.util.Scanner;
import java.util.*;
import java.util.Random;

public class SortCompare {
   static int comparisons = 0;
   public static void main (String [] args) {
      //    Scanner Input Setup
      Scanner scan = new Scanner(System.in);
      int arraySize = 0;
      System.out.println("INPUT PARAMS");      
      System.out.println("============");      
      
      System.out.println("How many entries? ");
      arraySize = scan.nextInt();
      System.out.println("Which sort [m, i, q, r, all]? ");
      String sort = scan.next();
      //    Fills array with random numbers  
      int array[] = new int[arraySize]; 
      int helperArray[] = new int[arraySize];
      for (int i = 0; i < array.length; i++){      
         array[i] = (int)(Math.random() * 100);   // add more 0's to 100 in this line if more digits are preferred (eg: 1000 = #'s 0 to 999)
      }
      // Extra copies of the random array for the "all" sort
      int[] arrayCopy1 = array.clone();
      int[] arrayCopy2 = array.clone();
      int[] arrayCopy3 = array.clone();
            
      //Checks for which sort to use & contains print statements (below)
      
         //Merge Sort execution
      if (sort.charAt(0) == 'm'){
         System.out.println("Merge Sort");
         System.out.println("==========");
         System.out.print("Unsorted array: ");
         printOrigArray(array);     
         mergeSort(array, helperArray, 0, array.length - 1);
         System.out.println(" ");
         System.out.println("Number of Comparisons: " + comparisons);
         System.out.print("Sorted Array: ");
         printArray(array);
         //Insertion Sort execution
      }else if (sort.charAt(0) == 'i') {
         System.out.println("Insertion Sort");
         System.out.println("==============");
         System.out.print("Unsorted array: "); 
         printOrigArray(array); 
         insertionSort(array);
         //Quick Sort execution
      }else if (sort.charAt(0) == 'q') { 
         System.out.println("Quick Sort");
         System.out.println("==========");
         System.out.print("Unsorted array: ");
         printOrigArray(array);
         quickSort(array, 0, array.length - 1);
         System.out.println(" ");
         System.out.println("Number of Comparisons: " + comparisons);
         System.out.print("Sorted Array: ");
         printArray(array);
         //Radix Sort execution
      }else if (sort.charAt(0) == 'r') { 
         System.out.println("Radix Sort");
         System.out.println("==========");
         System.out.print("Unsorted array: ");
         printOrigArray(array);
         radixSort(array);
         System.out.println(" ");
         System.out.println("Number of Comparisons: " + comparisons);
         System.out.print("Sorted Array: ");
         printArray(array);
         //"all" sorts execution
      }else if (sort.equals("all")) {
            //merge
         System.out.println("Merge Sort");
         System.out.println("==========");
         System.out.print("Unsorted array: ");
         printOrigArray(array);     
         mergeSort(array, helperArray, 0, array.length - 1);
         System.out.println(" ");
         System.out.println("Number of Comparisons: " + comparisons);
         System.out.print("Sorted Array: ");
         printArray(array);
         System.out.println(" ");
         comparisons = 0;
            //insertion
         System.out.println(" ");
         System.out.println("Insertion Sort");
         System.out.println("==============");
         System.out.print("Unsorted array: "); 
         printOrigArray(arrayCopy1); 
         insertionSort(arrayCopy1);
         System.out.println(" ");
         comparisons = 0;
            //quick
         System.out.println(" ");
         System.out.println("Quick Sort");
         System.out.println("==========");
         System.out.print("Unsorted array: ");
         printOrigArray(arrayCopy2);
         quickSort(arrayCopy2, 0, arrayCopy2.length - 1);
         System.out.println(" ");
         System.out.println("Number of Comparisons: " + comparisons);
         System.out.print("Sorted Array: ");
         printArray(arrayCopy2);
         System.out.println(" ");
         comparisons = 0;
            //radix
         System.out.println(" ");
         System.out.println("Radix Sort");
         System.out.println("==========");
         System.out.print("Unsorted array: ");
         printOrigArray(arrayCopy3);
         radixSort(arrayCopy3);
         System.out.println(" ");
         System.out.println("Number of Comparisons: " + comparisons);
         System.out.print("Sorted Array: ");
         printArray(arrayCopy3);
      }
   }
   //end of execution logic
   
   // Prints Original Array
   public static void printOrigArray(int[] array){
      if (array.length >= 20) {
         System.out.print("[array.length > 20]"); 
      }else{
         for (int j = 0; j < array.length; j++){
            System.out.print(array[j] + " ");
         }
      }
   }
   
   // Prints inputed array
   public static void printArray(int array[]){
      int length = array.length;
      if (length >= 20) {
         System.out.print("[array.length > 20]");
      }else{
         for (int i = 0; i < length; i++){
            System.out.print(array[i] + " ");  
         }
      }
   }
   
   // Merge Sort
   public static void mergeSort(int[] array, int[] helperArray, int lowIndex, int highIndex) {
      if (lowIndex < highIndex) {
         int midIndex = lowIndex + (highIndex - lowIndex) / 2;
         mergeSort(array, helperArray, lowIndex, midIndex);
         mergeSort(array, helperArray, midIndex + 1, highIndex);
         merge(array, helperArray, lowIndex, midIndex, highIndex);
      }  
   }
   public static void merge (int[] array, int[] helperArray, int lowIndex, int midIndex, int highIndex) {    // Helper Function for mergeSort
      for (int i = lowIndex; i <= highIndex; i++) {
         helperArray[i] = array[i]; 
      }
      int x = lowIndex;
      int y = midIndex + 1;
      int z = lowIndex;
      while (x <= midIndex && y <= highIndex) { // copies lowest values to right or left of the original array: "origArray"
         if (helperArray[x] <= helperArray[y]) {
            array[z] = helperArray[x]; 
            x++;
            comparisons++;
         }else{
            array[z] = helperArray[y];
            y++;
            comparisons++;   
         }      
         z++;  
      }
      while (x <= midIndex) { //copies left of origArray into newArray
        array[z] = helperArray[x];
        z++;
        x++;
      }
   }
   
   // Insertion Sort 
   public static void insertionSort(int[] array) {
      System.out.println("");
      int temp;
      //int numComparisons = 0;
      for (int i = 1; i < array.length; i++) {
         for (int j = i; j > 0; j--) {
            comparisons++;
            if (array[j] < array[j - 1]) {
               temp = array[j];
               array[j] = array[j-1];
               array[j-1] = temp;
            }
         }
      }
      System.out.print("Number of Comparisons: " + comparisons);
      System.out.println(" ");
      System.out.print("Sorted Array: ");
      printArray(array);
   }
   
   //    QuickSort
   public static void quickSort(int array[], int lowIndex, int highIndex) {
      if (lowIndex < highIndex){
         //comparisons++;  test spot for comparisons
         int pivot = partition(array, lowIndex, highIndex);
         quickSort(array, lowIndex, pivot - 1);
         quickSort(array, pivot + 1, highIndex);
      }
   }
   public static int partition(int[] array, int lowIndex, int highIndex) {
      int pivot = array[highIndex];  
      int i = (lowIndex - 1);
      for (int j = lowIndex; j < highIndex; j++){
         comparisons++; //best comparisons++ spot
         if(array[j] <= pivot){
            //comparisons++; test spot for comparisons
            i++;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
         }
      }
      int temp = array[i + 1];
      array[i + 1] = array[highIndex];
      array[highIndex] = temp;
      return i + 1;
   }
   
   //    Radix Sort
   public static void radixSort(int[] array) {
      int numBase = 10;  //number set is in decimal (0 to 9)
      int minVal = array[0];
      int maxVal = array[0];
      for (int i = 1; i < array.length; i++){ //finds min and max values
         if (array[i] < minVal){
            minVal = array[i];
         }else if(array[i] > maxVal){
            maxVal = array[i];
         }
      }
      int exp = 1; //exponent
      while ((maxVal - minVal) / exp >= 1){
         countSort(array, numBase, exp, minVal);
         exp *= numBase;
      }
   }
   private static void countSort(int[] array, int numBase, int exp, int minVal) { //helper function for radixSort
      int binIndex;
      int[] bins = new int[numBase];
      int[] finalArray = new int[array.length];
      // create bins
      for(int i = 0; i < numBase; i++){ 
         bins[i] =0;
      }
      //count occurences
      for(int i = 0; i < array.length; i++){
         binIndex = (int)(((array[i] - minVal) / exp) % numBase);
         bins[binIndex]++;
      }
      //move occurences
      for (int i = 1; i < numBase; i++){
         bins[i] += bins[i - 1];
      }
      for(int i = array.length - 1; i >= 0; i--){
         binIndex = (int)(((array[i] - minVal) / exp) % numBase);
         finalArray[--bins[binIndex]] = array[i];
      }
      //move elements in bins
      for(int i = 0; i < array.length; i++) {
         array[i] = finalArray[i];
      }
   }
   
}