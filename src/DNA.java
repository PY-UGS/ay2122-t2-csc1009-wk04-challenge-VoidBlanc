import java.io.*;

public class DNA {
    public static void main(String[] args) throws IOException {
        File current = new File (".");
        String sequence01fp = current.getCanonicalPath()+  "/src/sample01";
        String sequence02fp = current.getCanonicalPath()+  "/src/sample02";

        System.out.print("Output 01: ");
        countGTC(sequence01fp);

        System.out.print("Output 02: ");
        countGTC(sequence02fp);
    }

    public static void countGTC(String filePath) throws IOException{
        FileInputStream inputStream = new FileInputStream(filePath);

        BufferedInputStream bis = new BufferedInputStream(inputStream);
        char fprev = '-';
        char prev = '-';
        int gtc_count = 0;
        while (bis.available() > 0) {
            char c = (char) bis.read();
            if (c == 'G'){
                prev = c;
            }else{
                if (c == 'T'){
                    if(prev == 'G'){
                        // System.out.println("Res1 : " + prev + " : " + c);
                        // Set G to be the first previous
                        fprev = prev;

                        // Set T to be  the previous
                        prev  = c;
                    }else{
                        // If not G reset prev
                        prev = '-';
                    }
                }else if (c == 'C'){
                    if (fprev == 'G' && prev == 'T') {
                        // System.out.println("Res2 : " + fprev + " : " + prev+ " : " + c);
                        // Set T to  be first Previous
                        fprev = prev;

                        // Set C to be previous;
                        prev  = c;

                        // Increment counter for gtc since this is the occurrence of GTC
                        gtc_count++;
                    }else{
                        prev = '-';
                    }

                }
                else{
                    prev = '-';
                }
            }
        }
        if (gtc_count <= 10) {
            System.out.println("sequence \"GTC\" appeared : " + gtc_count);
        } else {
            System.out.println("sequence \"GTC\" appeared : " + gtc_count+ "; infection chance is high");
        }
    }
}
