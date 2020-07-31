package Package;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IsThatAlive {
    public static void main(String[] args) throws IOException {

        String urls;

        FileReader reader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        String data;

        while ((urls = bufferedReader.readLine()) != null) {
            URL url = null;
            try {
                url = new URL(urls);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                connection.setConnectTimeout(5000);
                data = (url.getHost() + "\t" + connection.getResponseCode() + "\n");
                System.out.println(url.getHost() + "\t" + connection.getResponseCode());
                bufferedWriter.write(data);

            } catch (UnknownHostException e) {
                System.err.println(url.getHost() + "\t" + "Timeout");
                bufferedWriter.write(url.getHost() + "\t" + "Timeout" + "\n");
            } catch (IOException e) {
                System.err.println(url.getHost() + "\t" + "Check");
                bufferedWriter.write(url.getHost() + "\t" + "Check" + "\n");
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=========================");
        System.out.println("Check output.txt");
        System.out.println("Press Enter to finish...");
        scanner.nextLine();
        scanner.close();
        reader.close();
        bufferedWriter.close();
    }

}
