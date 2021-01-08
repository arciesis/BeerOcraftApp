package xyz.beerocraft.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class YeastDAO {


    /**
     * Method that add a hops to the db
     *
     * @param myHop the hops to add to the db
     */
    public void addHopsToDB(Hop myHop) {

        try (PreparedStatement pstmt = DBConnectionHandler.myConn.prepareStatement("INSERT INTO hops(name, alphaAcide, type) VALUES (?,?,?)")) {
            pstmt.setString(1, myHop.getName());
            pstmt.setInt(2, myHop.getAlphaAcide());
            pstmt.setString(3, myHop.getType());

            pstmt.executeUpdate();

            System.out.println("Hop have beed added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
