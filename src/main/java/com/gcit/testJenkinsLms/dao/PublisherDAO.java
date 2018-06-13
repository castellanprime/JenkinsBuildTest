/**
 * 
 */
package com.gcit.testJenkinsLms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.gcit.testJenkinsLms.entity.Publisher;

/**
 * @publisher iratusmachina
 *
 */
@Component
public class PublisherDAO extends BaseDAO<Publisher> 
	implements ResultSetExtractor<List<Publisher>>{
	
	private static final Logger logger = LoggerFactory.getLogger(PublisherDAO.class);

	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		mySqlTemplate.update("insert into tbl_publisher(publisherName, publisherAddress, publisherPhone) values(?, ?, ?)", 
				publisher.getPublisherName(), 
				publisher.getPublisherAddress(), 
				publisher.getPublisherPhone());
	}
	
	public Integer addPublisherWithID(Publisher publisher) throws ClassNotFoundException, SQLException {
		String insertSql = "insert into tbl_publisher(publisherName, publisherAddress, publisherPhone) values(?, ?, ?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String id_column = "publisherId";
		mySqlTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertSql, new String[] { id_column });
			ps.setString(1, publisher.getPublisherName());
			ps.setString(2, publisher.getPublisherAddress());
			ps.setString(3, publisher.getPublisherPhone());
			return ps;
		}, keyHolder);

		logger.info("Id is {}", keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		mySqlTemplate.update("delete from tbl_publisher where publisherId = ?", publisher.getPublisherId());
	}
	
	public void updateName(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		mySqlTemplate.update("update tbl_publisher set publisherName = ? where publisherId = ?", 
				publisher.getPublisherName(), publisher.getPublisherId());
	}
	
	public void updatePhone(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		mySqlTemplate.update("update tbl_publisher set publisherPhone = ? where publisherId = ?", 
				publisher.getPublisherPhone(), publisher.getPublisherId());
	}
	
	public void updateAddress(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		mySqlTemplate.update("update tbl_publisher set publisherAddress = ? where publisherId = ?", 
				publisher.getPublisherAddress(), publisher.getPublisherId());
	}
	
	public List<Publisher> getAllPublishers() throws ClassNotFoundException, SQLException{
		return mySqlTemplate.query("select * from tbl_publisher", this);
	}
	
	public Publisher getPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		return mySqlTemplate.query("select * from tbl_publisher where publisherId = ?", 
				new Object[] {publisher.getPublisherId()}, this).get(0);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}
}
