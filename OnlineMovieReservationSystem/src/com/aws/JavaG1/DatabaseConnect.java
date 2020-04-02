package com.aws.JavaG1;

import java.sql.*;
import java.util.ArrayList;



public class DatabaseConnect {
	
	private static Connection connect;
	private Statement statement;
	private static ResultSet result;

	private static String SELECT_MOVIES = "SELECT " + 
			"cinema_id, movie_name, movie_director, movie_rating,movie_genre " +
			"FROM movies, cinemas WHERE movies.movie_id = cinemas.movie_id " + 
			"AND movies.status = 1;";
	
	private static String SELECT_TIMESLOTS = "SELECT" +  
		    "movies.movie_id, time_start" +
		    "FROM movies, timeslots where movies.movie_id = timeslots.movie_id;";

	private static String SELECT_SEATS = "SELECT" +  
		    "seat_number" +
			"FROM seats WHERE timeslot_id = 1 AND cinema_id = 1" +
		    "AND reservation_id IS NULL;";

	private static String UPDATE_SEATS = "UPDATE seats" +  
		    "SET reservation_id = ? " +
			"WHERE cinema_id = ? and timeslot_id = ? and seat_number = ?;";
	
	private static String SELECT_RESERVATION_ID = "SELECT " +
			"MAX('reservation_id') from reservations";
	
	
	public DatabaseConnect() {
		this.connect = null;
		this.statement = null;
		this.result = null;
		
		try {
		    connect = DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?useUnicode=true&"
		    		+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
		    //System.out.println("CONN SUCCESS");
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	
	public static ResultSet selectMovies(){
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_MOVIES);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int column = rsmd.getColumnCount();
			
			for (int i = 1; i <= column; i++ ) {
				 String name = rsmd.getColumnName(i);
				 System.out.print(name + "\t");
			}
	    	System.out.println();
			
	    	while(result.next()) {
//	    		System.out.print(result.getString("cinema_id") + "\t"
//				    +    result.getString("movie_name") + "\t"
//				    +    result.getString("movie_director") + "\t"
//				    +    result.getString("movie_rating") + "\t"
//				    +    result.getString("movie_genre"));
//		    	System.out.println();
		    	
		    	
		    	//String movie = result.getString("movie_name");
				Movie movie = new Movie(result.getInt("cinema_id"),
										result.getString("movie_name"),
										result.getString("movie_director"),
										result.getString("movie_rating"),
										result.getString("movie_genre"));
		    	
				System.out.println(movie.getMovieID()+ "\t"
								 + movie.getMovieName() + "\t"
								 + movie.getMovieDirector() + "\t"
								 + movie.getMovieRating() + "\t"
								 + movie.getMovieGenre());
		    }
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
		
	}
	
	public static ResultSet selectTimeslots(){
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_MOVIES);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int column = rsmd.getColumnCount();
			
			for (int i = 1; i <= column; i++ ) {
				 String name = rsmd.getColumnName(i);
				 System.out.print(name + "\t");
			}
	    	System.out.println();
			
	    	while(result.next()) {
	    		System.out.print(result.getString("movie_id") + "\t"
				    +    result.getString("time_start"));
		    	System.out.println();
		    }
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}
	
	public static ResultSet selectSeats(){
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_SEATS);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int column = rsmd.getColumnCount();
			
			for (int i = 1; i <= column; i++ ) {
				 String name = rsmd.getColumnName(i);
				 System.out.print(name + "\t");
			}
	    	System.out.println();
			
	    	while(result.next()) {
	    		System.out.print(result.getString("seat_number"));
		    	System.out.println();
		    }
	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}
	
	//needs seat 
	public static int updateSeats(int reservation_id, int cinema_id, int timeslot_id, ArrayList<Integer> reservedSeats){
		int res = -1;
		try {
			PreparedStatement ps = connect.prepareStatement(UPDATE_SEATS);
			//dummy
			//reservation
			//cinema_id = 1;
			//timeslot_id = 1;

			ps.setInt(1,reservation_id);
			ps.setInt(2,cinema_id);
			ps.setInt(3,timeslot_id);

			for (int i = 0; i < reservedSeats.size(); i++) {
				ps.setInt(4,reservedSeats.get(0));
				ps.executeUpdate();
				System.out.println("Seats Updated Successfully");
				res = 1;
			}	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return res;
	}
	
	public static int selectReservationId(){
		int id = -1;
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_RESERVATION_ID);
			result = ps.executeQuery();
			//ResultSetMetaData rsmd = result.getMetaData();
			//int column = rsmd.getColumnCount();
			
			//for (int i = 1; i <= column; i++ ) {
			if(result.next()) {
				//id = result.getInt();
				//String temp = result.getString("MAX(reservation_id)");
				//System.out.println("Successfully retrieved id" + temp);
				//id = Integer.parseInt(temp);
				//System.out.println("Successfully retrieved id" + id);
			}
			//}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return id;
	}
	
	

	
	
	//for testing
	public static void main(String[]args){
		DatabaseConnect db = new DatabaseConnect();
		
		//db.selectMovies();
		
		//int id = db.selectReservationId();
		
		//System.out.print(id);
		
		ArrayList<Integer> rs = new ArrayList<Integer>();
		rs.add(4);
		rs.add(5);
		rs.add(6);

		//db.updateSeats(id,1,1,rs);
		
	}

}
