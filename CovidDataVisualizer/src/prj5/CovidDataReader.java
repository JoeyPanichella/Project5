package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads and sorts the data obtained from the files
 * so that it can be organized and displayed. 
 * 
 * @author sophia rubsamen (sophiar)
 * @version 2022.04.19
 *
 */
public class CovidDataReader {
    
    private DLList<State> states;

    /**
     * @param file is the file to obtain the data from
     * @throws ParseException if the data in the file is in the 
     *         incorrect format
     * @throws FileNotFoundException if the file param is not found.
     */
    public CovidDataReader(String file) throws java.text.ParseException,
    FileNotFoundException {
        states = this.readState(file);
        GUIWindow window = new GUIWindow(states);
    }
    
    /**
     * 
     * @param file
     * @return
     */
    private DLList<State> readState(String file) {
        return states;
    }
}
