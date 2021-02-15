package student.adventure;

import java.io.IOException;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
        Layout layout = gson.fromJson(reader, Layout.class);
        Room startingRoom = layout.getRooms().get(0);
        Scanner scan = new Scanner(System.in);
    }
}
