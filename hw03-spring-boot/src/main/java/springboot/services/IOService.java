package springboot.services;

import java.io.IOException;

public interface IOService {
    String readFile();
    String readString() throws IOException;
}
