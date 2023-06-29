import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class DisplayFriends {

    public String getFriendsInfo() {
        StringBuilder friendsInfo = new StringBuilder();

        try {
            String nameNumberString;
            String name;
            long number;
            int index;

            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
                friendsInfo.append("Friend Name: ").append(name).append("\n")
                           .append("Contact Number: ").append(number).append("\n\n");
            }

            raf.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }

        return friendsInfo.toString();
    }
}