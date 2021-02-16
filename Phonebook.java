import java.util.Arrays;

public class Phonebook {
    public static void main(String[] args) {
        String[][] data = readFromFile(args[0]);
        int characteristic = Integer.parseInt(args[1]);
        String matcher = args[2];
        bubbleSort(data, characteristic);
        int start = findInitialIndex(data, matcher, characteristic);
        if (start == -1) {
            System.out.println("No results found");
        } else if (start >=0) {
            System.out.println(Arrays.deepToString(data[start]));
            int last = findLastIndex(data, matcher, characteristic, start);
            if (last != -1 && last != start) {
                System.out.println(Arrays.deepToString(data[last]));
            }
        }
        
                           
//        String[] left = {"kajal", "1230231231"};
//        String[] right = {"Hazard", "1231567890"};
//        String[][] test = {{"kajal", "12302342324"},{"Hazard", "4234234"}};
//        swap(test, 0, 1);
//        printRow(test, 4);
//        String[][] test = {{"Lorenzo", "9191919191"},{"Liam", "9090909090"},{"Kajal", "1231231231"},{"Hazard", "1230567890"}, {"Aram", "5757575757"}};
//        String matcher = "R";
//        System.out.println(Arrays.deepToString(test));
//        System.out.println(findInitialIndex(test, matcher, 0));
    }
    
    public static String[][] readFromFile(String filename) {
        In inStream = new In(filename);
        int numRows = inStream.readInt();
        int numCols = inStream.readInt();
        String[][] contacts = new String[numRows][2];
        for (int i = 0; i < numRows; i++) {
            contacts[i][0] = inStream.readString();
            contacts[i][1] = inStream.readString();
        }
        return contacts;
    }
    
    public static void swap(String[][] data, int left, int right) {
        String[] hold = data[left];
        data[left] = data[right];
        data[right] = hold; 
    }
    
    public static boolean isInversion(String[] left, String [] right, int characteristic) {
        if (left[characteristic].compareToIgnoreCase(right[characteristic]) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void bubbleSort(String[][] data, int characteristic) {
        int N = data.length;
        while (N >= 0) {
            for (int i = 0; i < data.length-1; i++) {
                if (isInversion(data[i], data[i+1], characteristic)) {
                    continue;
                } else {
                    swap(data, i, i+1);
                }
            }
            N--;
        }
    }
    
    //search array - see if mid is greater than, less than or equal to matcher
    //if equal - disregard right side of array
    //if greater than - disregard left side of array and repeat search with right half of array
    //if less than - disregard right side of array and repeat search with left half of array
    public static int findInitialIndex(String[][] data, String matcher, int characteristic) {
        int low = 0;
        int high = data.length;
        while (low <= high) {
            int mid = low + (high-low) / 2;
            if (data[mid][characteristic].startsWith(matcher) && mid==0) {
                return mid;
            } else if (data[mid][characteristic].startsWith(matcher) && data[mid-1][characteristic].startsWith(matcher)) {
                high = mid - 1;
            } else if (data[mid][characteristic].startsWith(matcher) && !data[mid-1][characteristic].startsWith(matcher)) {
                return mid;
            } else if (data[mid][characteristic].substring(0, matcher.length()).compareToIgnoreCase(matcher) > 0) {
                low = mid + 1;
            } else if (data[mid][characteristic].substring(0, matcher.length()).compareToIgnoreCase(matcher) < 0) {
                high = mid -1;
            }
        }
        
        return -1;
    }
    
    //loop through array - if match hold onto index and continue, if no match at all return -1
    
    public static int findLastIndex(String[][] data, String matcher, int characteristic, int start) {
        int index = -1;
        for (int i = start; i < data.length; i++) {
            if (data[i][characteristic].startsWith(matcher)) {
                index = i;
            }
        }
        
        return index;
    }
    
    public static void printRow(String[][] data, int row) {
        String s = "";
        for (int i = 0; i < data[row].length; i++) {
            s += data[row][i] + " ";
        }
        s += "\n";
        System.out.println(s);
    }
}


