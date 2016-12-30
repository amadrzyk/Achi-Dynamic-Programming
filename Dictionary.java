import java.util.*;
import java.lang.Math;

public class Dictionary implements DictionaryADT {

	// TABLE IS ESSENTIALLY AN ARRAY OF LINKEDLISTS
	private ArrayList<LinkedList<ConfigData>> table;
	private int tableSize;

	// Constructor for a dictionary
	public Dictionary(int size){
		table = new ArrayList<LinkedList<ConfigData>>(size);
		for (int i = 0; i < size; i++){
			table.add(i, null);
		}
		tableSize = size;
	}

	public int insert(ConfigData pair) throws DictionaryException {
		int index = hashIndex(pair.getConfig());

		// FIRST CHECK IF THE TABLE EVEN EXISTS
		if (table == null) throw new DictionaryException("Dictionary was not created");

		// THEN CHECK THAT THERE ISN'T A LIST AT THE INDEX OF THE TABLE (IF NOT, THEN NO COLLISION! :) )
		if (table.get(index) == null){
			table.add(index, new LinkedList<ConfigData>()); // shifts null element to the right
			table.remove(index+1);							// hence we delete that null element

			// CHECK IF LINKEDLIST ADDITION FAILED
			if (table.get(index) == null){
				throw new DictionaryException("Could not create new linkedlist");
			}

			table.get(index).add(pair);
			return 0;
		} 

		// THIS RUNS IF YOU GET A COLLISION (I.E. LIST AT INDEX ALREADY EXISTS)
		else {
			// IF CONFIGURATION ALREADY EXISTS IN THE TABLE, THEN THROW AN EXCEPTION
			LinkedList<ConfigData> list = table.get(index);
			for (ConfigData item : list) {
				if (item.getConfig().equals(pair.getConfig())){
					throw new DictionaryException("Item already in Dictionary");
				}
			}

			table.get(index).add(pair);

			return 1;
		}
	}

	public void remove(String config) throws DictionaryException {
		// IF CONFIGURATION DOESN'T EXIST IN THE TABLE, THEN THROW AN EXCEPTION
		if (find(config) == -1){
			throw new DictionaryException("Could not remove, item was not in the dictionary");
		} else {
			// WHERE IN THE TABLE THE LIST IS STORED
			int tableIndex = hashIndex(config);
			LinkedList<ConfigData> list = table.get(tableIndex);

			// WHERE IN THE LINKEDLIST THE CONFIG IS STORED
			int listIndex = 0;
			int i = 0;
			for (ConfigData item : list){
				if (item.getConfig() == config){
					listIndex = i;
				}
				i++;
			}

			// REMOVES CONFIGDATA
			list.remove(listIndex);

			// REMOVE LIST IF IT'S EMPTY
			if (list.size() == 0){
				table.add(tableIndex, null);  // Adding a null at the index shifts everything right
				table.remove(tableIndex + 1); // so we have to remove what we wanted to replace
			}
		}
	}

	public int find(String config) {
		int index = hashIndex(config);

		// RETURN IF NO LIST IN TABLE AT INDEX
		if (table.get(index) == null) return -1;

		// IF LIST EXISTS, FIND INDEX OF CONFIGDATA ITEM
		int listIndex = -1;
		int i = 0;
		for (ConfigData item : table.get(index)){
			if (item.getConfig().equals(config)){
				listIndex = i;
			}
			i++;
		}

		if (listIndex == -1){
			return -1;
		} else {
			return table.get(index).get(listIndex).getScore();
		}
	}

	private int hashIndex(String key) {
		int lastIndex = key.length() - 1;
		int primeNum = 37; // Arbitrary primary number

		int index = (int) key.charAt(lastIndex) % tableSize;

		for (int i = lastIndex; i >= 0; i--){
			index = (index + (int) Math.pow(primeNum, i) * (int) key.charAt(i)) % tableSize;
		}

		return index % tableSize;
	}
}
