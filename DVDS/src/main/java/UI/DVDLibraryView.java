package UI;

import DTO.DVD;

import java.util.List;

public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {this.io=io;}

    public DVDLibraryView() {

    }

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List Dvds");
        io.print("2. Create new DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit Dvd");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices", 1, 6);
    }
    public int printEditMenuAndGetSelection(){
        io.print("Edit Release Date");
        io.print("Edit MPAA");
        io.print("Edit Director Name");
        io.print("Edit User Rating");
        io.print("Studio Name");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    /*
    This method prompts the user for DVD ID, First Name, Last Name, and Cohort gathers this information, creates a new dvd object
    and returns it to the caller.
     */
    public DVD getNewDvdInfo(){
        String title = io.readString("Please enter the DVD title");
        String ReleaseDate = io.readString("Please enter the DVD release date");
        String MPAA = io.readString("Please enter the MPAA rating");
        String DirectorName = io.readString("Please enter the director name");
        String Studio= io.readString("Please enter the studio name");
        String UserRating = io.readString("Please enter user rating");

        DVD currentDVD = new DVD (title);
        currentDVD.setReleaseDate(ReleaseDate);
        currentDVD.setMPAA(MPAA);
        currentDVD.setDirectorName(DirectorName);
        currentDVD.setStudio(Studio);
        currentDVD.setUserRating(UserRating);
        return currentDVD;
    }
    /*
    This method simply displays a banner or heading to the UI indicating that the next interactions on the screen will be for creating a newDVD
     */

    public void displayCreateDvDBanner(){
        io.print("=== Create DvD ===");}
    /*
    second method displays dvd was successfully created and waits for user to press enter
     */
    public void displayCreateSuccessBanner(){
        io.readString(
                "DvD successfully created. Please hit enter to continue");

    }
    /* A method that takes a list of DVD objects as a parameter and displaus the information for each dvd to the screen

     */
    public void displayDvDList(List<DVD> dvdList){
        for(DVD currentDVD : dvdList){
            String dvdInfo = String.format("%s : %s %s %s %s %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMPAA(),
                    currentDVD.getDirectorName(),
                    currentDVD.getStudio(),
                    currentDVD.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue");

    }
    public void displayDisplayAllBanner(){io.print("=== Display All DvDs ===");}
    /*
    Get the dvd title to display information
     */
    public String getDvdTitleChoice(){return io.readString("Please enter the dvd title.");}

    public void displayDvd(DVD dvd){
    if(dvd != null){
        io.print(dvd.getTitle());
        io.print(dvd.getMPAA());
        io.print(dvd.getDirectorName());
        io.print(dvd.getReleaseDate());
        io.print(dvd.getUserRating());
        io.print(dvd.getStudio());
        io.print(" ");
    }else {
        io.print("No such dvd");
    }
    io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner(){io.print("=== Remove Dvd ===");}

    public void displayRemoveResultDvd(DVD dvdRecord){
        if(dvdRecord != null){
            io.print("Dvd successfully removed.");
        }else{
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue");
    }


    public void displayExitBanner(){ io.print("Good Bye!!!");}

    public void displayUnknownCommandBanner(){ io.print("Unknown Command!!!");}

    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    public void displayEditDvDBanner(){io.print("=== Edit DVD ===");}

    public void displayEditDvDSuccess(){
        io.readString(
                "DVD Successfully edited. Please hit enter to continue");
    }
    public void displayEditReleaseDateBanner(){ io.print("=== Edit DVD Release Date ===");}

    public void displayEditMpaaBanner(){io.print("=== Edit DVD MPAA rating ===");}

    public void displayEditDirectorNameBanner(){io.print("=== Edit DVD Director's Name ===");}

    public void displayEditStudio(){io.print("=== Edit DVD Studio ===");}

    public void displayEditUserRating(){io.print("=== Edit DVD User Rating ===");}

    public String getNewReleaseDate(){return io.readString("Please enter new release date.");}

    public String getNewMpaaRating(){return io.readString("Please enter new MPAA rating.");}

    public String getNewDirectorName(){ return io.readString("Please enter new director name.");}

    public String getNewUserRating(){return io.readString("Please enter new user rating.");}

    public String getNewStudio(){return io.readString("Please enter new studio.");}

    public void displayNullDVD(){
        io.print("No such DVD");
        io.readString("Please hit enter to continue.");
    }
}
