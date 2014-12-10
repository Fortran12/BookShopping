package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import constant.QueryConstant;
import util.DbConfigurationUtil;
import vo.successVO;
import vo.viewVO;

public class viewDAO {
        public static final Logger LOG = Logger.getLogger("ViewDAO");
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4=null;//latest
        ResultSet rs5=null;//latest
        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        Statement stmt4 = null;

        PreparedStatement ps1;
        PreparedStatement ps2;
        PreparedStatement ps3;
        PreparedStatement ps4;
        PreparedStatement ps5;
        PreparedStatement ps6;
        PreparedStatement ps7;
        PreparedStatement ps8;//latest
        PreparedStatement ps9;//latest
        PreparedStatement ps10;//latest
        Connection conn = null;
        String query = "";
        String query1 = "";
        String query2 = "";
        String query3 = "";
        String query4 = "";
        String query5 = "";
        String query6="";//latest
        String query7="";//latest
        int qty1 = 0;
        int p=0;
        int quantity = 0;
        int a=0;
        int new_avail=0;
        int avail=0;
        int needed=0;
        viewVO vo = new viewVO();
       

        public void connection() {
                try {
                        // System.out.println("inside connection");
                        conn = DbConfigurationUtil.getConnection();
                        stmt = conn.createStatement();
                        // System.out.println("connec estab");
                        LOG.info("ViewDAO : Connection Established");
                } catch (Exception e) {
                        LOG.error("ViewDAO : Exception"+e);
                        e.printStackTrace();
                }
        }

        public ArrayList<successVO> viewQ(String[] books, String[] quantity,int length,int cart_id) {
                connection();
                int cid =cart_id;
              ArrayList<successVO> list=new ArrayList<successVO>();

                try {
                       stmt1 = conn.createStatement();
                       for(int i=0;i<length;i++) {
                    	   
                    	   successVO svo = new successVO();
                    	   
                    	     ps1 = conn.prepareStatement(QueryConstant.SELECT_AVAILABILITY);
                    	     ps1.setInt(1,Integer.parseInt(books[i]));
                    	     rs1 =  ps1.executeQuery();
                    	     rs1.next();
                    	     avail = rs1.getInt(1);
                    	    
                    	     ps1 = conn.prepareStatement(QueryConstant.SELECT_QUANTITY_NEEDED);
                    	     ps1.setInt(1,Integer.parseInt(books[i]));
                    	     rs1 = ps1.executeQuery();
                    	     rs1.next();
                    	     needed = rs1.getInt(1);
                    	    
                    	     if(Integer.parseInt(quantity[i]) > needed) {
                    		    int difference = Integer.parseInt(quantity[i]) - needed;
                    		    int newAvail = avail - difference;
                    		    ps1 = conn.prepareStatement(QueryConstant.UPDATE_AVAILABILITY);
                    		    ps1.setInt(1, newAvail);
                    		    ps1.setInt(2,Integer.parseInt(books[i]));
                    		    ps1.executeUpdate();
                    		     
                    		    /*Setting in svo */
                    		/*
                    		 	ps1 = conn.prepareStatement(QueryConstant.SELECT_AVAILABILITY);
                       	     	ps1.setInt(1,Integer.parseInt(books[i]));
                       	     	rs1 =  ps1.executeQuery();
                       	     	rs1.next();
                       	     	avail = rs1.getInt(1);
                       	     	svo.setAvailability(avail);
                       	    */
                       	     	
                    		    ps1 = conn.prepareStatement(QueryConstant.UPDATE_QUANTITY_NEEDED);
                    		    ps1.setInt(1,Integer.parseInt(quantity[i]) );
                    		    ps1.setInt(2,Integer.parseInt(books[i]));
                    		    ps1.executeUpdate();
                    		   
                    		    /*Setting in svo */
                        		/*
                        	 ps1 = conn.prepareStatement(QueryConstant.SELECT_QUANTITY_NEEDED);
                    	     ps1.setInt(1,Integer.parseInt(books[i]));
                    	     rs1 = ps1.executeQuery();
                    	     rs1.next();
                    	     needed = rs1.getInt(1);
                    	     svo.setQty(needed);                        		 	
                           	  */
                    		    
                    		    
                    	   }
                    	   
                    	   else if(Integer.parseInt(quantity[i]) < needed)
                    	   {
                    		   int diff=needed-Integer.parseInt(quantity[i]);
                    		   int newavail1=avail+diff;
                    		   ps1 = conn.prepareStatement(QueryConstant.UPDATE_AVAILABILITY);
                    		   ps1.setInt(1, newavail1);
                    		   ps1.setInt(2,Integer.parseInt(books[i]));
                    		   ps1.executeUpdate();
          
                    		   /*
                    		   	ps1 = conn.prepareStatement(QueryConstant.SELECT_AVAILABILITY);
                      	     	ps1.setInt(1,Integer.parseInt(books[i]));
                      	     	rs1 =  ps1.executeQuery();
                      	     	rs1.next();
                      	     	newavail1 = rs1.getInt(1) + diff ;
                      	     	svo.setAvailability(newavail1);
                      	     	
                      	     	*/ ps1 = conn.prepareStatement(QueryConstant.UPDATE_QUANTITY_NEEDED);
                    		    ps1.setInt(1,Integer.parseInt(quantity[i]) );
                    		    ps1.setInt(2,Integer.parseInt(books[i]));
                    		    ps1.executeUpdate();
                    		   
                    		   
                    	   }
                    	   
                    	     ps1 = conn.prepareStatement("select book_name from book_details where book_id=?");
                    	     ps1.setInt(1,Integer.parseInt(books[i]));
                    	     rs1=ps1.executeQuery();
                    	     rs1.next();
                    	     
                    	    svo.setBook_name(rs1.getString(1));
                    	    svo.setQty(Integer.parseInt(quantity[i]));
                    	    list.add(svo);
                       }
                  
                       

                       /* if (qty > avail) {
                                // Qty > Avail
                                ps1=conn.prepareStatement(QueryConstant.UPDATE_BOOK_QUERY1);
                                ps1.setInt(1,b_id);
                                // updates book_details and sets avail=0 for next user
                                ps1.executeUpdate();
                                LOG.info("ViewDAO : Updates book_details and sets avail=0 for next user");

                                ps2=conn.prepareStatement(QueryConstant.UPDATE_GET_BOOKNAME);
                                ps2.setInt(1,b_id);
                                // retrieves book_name for that book_id
                                rs3 = ps2.executeQuery();
                                LOG.info("ViewDAO : Retrieves book_name for that book_id");

                                if (rs3.next()) {
                                        svo.setBook_name(rs3.getString(1));
                                        System.out.println("Book name : " + rs3.getString(1));
                                }
                                svo.setBook_id(b_id + "");
                                // sets book_name in svo (updation)

                                System.out.println("needed qty if qty > avail :"+qty);

                                ps8=conn.prepareStatement("select quantity_needed from cart_details where book_id=?");
                                ps8.setInt(1, b_id);
                                rs4=ps8.executeQuery();

                                if(rs4.next())
                                {
                                        a=rs4.getInt(1);
                                        System.out.println("diff in avail "+(a-qty));

                                        svo.setQty(a-qty);
                                }

                                System.out.println("update avail in book_details");

                                ps9=conn.prepareStatement("select availability from book_details where book_id=?");
                                ps9.setInt(1,b_id);
                                rs5=ps9.executeQuery();
                                if(rs5.next())
                                {
                                        p=rs5.getInt(1);
                                        new_avail=(p + (a-qty));
                                        System.out.println("updated avail in availbty after editing : "+new_avail);
                                }

                                ps10=conn.prepareStatement("update book_details set availability=? where book_id =?");
                                ps10.setInt(1,new_avail);
                                ps10.setInt(2,b_id);
                                ps10.executeUpdate();

                                }*/
                               /* if(qty<avail) {
                                // qty<avail
                                int a = avail - qty;
                                quantity = qty;

                                ps3=conn.prepareStatement(QueryConstant.UPDATE_BOOK_QUERY2);
                                ps3.setInt(1,a);
                                ps3.setInt(2,b_id);

                                // updates the difference as avail since user has updated
                                ps3.executeUpdate();
                                LOG.info("ViewDAO : Updates the difference as avail since user has updated");

                                ps4=conn.prepareStatement(QueryConstant.UPDATE_CART);
                                ps4.setInt(1,qty);
                                ps4.setInt(2, b_id);

                                // updates cart_Details
                                ps4.executeUpdate();
                                LOG.info("ViewDAO : Updates cart_Details");

                                ps5=conn.prepareStatement(QueryConstant.UPDATE_GET_BOOKNAME);
                                ps5.setInt(1,b_id);

                                // retrieves book_name for that book_id
                                rs2 = ps5.executeQuery();
                                LOG.info("ViewDAO : Retrieves book_name for that book_id");

                                if (rs2.next()) {
                                        svo.setBook_name(rs2.getString(1));
                                        // sets book_name in svo (updation)
                                }

                                svo.setBook_id(b_id + "");
                                svo.setQty(qty);

                                }
                                else
                                {
                                        svo.setAvailability(p);

                                }
*/
                } catch (SQLException e) {
                        LOG.error("ViewDAO : SQL Exception "+e);
                        e.printStackTrace();
                }
                LOG.info("ViewDAO : Book Details "+list);
                return list;
        }

        public ArrayList viewBook(int cart_id) {
                connection();
                // recieves cartid from bo
                ArrayList<viewVO> l = new ArrayList<viewVO>();
                try {
                        // joins 2 tables based on book_id and retrieves details

                        ps6=conn.prepareStatement(QueryConstant.GET_BOOK_DETAIL);
                        ps6.setInt(1,cart_id);
                        rs = ps6.executeQuery();
                        LOG.info("ViewDAO : Joins 2 tables based on book_id and retrieves details ");

                        while (rs.next()) {
                                viewVO vo = new viewVO();
                                // retrieves the details from table and sets them in the VO
                                // object.
                                vo.setBookId(rs.getInt(1));
                                vo.setCat_id(rs.getInt(2));
                                vo.setBookName(rs.getString(3));
                                vo.setPrice(rs.getFloat(4));
                                vo.setAvailability(rs.getInt(5));
                                vo.setBinding(rs.getString(6));
                                vo.setLanguage(rs.getString(7));
                                vo.setAuthorName(rs.getString(8));
                                vo.setPublisherName(rs.getString(9));
                                vo.setQuantity(rs.getInt(10));
                                // adding the vo into arraylist
                                l.add(vo);

                        }

                } catch (SQLException e) {
                        LOG.error("ViewDAO : SQL Exception"+e);
                        e.printStackTrace();
                }
                LOG.info("ViewDAO : Updated Cart "+l);
                return l;

        }

}