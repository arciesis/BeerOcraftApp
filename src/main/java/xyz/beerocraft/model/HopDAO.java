/**
 * @author Arcieis http://github/arciesis/BeerOcraftApp
 */
package xyz.beerocraft.model;

import javafx.collections.FXCollections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static xyz.beerocraft.model.Hop.hops;

public class HopDAO {


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


    public void searchingForHops(String letters) {

        String querry = "SELECT name FROM hops WHERE name LIKE ?";

        try (PreparedStatement pstmt = DBConnectionHandler.myConn.prepareStatement(querry)) {
            pstmt.setString(1, letters + "%");
            ResultSet rs = pstmt.executeQuery();

            Hop.searchingHops = null;
            Hop.searchingHops = FXCollections.observableArrayList();

            while (rs.next()) {
                Hop.searchingHops.add(rs.getString(1));
                System.out.println("Hops added to listView : " + rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void loadHopsToListViewFromName() {
        try {
            String query = "SELECT name FROM hops";
            Statement st = DBConnectionHandler.myConn.createStatement();
            try (ResultSet rs = st.executeQuery(query)) {

                while (rs.next()) {

                    System.out.println("Hops :" + rs.getString(1));
                    Hop.hops.add(rs.getString(1));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
