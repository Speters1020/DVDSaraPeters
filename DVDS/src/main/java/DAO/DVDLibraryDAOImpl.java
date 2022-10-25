package DAO;

import DTO.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDAOImpl implements DVDLibraryDao {

    public final String DVD_FILE = "dvd.txt";

    public static final String DELIMiTER = "::";
    /*
    Hash map to store and retrieve the dvd with title name
     */
    private Map<String, DVD> dvds= new HashMap<>();

        //public DVDLibraryDAOImpl(){DVD_FILE = "dvd.txt";}
        //public DVDLibraryDAOImpl(String LibraryTextFile){DVD_FILE = LibraryTextFile;}

    @Override
    public DVD addDvd(String title, DVD dvd) throws DvdLibraryDaoException {
            loadDvdFile();
            DVD prevDvd = dvds.put(title, dvd);
            writeDvdFile();
            return prevDvd;

    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryDaoException{
            loadDvdFile();
            return new ArrayList<DVD>(dvds.values());
    }


    @Override
    public DVD RemoveDvd(String title) throws DvdLibraryDaoException {
            loadDvdFile();
            DVD removedDvd = dvds.remove(title);
            writeDvdFile();
        return removedDvd;
    }

    /*
    This method is quite simple - we just askl the dvds map for the dvd object with
    the given title and return it
     */
    @Override
    public DVD getDvd(String title)throws DvdLibraryDaoException {
        loadDvdFile();
        return dvds.get(title);
    }
    /*
    Method to unmarshall the object or read a line of
    string from the line and convert it into an object
     */
    private DVD unmarshallDvd(String dvdAsText){
        String [] dvdTokens = dvdAsText.split((DELIMiTER));
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD (title);

        //index 1
        dvdFromFile.setTitle(dvdTokens[0]);

        //index 2
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        //index 3
        dvdFromFile.setMPAA(dvdTokens[2]);

        //index 4
        dvdFromFile.setStudio(dvdTokens[3]);


        dvdFromFile.setUserRating(dvdTokens[4]);

        //index 6
        dvdFromFile.setDirectorName(dvdTokens[5]);

        //index 5

        return dvdFromFile;

    }

    /*
    Method to Load file DVD_FILE into memory
     */
    private void loadDvdFile() throws DvdLibraryDaoException{
        Scanner scanner;

        try{
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        DVD currentDvd;

        while(scanner.hasNextLine()) {
            currentLine=scanner.nextLine();
            currentDvd= unmarshallDvd(currentLine);

            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    private String marshallDvd(DVD aDVD){
        String dvdAsText= aDVD.getTitle()+DELIMiTER;

        dvdAsText += aDVD.getReleaseDate() + DELIMiTER;
        dvdAsText += aDVD.getMPAA() + DELIMiTER;
        dvdAsText += aDVD.getStudio() + DELIMiTER;
        dvdAsText += aDVD.getUserRating() + DELIMiTER;
        dvdAsText += aDVD.getDirectorName();
        return dvdAsText;
    }

    private void writeDvdFile() throws DvdLibraryDaoException{
        PrintWriter out;
        try{
            out = new PrintWriter((new FileWriter((DVD_FILE))));
        }catch (IOException e){
            throw new DvdLibraryDaoException("Could not save student data.", e);
        }
        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for(DVD currentDvd : dvdList){
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public DVD editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException{
        loadDvdFile();
        DVD currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DVD editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadDvdFile();
        DVD currentDVD = dvds.get(title);
        currentDVD.setMPAA(newMpaaRating);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DVD editDirectorName(String title, String newDriectorName) throws DvdLibraryDaoException{
        loadDvdFile();
        DVD currentDVD = dvds.get(title);
        currentDVD.setDirectorName(newDriectorName);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DVD editUserRating(String title, String newUserRating) throws DvdLibraryDaoException{
        loadDvdFile();
        DVD currentDVD = dvds.get(title);
        currentDVD.setUserRating(newUserRating);
        writeDvdFile();
        return currentDVD;
    }

    @Override
    public DVD editStudio(String title, String newStudentName) throws DvdLibraryDaoException{
        loadDvdFile();
        DVD currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudentName);
        writeDvdFile();
        return currentDVD;
    }

}
