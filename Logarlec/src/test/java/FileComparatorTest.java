import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileComparatorTest {

    @ParameterizedTest
    @MethodSource("provideTestFilePaths")
    public void testCompareFiles(String file1Path, String file2Path) {
        try {
            String content1 = readFileContent(file1Path);
            String content2 = readFileContent(file2Path);

            assertEquals(content1, content2, "A két fájl tartalma nem egyezik meg.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFileContent(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append("\n");
            }
        }
        return contentBuilder.toString();
    }

    private static Stream<Arguments> provideTestFilePaths() throws IOException {
        Map<String,String> filePaths = new HashMap<>();

        Files.walk(Paths.get("tests")).forEach(folderPath -> {
            if (Files.isDirectory(folderPath)) {
                String folderName = folderPath.getFileName().toString();
                    int i = 1;
                final String[] assrt = {null};
                final String[] out = {null};
                    while(folderName.equals("test_"+i)) {
                        try {
                            int finalI = i;
                            Files.walk(folderPath).forEach(filePath -> {
                                String fileName = filePath.getFileName().toString();
                                if (Files.isRegularFile(filePath)) {
                                    if (fileName.equals("assert.txt"))
                                        assrt[0] = folderPath.resolve(fileName).toString();
                                    if (fileName.equals("out.txt"))
                                        out[0] =  folderPath.resolve(fileName).toString();
                                }
                            });
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        filePaths.put(assrt[0], out[0]);
                        i++;
                }
            }
        });
        return filePaths.entrySet().stream().map((entry) -> Arguments.of(entry.getKey(), entry.getValue()));
    }
}