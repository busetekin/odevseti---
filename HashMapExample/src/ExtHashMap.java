import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ExtHashMap<K1, K2, V> extends HashMap<K1, HashMap<K2, V>> {

	private static final long serialVersionUID = 1L;

	public ExtHashMap() {
		super();
	}

	public void putChildren(K1 key1, K2 key2, V value) {

		HashMap<K2, V> childMap = get(key2);
		if (childMap == null) {
			childMap = new HashMap<K2, V>();
			put(key1, childMap);
		}
		childMap.put(key2, value);
	}

	public void listChildrenKeys() {

		/*
		 * map'ime super ile ulaþtým.Value'ya atadýðým inner Hashmap, daha sonra
		 * onun yani inner hashmap'in keylerine Set arayüzünden ulaþtým.
		 */
		for (java.util.Map.Entry<K1, HashMap<K2, V>> entry : super.entrySet()) {

			HashMap<K2, V> value = entry.getValue();
			Set<K2> s = value.keySet();

			Iterator<K2> childrenKey = s.iterator();
			while (childrenKey.hasNext()) {
				System.out.println(childrenKey.next());
			}

		}

	}

	public void listChildrenValues() {

		for (java.util.Map.Entry<K1, HashMap<K2, V>> entry : super.entrySet()) {

			HashMap<K2, V> value = entry.getValue();
			Collection<K2> c = (Collection<K2>) value.values();

			Iterator<K2> childrenValue = c.iterator();
			while (childrenValue.hasNext()) {
				System.out.println(childrenValue.next());
			}

		}

	}

	public void getChildrenValues(K2 key2) {

		for (java.util.Map.Entry<K1, HashMap<K2, V>> entry : super.entrySet()) {

			HashMap<K2, V> value = entry.getValue();

			Collection<K2> c = (Collection<K2>) value.values();
			Set<K2> s = value.keySet();

			Iterator<K2> childrenKey = s.iterator();
			Iterator<K2> childrenValue = c.iterator();

			while (childrenKey.hasNext()) {
				// childrenValue.next()
				if (childrenKey.next().equals(key2))
					System.out.println(childrenValue.next());

			}

		}

	}

	public void removeValueAtChildren(K2 key2) {

		for (java.util.Map.Entry<K1, HashMap<K2, V>> map : super.entrySet()) {

			HashMap<K2, V> innerMap = map.getValue();
			
			for (java.util.Map.Entry<K2, V> innerMapElement : innerMap.entrySet()) {

				if (innerMapElement.getKey().equals(key2)) {
					
					System.out.println("key :" +innerMapElement.getKey() + " value :" +innerMapElement.getValue());
					innerMap.remove(innerMapElement.getKey());
					
					/* her key bir value tuttuðu için 23 numaralý key'in durmasý ama value5 deðerinin
					 * silinmesi hashmap'e uymayan bir kullaným þekli olduðu için soruda tam olarak ne
					 * istendiðini anlmadým bu method için.
					 */
					
				}
				
			}

		}

	}

	public static void main(String[] args) {

		ExtHashMap<String, Integer, String> map = new ExtHashMap<String, Integer, String>();
		map.putChildren("Ali", 1234, "VALUE1");
		map.putChildren("Veli", 4321, "VALUE2");
		map.putChildren("Ayþe", 4657839, "VALUE3");
		map.putChildren("Zeynep", 13, "VALUE4");

		System.out.println("Tüm Kayýlar :");
		System.out.println(map.entrySet() + "\n");

		System.out
				.println("Childeren's keys (listChildrenKeys metodu testi) : \n");

		map.listChildrenKeys();
		System.out.println();

		System.out
				.println("Childeren's values (listChildrenValues metodu testi) : \n");
		map.listChildrenValues();

		System.out.println(".......\n");

		System.out
				.println(" 13 numaralý öðrencinin deðeri (getChildrenValues metodu testi): ");
		map.getChildrenValues(13);

		System.out.println(" 1234 numaralý öðrencinin deðeri :");
		map.getChildrenValues(1234);

		/*
		 * silme fonksiyonunu kontrol etmeden önce yeni bir children ekledim,tüm
		 * kayýtlarý listeledim sonra sildim ve yine kayýtlarý listeledim.
		 */
		System.out.println("\n");
		System.out
				.println("Yeni bir öðrenci ekleniyor ve tüm kayýtlar listeleniyor...");
		map.putChildren("buse", 23, "value5");

		System.out.println(map.entrySet());
		System.out.println("\n");
		System.out
				.println("Key = 23 olan öðrenci deðeri siliniyor(removeValueAtChildren metodu testi).. \n");
		map.removeValueAtChildren(23);
		// System.out.println(map.entrySet());

		System.out.println(map.entrySet());
	}
}
