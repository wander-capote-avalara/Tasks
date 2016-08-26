package task.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import task.objects.User;
import task.objects.Work_Item;

public class JDBCwiDAO {
	private Connection connection;

	public JDBCwiDAO(Connection connectionR) {
		this.connection = connectionR;
	}
	
	public List<Work_Item> getWIs(int id) throws Exception {
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT wi.id As wiId, wi.users_id AS wiUserId, wi.name AS wiName, wi.estimated_effort AS wiEE, ");
		stbd.append("wi.description AS wiDesc, wi.status AS wiStatus, wi.effort AS wiE, wi.deviation_percentage AS wiDP, ");
		stbd.append("u.id AS uId, u.username AS uUser, u.email AS uEmail ");
		stbd.append("FROM work_item wi ");
		stbd.append("LEFT JOIN users u ON u.id = wi.users_id ");
		if(id != 0)
			stbd.append("WHERE wi.id = ?");

		PreparedStatement p;
		ResultSet rs = null;
		List<Work_Item> wiList = new ArrayList<Work_Item>();

		try {
			p = this.connection.prepareStatement(stbd.toString());
			if(id != 0)
				p.setInt(1, id);
			
			rs = p.executeQuery();

			while (rs.next()) {
				Work_Item wi = new Work_Item();
				User user = new User();
				
				wi.setId(rs.getInt("wiId"));
				wi.setName(rs.getString("wiName"));
				wi.setEstimated_effort(rs.getTime("wiEE"));
				wi.setDescription(rs.getString("wiDesc"));
				wi.setStatus(rs.getInt("wiStatus"));
				wi.setEffort(rs.getTime("wiE"));
				wi.setDeviation_percentage(rs.getTime("wiDP"));
				
				user.setId(rs.getInt("uId"));
				user.setUsername(rs.getString("uUser"));
				user.setEmail(rs.getString("uEmail"));
				
				wi.setUser(user);
				
				wiList.add(wi);
			}

		} catch (Exception e) {
			throw new Exception("Something went wrong with work itens!", e);
		}
		return wiList;
	}
}
