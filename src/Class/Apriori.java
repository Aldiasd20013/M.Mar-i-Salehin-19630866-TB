/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.*;
import java.sql.*;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Tuple {
    Set<Integer> itemset;
    int support;
    
    Tuple(){
        itemset = new HashSet<>();
        support = -1;
    }
    
    Tuple(Set<Integer> s) {
        itemset = s;
        support = -1;
    }
    
    Tuple(Set<Integer> s, int i) {
        itemset = s;
        support = i;
    }
}




/**
 *
 * @author Glora
 */
public class Apriori {
    static Set<Tuple> c;
    static Set<Tuple> l;
    static int d[][];
    static int min_support;
    /**
     * @param args the command line arguments
     */
    public void analisis ()throws Exception {
        try {
            File file = new File(logic_koneksi.almDir + "Apriori.txt");
            PrintStream stream = new PrintStream(file);
            System.out.println(file.getAbsolutePath());
            System.setOut(stream);
        } catch (Exception ex) {
            
        }
        getDatabase();
        c = new HashSet<>();
        l = new HashSet<>();
        Scanner scan = new Scanner(System.in);
        int i, j,  m, n;
        min_support = Integer.parseInt(JOptionPane.showInputDialog(null, "isi angka", "Tentukan min support", 3));
        System.out.println(min_support);
        Set<Integer> candidate_set = new HashSet<>();
        for (i = 0; i < d.length; i++) {
            System.out.println("Transaksi ke_ : " + (i+1) + ":");
            for (j = 0; j<d[i].length; j++) {
                System.out.print("(f) item ke" + (j+1) + "=");
                System.out.println(d[i][j]);
                candidate_set.add(d[i][j]);
            }
        }
        
        Iterator<Integer> iterator = candidate_set.iterator();
        while (iterator.hasNext()) {
            Set<Integer> s = new HashSet<>();
            s.add(iterator.next());
            Tuple t = new Tuple(s, count(s));
            c.add(t);
        }
        
        prune();
        generateFrequentItemsets();
    }
    
    static int count(Set<Integer>s){
        int i, j, k;
        int support = 0;
        int count;
        boolean containsElement;
        for (i = 0; i < d.length; i++) {
            count = 0;
            Iterator<Integer> iterator = s.iterator();
            while (iterator.hasNext()) {
                int element = iterator.next();
                containsElement = false;
                for (k = 0; k < d[i].length; k++) {
                    if (element == d[i][k]) {
                        containsElement = true;
                        count++;
                        break;
                    }
                }
                
                if (!containsElement) {
                    break;
                }
            }
            
            if (count == s.size()) {
                support++;
            }
        }
        return support;
    }
    
    static void prune() {
        l.clear();
        Iterator<Tuple> iterator = c.iterator();
        while (iterator.hasNext()) {
            Tuple t = iterator.next();
            if (t.support >= min_support) {
                l.add(t);
            }
        }
        System.out.println("-+- Kombinasi Item -+-");
        for (Tuple t : l) {
            System.out.println(t.itemset + " : "+ t.support);
        }
    }
    
    static void generateFrequentItemsets() {
        boolean toBeContinued = true;
        int element = 0;
        int size = 1;
        Set<Set> candidate_set = new HashSet<>();
        while (toBeContinued) {
            candidate_set.clear();
            c.clear();
            Iterator<Tuple> iterator = l.iterator();
            while (iterator.hasNext()) {
            Tuple t1 = iterator.next();
            Set<Integer> temp = t1.itemset;
            Iterator<Tuple> it2 = l.iterator();
            while (it2.hasNext()) {
                Tuple t2 = it2.next();
                Iterator<Integer> it3 = t2.itemset.iterator();
                while (it3.hasNext()){
                    try {
                        element = it3.next();
                    } catch (ConcurrentModificationException e){
                        break;
                    }
                    temp.add(element);
                    if (temp.size() !=size) {
                        Integer[] int_arr = temp.toArray(new Integer[0]);
                        Set<Integer> temp2 = new HashSet<>();
                        for (Integer x : int_arr) {
                            temp2.add(x);                        
                        }
                        candidate_set.add(temp2);
                        temp.remove(element);
                        
                    }
                }
                
            }
            
        }
            Iterator<Set> candidate_set_iterator = candidate_set.iterator();
            while (candidate_set_iterator.hasNext()) {
                Set  s = candidate_set_iterator.next();
                c.add(new Tuple(s, count(s)));
            }
            prune();
            if (l.size() <=1) {
                toBeContinued = false;
            }
            size++;
        }
        System.out.println("\n++= FINAL LIST =+=");
        for (Tuple t : l) {
            System.out.println(t.itemset + " : " + t.support);
        }
    }
    
    static void getDatabase() throws Exception {
        logic_koneksi test = new logic_koneksi();
        test.makeConnect();
        Connection con = logic_koneksi.getConnection();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT (RIGHT(faktur,2)), (RIGHT(prd_id,5)) FROM trs_penjualan_detail");
        Map<Integer, List<Integer>> m = new HashMap<>();
        List<Integer> temp;
        
        while (rs.next()) {
            int list_no = Integer.parseInt(rs.getString(1));
            int object = Integer.parseInt(rs.getString(2));
            temp = m.get(list_no);
            if (temp == null) {
                temp = new LinkedList<>();
            }
            temp.add(object);
            m.put(list_no, temp);
        }
        Set<Integer> keyset = m.keySet();
        d = new int[keyset.size()][];
        Iterator<Integer> iterator = keyset.iterator();
        int count = 0;
        
        while (iterator.hasNext()) {
            temp = m.get(iterator.next());
            Integer[] int_arr = temp.toArray(new Integer[0]);
            d[count] = new int[int_arr.length];
            for (int i = 0; i < d[count].length; i++) {
                d[count][i] = int_arr[i].intValue();
            }
            count++;
        }
    }
}