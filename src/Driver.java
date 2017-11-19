import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by paanir on 11/16/17.
 */
public class Driver {
    public static void main(String[] args) {

        final String INPUT_FILENAME = "/Users/paanir/B+Tree/input6.txt";
        final String OUTPUT_FILENAME = "output_file.txt";
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(INPUT_FILENAME);
            br = new BufferedReader(fr);
            List<String> outputLines = new ArrayList<>();

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
                    outputLines.add(String.join(",", values));

                } else if (StringUtils.isSearchRange(line)) { //search range
                    double[] range = StringUtils.getSearchRange(line);
                    List<String> bEntries = tree.search(range[0], range[1])
                            .stream()
                            .map(BEntry::toString)
                            .collect(Collectors.toList());
                    outputLines.add(String.join(",", bEntries));
                } else
                    throw new InvalidInputException(line + " is not a valid input");
            }

            Path file = Paths.get(OUTPUT_FILENAME);
            Files.write(file, outputLines, Charset.forName("UTF-8"));

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
