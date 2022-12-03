import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private String address;

    public CSVReader(String address) {
        this.address = address;
    }

    public List<Industry> readCSVAsIndustryList(String fileName) {
        List<Industry> industryList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(address + "/" + fileName));
            String s;
            int idx = 0;
            while ((s = br.readLine()) != null) {
                if (idx != 0) {
                    String[] sArray = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    Industry industry = new Industry();
                    industry.setYear(Integer.valueOf(sArray[0]));
                    industry.setIndustry_code(sArray[1]);
                    industry.setIndustry_name(sArray[2]);
                    industry.setRme_size_grp(sArray[3]);
                    industry.setVariable(sArray[4]);
                    industry.setValue(Integer.valueOf(sArray[5]));
                    industry.setUnit(sArray[6]);
                    industryList.add(industry);
                }
                idx++;
            }
            System.out.println("File length: "+industryList.size());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return industryList;
    }
}
