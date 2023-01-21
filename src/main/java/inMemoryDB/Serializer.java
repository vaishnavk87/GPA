package inMemoryDB;

import java.io.*;

// TODO: Make required implementations serializable in order to save them to a file.

public class Serializer {
    /**
     * Serialize an object to a file.
     * Precondition: object and all of its nested data members implement serializable.
     *
     * @param object   the object to serialize
     * @param filename the name of the file to store data in
     * @throws FileNotFoundException if the file is not found
     * @throws IOException           if the file cannot be written to
     */
    public static void serialize(Serializable object, String filename) throws FileNotFoundException, IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(object);
        }
    }

    /**
     * read a serialized object from a file.
     *
     * @param filename the filename to read from
     * @return the deserialized object.
     * @throws FileNotFoundException  if the file is not found
     * @throws IOException            if the file cannot be read from
     * @throws ClassNotFoundException if a serialized object could not be found.
     */
    public static Object deserialize(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return inputStream.readObject();
        }
    }
}
