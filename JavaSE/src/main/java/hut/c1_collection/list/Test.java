package hut.c1_collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

    /**
     *
     */
    public static void testArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("1");
        System.out.println(arrayList);
        // 四种遍历方式
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        for (Object o : arrayList) {
            System.out.println(o);
        }
        Iterator<Object> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        ListIterator<Object> listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }

    /**
     *
     */
    public static void testLinkedList() {
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("1");
        System.out.println(linkedList);
        // 四种遍历方式
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        for (Object o : linkedList) {
            System.out.println(o);
        }
        Iterator<Object> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        ListIterator<Object> listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }

    /**
     * 并发的时候保证数据安全，并且加锁写的时候，其他线程可以读，因为写的时候是生成新的数组，缺点是耗费内存，因为要复制一个新的数组嘛
     * 每次添加生成一个新的数组，遍历的时候原数组的数据
     */
    public static void testCopyOnWriteArrayList() {
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("1");
        copyOnWriteArrayList.add("2");
        copyOnWriteArrayList.add("1");
        System.out.println(copyOnWriteArrayList);
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            System.out.println(copyOnWriteArrayList.get(i));
        }
        for (Object o : copyOnWriteArrayList) {
            System.out.println(o);
        }
        Iterator<Object> iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        ListIterator<Object> listIterator = copyOnWriteArrayList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
//            System.out.println(copyOnWriteArrayList.hashCode());
//            copyOnWriteArrayList.add("3");
//            System.out.println(copyOnWriteArrayList.hashCode());
        }
    }

    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testCopyOnWriteArrayList();
    }

}
