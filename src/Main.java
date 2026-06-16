import collections.MyHashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, String> users = new MyHashMap<>();
        users.put("1", "Артём");
        users.put("2", "Вася");
        users.put("3", "Коля");

        users.printAll();

        System.out.println(users.get("1"));

        users.remove("1");
        users.remove("2");
        System.out.println(users.size());
        users.remove("3");
        users.printAll();
        System.out.println(users.size());
        System.out.println(users.isEmpty());
        System.out.println(users.get("3"));
    }
}
