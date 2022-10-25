package Controller;

import DAO.DVDLibraryDAOImpl;
import DAO.DVDLibraryDao;
import DAO.DvdLibraryDaoException;
import DTO.DVD;
import UI.DVDLibraryView;
import UI.UserIO;
import UI.UserIOConsoleImpl;

import javax.swing.text.View;
import java.util.List;

public class DVDLibraryController {


    DVDLibraryView view = new DVDLibraryView();
    DVDLibraryDao dao = new DVDLibraryDAOImpl();
    UserIO io = new UserIOConsoleImpl();

/*
not in Class roster: below
 */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao){
        //1st data type= 2nd one variable name
        this.view = view;
        this.dao = dao;
    }



    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }
            }
        }catch (DvdLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();

    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


    private void createDvd() throws DvdLibraryDaoException{
        view.displayCreateDvDBanner();
        DVD newDVD = view.getNewDvdInfo();
        dao.addDvd(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
     /*
     a method called listDvds that will get a list of all Dvd objects in
    the system from the DAO and then hand that list to the view to display to the user.
     */
    private void listDvds() throws DvdLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDvDList(dvdList);
    }
     /*
    This method asks the view to display the View dvd banner and get the titlr from the user
     */
    private void viewDvd() throws DvdLibraryDaoException {
        //prompt user for input title save as userInput
        String userInput = io.readString("Please enter dvd title");
        //access data from dao
        DVD returnInfo = dao.getDvd(userInput);
        view.displayDvd(returnInfo);
    }
    /*
    This method will ask the view to display the Remove dvd banner and ask the user for the title of the dvd to be removed
     */
    private void removeDvd() throws DvdLibraryDaoException{
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD removedDvd = dao.RemoveDvd(title);
        view.displayRemoveResultDvd(removedDvd);
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void editDvd() throws DvdLibraryDaoException{
        view.displayEditDvDBanner();
        String title = io.readString("Please enter dvd title.");
        DVD currentDVD = dao.getDvd(title);
        if(currentDVD == null){
            view.displayNullDVD();
        }else {
            view.displayDvDList((List<DVD>) currentDVD);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing){
                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection){
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMPAA(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false){
                    break;
                }
            }
        }
    }
    private int getEditMenuSelection(){ return view.printEditMenuAndGetSelection();}

    private void editReleaseDate(String title) throws DvdLibraryDaoException{
        view.displayEditReleaseDateBanner();
        String newReleaseDate = view.getNewReleaseDate();
        dao.editReleaseDate(title, newReleaseDate);
        view.displayEditDvDSuccess();
    }

    private void editMPAA(String title) throws DvdLibraryDaoException{
        view.displayEditMpaaBanner();
        String newMpaaRating = view.getNewMpaaRating();
        dao.editMPAA(title, newMpaaRating);
        view.displayEditDvDSuccess();
    }

    private void editDirectorName(String title) throws DvdLibraryDaoException{
        view.displayEditDirectorNameBanner();
        String newDirectorName= view.getNewDirectorName();
        dao.editDirectorName(title, newDirectorName);
        view.displayEditDvDSuccess();
    }

    private void editUserRating(String title) throws DvdLibraryDaoException{
        view.displayEditUserRating();
        String newUserRating = view.getNewUserRating();
        dao.editUserRating(title, newUserRating);
        view.displayEditDvDSuccess();
    }
    private void editStudioName(String title) throws DvdLibraryDaoException{
        view.displayEditStudio();
        String newStudioName = view.getNewStudio();
        dao.editStudio(title, newStudioName);
        view.displayEditDvDSuccess();
    }

    private void exitMessage(){view.displayExitBanner();}
}
