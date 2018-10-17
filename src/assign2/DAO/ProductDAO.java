package assign2.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import assign2.DBConnector;
import assign2.model.Product;

public class ProductDAO {
	static Connection con = null;
	static PreparedStatement pst;
	
	//add product
	public int addProductRow(String productId, String productName, int quantity, double price, String category) {
		
		String query = "INSERT INTO PRODUCT (PRODUCTID, PRODUCTNAME, QUANTITY, PRICE, CATEGORY) "
						+ "VALUES (?, ?, ?, ?, ?)";
		int result = -1;
		try {
			con = DBConnector.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, productId);
			pst.setString(2, productName);
			pst.setInt(3, quantity);
			pst.setDouble(4, price);
			pst.setString(5, category);
			result = pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	//show all information of product
//	public List<Product> gettAllProducts() {
//		String query = "select * from PRODUCT";
//		List<Product> products = new ArrayList<Product>();
//		
//		try {
//			con = DBConnector.getConnection();
//			System.out.println(con);
//			pst = con.prepareStatement(query);
//			
//			ResultSet rs = pst.executeQuery();
//			
//			while(rs.next()) {
//				Product product = new Product(
//					rs.getString(1),
//					rs.getString(2),
//					rs.getInt(3),
//					rs.getDouble(4),
//					rs.getString(5)
//				);
//				products.add(product);
//			}
//			pst.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return products;
//	}
	
	//get product by id searched
	public Product getProductById(String productId) {
		System.out.println(productId);
		String query = "select * from Product where PRODUCTID = ?";
		Product product = null;
		try {
			con = DBConnector.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, productId);
		
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())  {
				product = new Product(
					rs.getString(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getDouble(4),
					rs.getString(5)
				);
			}
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	//edit product by price or quantity
	public int editProduct(String productId, Integer quantity, Double price) {
		System.out.println(productId);
		String query = "UPDATE PRODUCT SET ";
		
		if( quantity != null && price != null) {
			query += "QUANTITY=?, PRICE=?";
		} else if( price != null ) {
			query += "PRICE=?";
		} else if( quantity != null) {
			query += "QUANTITY=?";
		}
		query += " WHERE PRODUCTID=?";
		
		System.out.println(query);
		int result = -1;
		try {
			con = DBConnector.getConnection();
			pst = con.prepareStatement(query);
			
			if( quantity != null && price != null) {
				pst.setInt(1, quantity);
				pst.setDouble(2, price);
				pst.setString(3, productId);
			} else if( price != null ) {
				pst.setDouble(1, price);
				pst.setString(2, productId);
			} else if( quantity != null) {
				pst.setInt(1, quantity);
				pst.setString(2, productId);
			}
			
			result = pst.executeUpdate();
			
//			product = new Product(
//				rs.getString(1),
//				rs.getString(2),
//				rs.getInt(3),
//				rs.getDouble(4),
//				rs.getString(5)
//			);
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
