
public class TestHash {
	public static void main(String[] args){
		System.out.println(hashIndex("9999"));
	}
	
	private static int hashIndex(String key) {
		int lastIndex = key.length() - 1;
		int primeNum = 37; // Arbitrary primary number
		int tableSize = 7919;
		
		int index = (int) key.charAt(lastIndex) % tableSize;
		
		for (int i = lastIndex - 1; i >= 0; i--){
			index = (index + (int) Math.pow(primeNum, i) * (int) key.charAt(i)) % tableSize;
		}
		
		return index % tableSize;
	}
}
