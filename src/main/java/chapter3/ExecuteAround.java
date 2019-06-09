package chapter3;


import java.io.*;

public class ExecuteAround {
    public  String processFile() throws IOException {
        try(BufferedReader br =
                new BufferedReader(new FileReader("/home/joshua/data.txt"))){

            return br.readLine();
        }
    }

    public String processFileWithRamda(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br =
                    new BufferedReader(new FileReader("/home/joshua/data.txt"))){

            return p.process(br);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor{
        String process(BufferedReader br) throws IOException;
    }
}
