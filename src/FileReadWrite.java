import java.io.*;
import java.text.Collator;
import java.util.Collections;
import java.util.List;

public class FileReadWrite<T> {
    private String address;
    T input;

    public FileReadWrite(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void writeFile(List<T> inputList, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(this.address + "/" + fileName));
            for (T input : inputList) {
                bw.write(input + "\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void duplicateFile(String oldFileName, String newName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.address + "/" + oldFileName));
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.address + "/" + newName));
            String s;
            while ((s = br.readLine()) != null) {
                bw.write(s + "\n");
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> sortIntegerList(List<Integer> integerList) {
        Collections.sort(integerList);
        return integerList;
    }

    public List<String> sortList(List<String> stringList) {
        Collections.sort(stringList, Collator.getInstance());
        return stringList;
    }

    public void readFile(String name) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.address + "/" + name));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
