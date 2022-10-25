package DAO;

import DTO.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDvd(String title, DVD dvd) throws DvdLibraryDaoException;

    List<DVD> getAllDvds() throws DvdLibraryDaoException;

    DVD getDvd(String title) throws DvdLibraryDaoException;

    DVD RemoveDvd(String title) throws DvdLibraryDaoException;

    DVD editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException;

    DVD editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException;

    DVD editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException;

    DVD editUserRating(String title, String newUserRating) throws DvdLibraryDaoException;

    DVD editStudio(String title, String newStudioName) throws DvdLibraryDaoException;


}
