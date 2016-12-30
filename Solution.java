import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    // Entry class
    public static class Entry{
        int year;
        int month;
        int day;
        String type; // engagement type
        int numEng; // number of engagements
        Entry(int Y, int M, int D, String T, int N){
            year = Y;
            month = M;
            day = D;
            type = new String(T);
            numEng = N;
        }
    }
    
    // Date class
    public static class Date{
        int year;
        int month;
        Date(){
            year = 0;
            month = 0;
        }
    }
    
   
    
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String dateLine = scanner.nextLine();
        String[] interval = dateLine.split("[-,]");
        
        // Start Date
        Date startDate = new Date();
        startDate.year = Integer.parseInt(interval[0].trim());
        startDate.month = Integer.parseInt(interval[1].trim());
        
        // End Date
        Date endDate = new Date();
        endDate.year = Integer.parseInt(interval[2].trim());
        endDate.month = Integer.parseInt(interval[3].trim());
        
        // Skip the new line
        scanner.nextLine();
        
        // List of all entries
        ArrayList<Entry> entries = new ArrayList<Entry>();
        
        // Parse through every line and create a new entry
        while (scanner.hasNextLine()){
            String inputLine = scanner.nextLine();
            String[] nextLine = inputLine.split("[-,]");

            // int year, int month, int day, String type, int numEng
            int year = Integer.parseInt(nextLine[0].trim());
            int month = Integer.parseInt(nextLine[1].trim());
            int day = Integer.parseInt(nextLine[2].trim());
            String type = nextLine[3].trim(); // engagement type
            int numEng = Integer.parseInt(nextLine[4].trim()); // number of engagements
            Entry newEntry = new Entry(year, month, day, type, numEng);
            entries.add(newEntry);
            
        }
        
        // Sort based on the year, and then the type
        Collections.sort(entries, new Comparator<Entry>() {
            @Override 
            public int compare(Entry first, Entry second) {
                // If both have the same date, sort alphabetically
                if (first.year == second.year && first.month == second.month){
                    if (first.type.compareTo(second.type) > 0)
                     return 1;
                    else return -1;
                }
                else if (first.year < second.year || first.year == second.year && first.month < second.month ){
                   return 1;
                }
                else return -1;
            }   
        });
     
        
        String output = "";
        HashMap<Integer, Integer> dates = new HashMap<Integer, Integer>();
        HashMap<String, Integer> engagements = new HashMap<String, Integer>();
        
        for (int k = 0; k < entries.size(); k++){
            // Current entry
            Entry curr = entries.get(k);
            
            // Check that the entry is within the date range
            if (curr.year >= startDate.year && curr.year <= endDate.year && curr.numEng > 0){
                if (curr.year == startDate.year && curr.month >= startDate.month || 
                    curr.year == endDate.year && curr.month < endDate.month){
                    
                    // If entry already exists at this date, add to hashmap because you'll be appending output
                    if (dates.containsKey(curr.year) && dates.get(curr.year) == curr.month){
                                                
                        // Increment number of engagements for this type, if it's in the hashmap
                        if (engagements.containsKey(curr.type)){
                            engagements.put(curr.type, engagements.get(curr.type) + curr.numEng);
                        } else {
                            engagements.put(curr.type, curr.numEng);
                        }
                        
                    } else engagements.put(curr.type, curr.numEng);
                        
                    // Once you're done with this entry, add it to the dates HashMap
                    dates.put(curr.year, curr.month);
                    
                    
                    // If the current date is different from the next date, or we're at the last date, add to output
                    if (k == entries.size() - 1 || curr.year != entries.get(k+1).year || 
                        curr.year == entries.get(k+1).year && curr.month != entries.get(k+1).month){
                        if (!output.equals("")) output += "\n";

                        output += String.format("%d-%02d", curr.year, curr.month);
                        
                        // Sort results alphabetically
                        ArrayList<String> list =  new ArrayList(new TreeSet<String>(engagements.keySet()));
                        for (String key : list){
                            output += String.format(", %s, %d", key, engagements.get(key));
                        }
                        engagements.clear();
                    }
                }
            }
        }
        System.out.print(output);
    }
}