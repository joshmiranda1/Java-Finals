import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader 
{
    // Makes an array list for csv file 
    public static ArrayList<Library> readData(String filename)
    {
        ArrayList<Library> dataList = new ArrayList<>();

        File dataFile = new File(filename);

        try(Scanner fileScanner = new Scanner(dataFile))
        {
            ArrayList<String> lines = new ArrayList<>();

            while (fileScanner.hasNextLine())
            {
                lines.add(fileScanner.nextLine());
            }
            
            for (int index = 1; index < lines.size(); index++)
            {
                String line = lines.get(index);

                String [] data = line.split(",");

                String name = data[0];
                String authr = data[1];
                int page = Integer.parseInt(data[2]);
                String publishr = data[3];
                String genre = data[4];
               
                Library model = new Library(name, authr, page, publishr, genre);

                dataList.add(model);
            }
        }
        catch(FileNotFoundException error)
        {
            handleError(error);
        }
        return dataList;
    }

    public static void handleError(Exception error)
    {
        System.out.println("ERROR DETECTED: ");
        System.out.println(error.getMessage());
    }
}
