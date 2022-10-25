import Controller.DVDLibraryController;
import DAO.DVDLibraryDAOImpl;
import DAO.DVDLibraryDao;
import UI.DVDLibraryView;
import UI.UserIO;
import UI.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(io);
        DVDLibraryDao myDao = new DVDLibraryDAOImpl();
        DVDLibraryController controller = new DVDLibraryController( myView, myDao);
        controller.run();
    }
}
