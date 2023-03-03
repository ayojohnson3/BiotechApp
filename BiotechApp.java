/*

Project Name: IRTProject

Project Description: The IRTProject is a simple Java project 
that uses SQL to showcase how to manage data for an 
Interactive Response Technology (IRT) system. The project includes 
a database schema that defines the tables required 
to store information about study participants and their responses to IRT system prompts.

The Java application connects to the database, inserts sample data, 
and queries the data to display it to the user.

Database Schema: (Below)

*/

CREATE TABLE Participants (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE IRTResponses (
    id INT PRIMARY KEY,
    participant_id INT,
    prompt_id INT,
    response VARCHAR(255),
    FOREIGN KEY (participant_id) REFERENCES Participants(id)
);


import java.sql.*;

public class IRTProject {
    public static void main(String[] args) {
        //These two var will connect to the db and excel sql statements
        Connection conn = null;
        Statement stmt = null;
        //try-catch block to handle any exceptions that may occur during the program's execution.
        try {
            // Connect to the database - Class.forName method and creates a connection to the database using the "DriverManager.getConnection()" method.
            // This isn't connected
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/irt_db", "root", "");

            // Insert sample data
            //Implement a SQL statement using the "conn.createStatement()" method and inserts sample data into the 
            //"Participants" and "IRTResponses" tables using the "stmt.executeUpdate()" method.
            stmt = conn.createStatement();
            String sql = "INSERT INTO Participants (id, name, email) VALUES (1, 'John Smith', 'john.smith@example.com')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO IRTResponses (id, participant_id, prompt_id, response) VALUES (1, 1, 1, 'Yes')";
            stmt.executeUpdate(sql);

            //Query the data
            //Implement another SQL statement and executes a SELECT query that joins the "Participants" and "IRTResponses" 
            //tables using the "stmt.executeQuery()" method.
            sql = "SELECT Participants.name, IRTResponses.prompt_id, IRTResponses.response FROM Participants INNER JOIN IRTResponses ON Participants.id = IRTResponses.participant_id";
            ResultSet rs = stmt.executeQuery(sql);

            // Display the results
            //The code retrieves the results of the query using the "rs.next()" method to iterate over the rows in the result set and the "rs.getString()" 
            //and "rs.getInt()" methods to retrieve the values of specific columns.
            while (rs.next()) {
                String name = rs.getString("name");
                int prompt_id = rs.getInt("prompt_id");
                String response = rs.getString("response");
                System.out.println("Name: " + name + ", Prompt ID: " + prompt_id + ", Response: " + response);
            }

            // Clean up resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Output below assuming database is connect and data is in the databas 
//Name: John Smith, Prompt ID: 1, Response: Yes
