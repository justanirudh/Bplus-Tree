import exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by paanir on 11/16/17.
 */
public class Driver {
    public static void main(String[] args) {

        final String FILENAME = "/Users/paanir/B+Tree/input.txt";
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            int k = Integer.parseInt(br.readLine().trim());
            Tree tree = new Tree(k);

            String line;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isInsert(line)) { //insert
                    BPair pair = StringUtils.getInsertPair(line);
                    tree.insert(pair.getKey(), pair.getValue());
                } else if (StringUtils.isSearch(line)) { //search
                    double key = StringUtils.getSearchKey(line);
                    List<String> values = tree.search(key);
                    System.out.println(String.join(",", values));
                } else if (StringUtils.isSearchRange(line)) { //search range
                    double[] range = StringUtils.getSearchRange(line);
                    List<String> bEntries = tree.search(range[0], range[1])
                            .stream()
                            .map(BEntry::toString)
                            .collect(Collectors.toList());
                    System.out.println(String.join(",", bEntries));
                } else
                    throw new InvalidInputException(line + " is not a valid input");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
