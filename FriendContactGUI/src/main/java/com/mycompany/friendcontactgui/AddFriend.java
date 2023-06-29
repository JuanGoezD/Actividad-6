import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddFriend {
    public void addFriend(String newName, long newNumber) {
        try {
            String name;
            long number;

            // Using file pointer creating the file.
            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                // Create a new file if it doesn't exist.
                file.createNewFile();
            }

            // Opening file in reading and write mode.
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Checking whether the name of contact already exists.
            while (raf.getFilePointer() < raf.length()) {
                // Reading line from the file.
                String nameNumberString = raf.readLine();
                // Splitting the string to get name and number.
                String[] lineSplit = nameNumberString.split("!");
                // Separating name and number.
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                // If condition to find existence of record.
                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                // Enter this block when a record is not already present in the file.
                String nameNumberString = newName + "!" + String.valueOf(newNumber);
                // Write the string as a sequence of bytes.
                raf.writeBytes(nameNumberString);
                // Insert a new line for the next record.
                raf.writeBytes(System.lineSeparator());

                // Print the message.
                System.out.println("Friend added.");

                // Closing the resources.
                raf.close();
            } else {
                // The contact to be added already exists.
                // Closing the resources.
                raf.close();
                // Print the message.
                System.out.println("Input name or number already exists.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
    }
}