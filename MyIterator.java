import java.util.ArrayList;
import java.util.Iterator;

public class MyIterator implements Iterator{
    int chainIndex = 0;
    int elementIndex = 0;
    ArrayList<String> [] table;
    public MyIterator(ArrayList<String> [] table){
        this.table = table;
    }
    @Override
    public boolean hasNext() {
        for (int i = chainIndex; i< table.length; i++){
            if (table[i] != null && !table[i].isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String next() {
        String keyReturned = "not found";
        for (int i = chainIndex; i < table.length; i++){
            if (table[i] != null && !table[i].isEmpty()){
                chainIndex = i;
                keyReturned = table[i].get(elementIndex);
                break;
            }
        }
        if (elementIndex+1 < table[chainIndex].size()){
            elementIndex++;
        } 
        else {
            elementIndex = 0;
            chainIndex++;
        }
        return keyReturned;
    }

}
