import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HashTable {
    private int size = 4;
    private int count = 1;
    private ArrayList<String> [] table = new ArrayList [size];
    public HashTable(){

    }
// Methods you have to supply:
//
    public void put(String key) {
        System.out.println("Count: " + count + "  Size: " + size);
        if(count*3 >= size*2){//table s
        // Saturated
            expand();
        }
        int index = Math.abs(key.hashCode()%size);
        if(table[index] == null){
            table[index] = new ArrayList<String>();
        }
        table[index].add(key);
        count++;
    }
    public void expand(){
        count = 0;
        System.out.println("rehashing");
        Iterator itr = this.keys();
        size*=2;
        table = new ArrayList [size];
        
        while(itr.hasNext()){
            this.put((String)itr.next());
        }
        
    }
//
    public boolean get(String key) {
        int index = key.hashCode();
        return table[index].contains(key);
    }
//
    public String remove(String key){
        int index = Math.abs(key.hashCode()%size);
        boolean found = table[index].remove(key);

        if(found){
            return key;
        }
        return "key not found";
    }
    public Iterator keys() {
        return new MyIterator(table);
    }
    public void print(){
        int someNumber = 0;
        for (ArrayList<String> chain : table){
            if (chain != null && !chain.isEmpty()){
                for (String key : chain){
                    System.out.print("[" +  key + "] ");
                }
            }
            System.out.println(someNumber++);
        }
  	}
	/**
	 * Loads this HashTable from a file named "Lookup.dat".
	 */
    public void load() {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        
        // Open the file for reading
        try {
            File f = new File(System.getProperty("user.home"), "Lookup.dat");
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e) {
            System.err.println("Cannot find input file \"Lookup.dat\"");
        }
        
        // Read the file contents and save in the HashTable
        try {
            while (true) {
                String key = bufferedReader.readLine();
                if (key == null) return;
                String value = bufferedReader.readLine();
                if (value == null) {
                    System.out.println("Error in input file");
                    System.exit(1);
                }
                String blankLine = bufferedReader.readLine();
                if (!"".equals(blankLine)) {
                    System.out.println("Error in input file");
                    System.exit(1);
                }
                put(key);
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
        
        // Close the file when we're done
        try {
            bufferedReader.close( );
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }

	/**
	 * Saves this HashTable onto a file named "Lookup.dat".
	 */
	public void save() {
        FileOutputStream stream;
        PrintWriter printWriter = null;
        Iterator iterator;
        
        // Open the file for writing
        try {
            File f = new File(System.getProperty("user.home"), "Lookup.dat");
            stream = new FileOutputStream(f);
            printWriter = new PrintWriter(stream);
        }
        catch (Exception e) {
            System.err.println("Cannot use output file \"Lookup.dat\"");
        }
       
        // Write the contents of this HashTable to the file
        iterator = keys();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            printWriter.println(key);
        }
       
        // Close the file when we're done
        printWriter.close( );
    }
    
    /**
     * Replaces all line separator characters (which vary from one platform
     * to the next) with spaces.
     * 
     * @param value The input string, possibly containing line separators.
     * @return The input string with line separators replaced by spaces.
     */
    private String removeNewlines(String value) {
        return value.replaceAll("\r|\n", " ");
    }
    public static void main(String[] args) {
        HashTable table = new HashTable();
        table.put("stuff1");
        table.put("stuff2");
        table.put("stuff3");
        table.put("stuff1");
        table.put("stuff2");
        table.put("stuff3");
        table.remove("stuff1");
        table.remove("stuff1");
        table.remove("stuff1");
        table.print();
        Iterator itr = table.keys();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
