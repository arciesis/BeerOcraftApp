package xyz.beerocraft.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class YeastDAO {


    /**
     * Method that add a yeast to the db
     *
     * @param myYeast the yeasts to add to the db
     */
    public static void addYeastToDB(Yeast myYeast) {
        try (PreparedStatement pstmt = DBConnectionHandler.myConn.prepareStatement("INSERT INTO yeasts(name, tempMin, tempMax, attenuation) VALUES (?,?,?,?)")) {
            pstmt.setString(1, myYeast.getName());
            pstmt.setInt(2, myYeast.getTempMin());
            pstmt.setInt(3, myYeast.getTempMax());
            pstmt.setInt(4, myYeast.getApparentAttenuation());

            pstmt.executeUpdate();

            System.out.println("Yeast have beed added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
