import exceptions.InvalidInputException;
import utils.StringUtils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by paanir on 11/16/17.
 */
public class Driver {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int k = Integer.parseInt(stdin.nextLine());

        Tree tree = Tree.initialize(k);

        while (stdin.hasNextLine()) {
            String line = stdin.nextLine();
            if (StringUtils.isInsert(line)) {
                BPair pair = StringUtils.getInsertPair(line);
                tree.insert(pair.getKey(), pair.getValue());
            } else if (StringUtils.isSearch(line)) {
                double key = StringUtils.getSearchKey(line);
                List<String> values = tree.search(key);
                System.out.println(String.join(",", values));
            } else if (StringUtils.isSearchRange(line)) {
                double[] range = StringUtils.getSearchRange(line);
                List<String> bEntries = tree.search(range[0], range[1]).stream().map(BEntry::toString).collect(Collectors.toList());
                System.out.println(String.join(",", bEntries));
            } else
                throw new InvalidInputException(line);
        }
    }
}
