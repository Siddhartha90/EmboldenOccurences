import java.io.*;
import java.util.*;

class Fig {
  
  // Given an input string and a query, implement a function `highlight` that highlights all occurrences of the query with bold tag:
  // highlight("fig", "configure figma") => "con<b>fig</b>ure <b>fig</b>ma"
  //
  //. figfigfig
  //  fifigg
  // 'aba' -- 'ababa' -> [0, 2], [2, 4]
  // 'aba' -- 'abababa' -> [0, 2], [2, 4], [4, 6]
  // 'aba' -- 'abababa' -> 0, 2, 4
  // 'davy' -- 'davy' -> 
  // 'naan' -- 'naanaan' -> 0, 3
  // 'aba' -- 'abab' -> 
  // 'aba' -- 'abaa' -> 
  // Don't worry about optimizing runtime; a brute force solution is fine.
  //
  // You may use any helper functions/libraries that come with your chosen language and use Google to look up their interface definition
  // (eg: `text.substring(x, y) == query` or `text.indexOf(query)`)
    public static String highlight(String query, String text) {
      String result = "";      
      String replacementString = "<b>" + query + "</b>";
      
      result = text.replaceAll(query, replacementString);
      return result;
    }

    public static String highlightOverlapping(String query, String text) {
      if (text == null) {
          return null;
      }

      if (query == null || query.length() < 1) {
        return text;
      }

      String replacementString = "<b>" + query + "</b>";
      
      ArrayList<Integer> indices = new ArrayList<>();
      int index = text.indexOf(query);
      while(index >= 0) {
          indices.add(index);
          index = text.indexOf(query, index+1);
      }

      if (indices.size() < 1) {
          // No occurences found
        return text;
      }

      ArrayList<Pair> result = reduceArray(indices, query.length());
      System.out.println(result);

      StringBuilder emboldenedString = new StringBuilder();
      int start = 0;
      // sandwich string with bold tags
      for (Pair pair: result) {
          emboldenedString.append(text.substring(start, pair.first));
          emboldenedString.append(emboldenedString(text.substring(pair.first, pair.last + 1)));
          start = pair.last + 1;
      }

      // Take care of remaining string, if any
      if (start < text.length()) {
        emboldenedString.append(text.substring(start));
      }

      return emboldenedString.toString();
    }

    private static String emboldenedString(String str) {
      return "<b>" + str + "</b>";
    }
    
    public static void main(String[] args) {
      System.out.println("##TEST 1##");
      System.out.println(highlightOverlapping("aba", "ababaaba"));
      
      System.out.println("\n##TEST 2##");
      System.out.println(highlightOverlapping("fig", "configure figma"));
      
      System.out.println("\n##TEST 3##");
      System.out.println(highlightOverlapping("fig", "No occurrences here"));

      System.out.println("\n##TEST 4##");
      System.out.println(highlightOverlapping("sid", "sid"));

      System.out.println("\n##TEST 5##");
      System.out.println(highlightOverlapping("sid", null));

      System.out.println("\n##TEST 6##");
      System.out.println(highlightOverlapping("", "testing 123"));
    }
    
    private static class Pair {
      public Pair(int first, int last) {
        this.first = first;
        this.last = last;
      }
      
      public int first;
      public int last;
      
      public String toString(){
        return first + "," + last; 
      }
      
    }
  
    private static ArrayList<Pair> reduceArray(ArrayList<Integer> array, int increment) {
        ArrayList<Pair> reducedArray = new ArrayList<Pair>();
        // 0 2 4 6 11 13 15 
        // (0, 8) 11, 17
        // 0 2 4

        int index = 0;
        int first = array.get(0);
        Pair absorbedSubArray = new Pair(first, first + increment - 1);
      
        while (index < array.size() - 1){
          if (array.get(index+1) >= array.get(index) + increment) {
            // Next element does not overlap. Complete pair element and finish absorption.
            absorbedSubArray.last = array.get(index) + increment - 1;
            reducedArray.add(absorbedSubArray);            
            // account for next sub-block.
            first = array.get(index+1);
            absorbedSubArray = new Pair(first, first + increment - 1);
          } else {
            // Absorb next element.
            absorbedSubArray.last = array.get(index+1) + increment - 1;      
          }
          index++;
        }

        // Account for last element which might have gotten absorbed
        reducedArray.add(absorbedSubArray);            
      
      return reducedArray;
    }
    
  }
  