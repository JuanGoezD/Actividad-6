import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DeleteFriend {

    public void deleteFriend(String nameToDelete) throws IOException {
        String nameNumberString;
        String name;
        long number;
        int index;

        // Using file pointer creating the file.
        File file = new File("friendsContact.txt");

        if (!file.exists()) {
            // Create a new file if it doesn't exist.
            file.createNewFile();
        }

        // Opening the file in reading and write mode.
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        boolean found = false;

        // Checking whether the name of the contact exists.
        // getFilePointer() gives the current offset value
        // from the start of the file.
        while (raf.getFilePointer() < raf.length()) {
            // Reading a line from the file.
            nameNumberString = raf.readLine();

            // Splitting the string to get the name and number.
            String[] lineSplit = nameNumberString.split("!");

            // Separating name and number.
            name = lineSplit[0];

            // If condition to find the existence of the record.
            if (name.equals(nameToDelete)) {
                found = true;
                break;
            }
        }

        // Delete the contact if the record exists.
        if (found) {
            // Creating a temporary file with file pointer as tmpFile.
            File tmpFile = new File("temp.txt");

            // Opening this temporary file in ReadWrite Mode.
            RandomAccessFile tmpRaf = new RandomAccessFile(tmpFile, "rw");

            // Set the file pointer to start.
            raf.seek(0);

            // Traversing the friendsContact.txt file.
            while (raf.getFilePointer() < raf.length()) {
                // Reading the contact from the file.
                nameNumberString = raf.readLine();

                index = nameNumberString.indexOf('!');
                name = nameNumberString.substring(0, index);

                // Check if the fetched contact is the one to be deleted.
                if (name.equals(nameToDelete)) {
                    // Skip inserting this contact into the temporary file.
                    continue;
                }

                // Add this contact in the temporary file.
                tmpRaf.writeBytes(nameNumberString);

                // Add the line separator in the temporary file.
                tmpRaf.writeBytes(System.lineSeparator());
            }

            // The contact has been deleted now.
            // Copy the updated content from the temporary file to the original file.

            // Set both files' pointers to start.
            raf.seek(0);
            tmpRaf.seek(0);

            // Copy the contents from the temporary file to the original file.
            while (tmpRaf.getFilePointer() < tmpRaf.length()) {
                raf.writeBytes(tmpRaf.readLine());
                raf.writeBytes(System.lineSeparator());
            }

            // Set the length of the original file to that of the temporary file.
            raf.setLength(tmpRaf.length());

            // Closing the resources.
            tmpRaf.close();
            raf.close();

            // Deleting the temporary file.
            tmpFile.delete();

            System.out.println("Friend deleted.");
        } else {
            // Closing the resources.
            raf.close();

            // Print the message.
            System.out.println("Input name does not exist.");
        }
    }
}
