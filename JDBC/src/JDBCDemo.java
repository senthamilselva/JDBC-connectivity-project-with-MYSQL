import java.sql.*;
  public class JDBCDemo {
     public static void main(String[] args) throws Exception {
    	// insertRecord();
       insertvar();
    	//insertUsingpst();
    	 //delete(); 
    	//update();
    	 //batchdemo();
    	 sp();
    	 //commitdemo();
    	//readRecord();
    	 
     }
     public static void readRecord() throws Exception {
    	   String url ="jdbc:mysql://localhost:3306/senthil";
	        String username = "root";
	        String password = "Thenmozhi@123";
	        String query ="select * from employees";

	        Connection con = DriverManager.getConnection(url, username, password);
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        
	        while(rs.next())
	        {
	        System.out.println("ID is " + rs.getInt(1));
	        System.out.println("Name is " + rs.getString(2));
	        System.out.println("Salary is " + rs.getInt(3));
	        }
	        con.close();
	    }
     public static void insertRecord() throws Exception {
    	 String url ="jdbc:mysql://localhost:3306/senthil";
	        String username = "root";
	        String password = "Thenmozhi@123";
	        String query ="insert into employees values(4,'priya',250000)";

	        Connection con = DriverManager.getConnection(url, username, password);
	        Statement st = con.createStatement();
	        int row = st.executeUpdate(query);
	        
	        System.out.println("Number of rows affected:"+row);
	        con.close(); 
     }
     public static void insertvar() throws Exception {
     	 String url ="jdbc:mysql://localhost:3306/senthil";
	        String username = "root";
	        String password = "Thenmozhi@123";
	        int id=5;
	        String name="varun";
	        int salary=3000000;
	        String query ="insert into employees values("+id+",'"+name+"',"+salary+");";

	        Connection con = DriverManager.getConnection(url, username, password);
	        Statement st = con.createStatement();
	        int row = st.executeUpdate(query);
	        
	        System.out.println("Number of rows affected:"+row);
	        con.close(); 
     }
     public static void insertUsingpst() throws Exception {
     	 String url ="jdbc:mysql://localhost:3306/senthil";
	        String username = "root";
	        String password = "Thenmozhi@123";
	        int id=6;
	        String name="nila ";
	        int salary=3000000;
	        String query ="insert into employees values(?,?,?);";

	        Connection con = DriverManager.getConnection(url, username, password);
	        PreparedStatement pst=con.prepareStatement(query);
	        pst.setInt(1,id);
	        pst.setString(2,name);
	        pst.setInt(3,salary);
	        int row=pst.executeUpdate();
	        System.out.println("NUmber of rows affected:"+row);
	        con.close(); 
     } 
     public static void delete() throws Exception {
     	 String url ="jdbc:mysql://localhost:3306/senthil";
	        String username = "root";
	        String password = "Thenmozhi@123";
	        int id=5;
	        String query ="delete from employees where emp_id="+id;

	        Connection con = DriverManager.getConnection(url, username, password);
	        Statement st = con.createStatement();
	        int row = st.executeUpdate(query);
	        
	        System.out.println("Number of rows affected:"+row);
	        con.close(); 
     }
     public static void update() throws Exception {
     	 String url ="jdbc:mysql://localhost:3306/senthil";
	        String username = "root";
	        String password = "Thenmozhi@123";
	      
	        String query ="update employees set salary=800000 where emp_id=1";

	        Connection con = DriverManager.getConnection(url, username, password);
	        Statement st = con.createStatement();
	        int row = st.executeUpdate(query);
	        
	        System.out.println("Number of rows affected:"+row);
	        con.close(); 
     }
   //types of statement
     //norma,prepared,callable
     public static void sp() throws Exception {
   	   String url ="jdbc:mysql://localhost:3306/senthil";
          String username = "root";
          String password = "Thenmozhi@123";
          String query ="select * from employees";

          Connection con = DriverManager.getConnection(url, username, password);
          CallableStatement cst = con.prepareCall("{call GetEmp}");
          cst.executeQuery();
          ResultSet rs=cst.executeQuery(query); 
          while(rs.next())
	        {
	        System.out.println("ID is " + rs.getInt(1));
	        System.out.println("Name is " + rs.getString(2));
	        System.out.println("Salary is " + rs.getInt(3));
	        }
          con.close();
     }
     //calling stored procedure with input parameter
     public static void sp2() throws Exception {
     	   String url ="jdbc:mysql://localhost:3306/senthil";
            String username = "root";
            String password = "Thenmozhi@123";
            String query ="select * from employees";
            int id=5;
            Connection con = DriverManager.getConnection(url, username, password);
            CallableStatement cst = con.prepareCall("{call GetEmpById(?)}");
            cst.setInt(1,id);
            //cst.executeQuery();
            ResultSet rs=cst.executeQuery(); 
            while(rs.next())
  	        {
  	        System.out.println("ID is " + rs.getInt(1));
  	        System.out.println("Name is " + rs.getString(2));
  	        System.out.println("Salary is " + rs.getInt(3));
  	        }
            con.close();
       } 
     public static void sp3() throws Exception {
   	   String url ="jdbc:mysql://localhost:3306/senthil";
          String username = "root";
          String password = "Thenmozhi@123"; 
          int id=5;
          Connection con = DriverManager.getConnection(url, username, password);
          CallableStatement cst = con.prepareCall("{call GetNameById(?,?)}");
          cst.setInt(1,id);
          cst.registerOutParameter(2,Types.VARCHAR );
          cst.executeUpdate();
         System.out.println(cst.getString(2));
     
          con.close();
     } 
     //commit vs autocomit
     public static void commitdemo() throws Exception {
     	   String url ="jdbc:mysql://localhost:3306/senthil";
            String username = "root";
            String password = "Thenmozhi@123"; 
            String query1="update employees set salary=5000000 where emp_id=1";
            String query2="update employees set salary=5000000 where emp_id=2";
            Connection con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
            Statement st = con.createStatement();
	        int row1 = st.executeUpdate(query1);
	        System.out.println("rows affected:"+row1);
	        int row2 = st.executeUpdate(query2);
	        System.out.println("rows affected:"+row2);
	        if(row1>0&&row2>0)
	        	con.commit();
	        con.close();
	          
     }
   //batch processing
     public static void batchdemo() throws Exception {
   	   String url ="jdbc:mysql://localhost:3306/senthil";
          String username = "root";
          String password = "Thenmozhi@123"; 
          String query1="update employees set salary=5000000 where emp_id=1";
          String query2="update employees set salary=5000000 where emp_id=2";
          String query3="update employees set salary=5000000 where emp_id=3";
          String query4="update employees set salary=5000000 where emp_id=4";
          Connection con = DriverManager.getConnection(url, username, password);
          con.setAutoCommit(false);
          Statement st = con.createStatement();
          st.addBatch(query1);
          st.addBatch(query2);
          st.addBatch(query3);
          st.addBatch(query4);
          int[] res=st.executeBatch();
          for(int i:res)
          {
        	  if(i>0)
        		  continue;
        	  else
        		  con.rollback();
          }
          con.commit();
          con.close();
     }
   }
    	

