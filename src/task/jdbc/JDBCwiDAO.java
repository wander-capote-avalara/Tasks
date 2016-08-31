package task.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import task.jdbcinterface.WiDAO;
import task.objects.User;
import task.objects.WI_Log;
import task.objects.Work_Item;

public class JDBCwiDAO implements WiDAO{
	private Connection connection;

	public JDBCwiDAO(Connection connectionR) {
		this.connection = connectionR;
	}

	public List<Work_Item> getWIs(int id, int userId, int status, boolean showAll) throws Exception {
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT wi.id As wiId, wi.users_id AS wiUserId, wi.name AS wiName, wi.estimated_effort AS wiEE, ");
		stbd.append("wi.description AS wiDesc, wi.status AS wiStatus, wi.effort AS wiE,  ");
		stbd.append("u.id AS uId, u.username AS uUser, u.email AS uEmail ");
		//stbd.append("cast((effort*100)/estimated_effort as decimal(16,2)) as percentage ");
		stbd.append("FROM work_item wi ");
		stbd.append("LEFT JOIN users u ON u.id = wi.users_id ");
		stbd.append(!showAll ? "WHERE wi.users_id = ? ":"WHERE 1=1 ");
		if (id != 0)
			stbd.append("AND wi.id = ? ");
		if(status != 4)
			stbd.append("AND wi.status ="+status);


		PreparedStatement p;
		ResultSet rs = null;
		List<Work_Item> wiList = new ArrayList<Work_Item>();

		try {
			p = this.connection.prepareStatement(stbd.toString());
			if (!showAll)
				p.setInt(1, userId);
			if (id != 0)
				p.setInt(2, id);

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
				//wi.setDeviation_percentage(rs.getString("percentage")+"%");
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

	public void add(Work_Item wi) throws Exception {
		StringBuilder stbd = new StringBuilder();
		if (wi.getId() == 0) {
			stbd.append("INSERT INTO work_item (");
			stbd.append("users_id, name, estimated_effort, description, ");
			stbd.append("status, effort");
			stbd.append(") VALUES (?,?,?,?,?,?)");

			PreparedStatement p;
			ResultSet rs = null;

			try {
				p = this.connection.prepareStatement(stbd.toString(),
						PreparedStatement.RETURN_GENERATED_KEYS);
				p.setInt(1, wi.getUser().getId());
				p.setString(2, wi.getName());
				p.setTime(3, wi.getEstimated_effort());
				p.setString(4, wi.getDescription());
				p.setInt(5, wi.getStatus());
				p.setTime(6, wi.getEffort());
				p.execute();
				rs = p.getGeneratedKeys();
				if (rs.next())
					wi.setId(rs.getInt(1));

				wILogAdd(wi);			
			} catch (Exception e) {
				throw new Exception("Error while adding work item!");
			}
		} else {
			stbd.append("UPDATE work_item SET ");
			stbd.append("users_id=?, name=?, estimated_effort=?, description=?, ");
			stbd.append("status=?, effort=? ");
			stbd.append("WHERE id = ?");
			
			PreparedStatement p;
			ResultSet rs = null;

			try {
				p = this.connection.prepareStatement(stbd.toString(),
						PreparedStatement.RETURN_GENERATED_KEYS);
				p.setInt(1, wi.getUser().getId());
				p.setString(2, wi.getName());
				p.setTime(3, wi.getEstimated_effort());
				p.setString(4, wi.getDescription());
				p.setInt(5, wi.getStatus());
				p.setTime(6, wi.getEffort());
				p.setInt(7, wi.getId());
				p.execute();
				rs = p.getGeneratedKeys();
				if (rs.next())
					wi.setId(rs.getInt(1));

				wILogAdd(wi);				
			} catch (Exception e) {
				throw new Exception("Error while editing work item!");
			}
		}
	}

	private void wILogAdd(Work_Item wi) throws Exception {
		try {
			StringBuilder stbd = new StringBuilder();

			stbd.append("INSERT INTO wi_log (");
			stbd.append("work_item_id, change_date, changed_status");
			stbd.append(") VALUES (?,current_date,?)");

			PreparedStatement p;

			try {
				p = this.connection.prepareStatement(stbd.toString());
				p.setInt(1, wi.getId());
				p.setInt(2, wi.getStatus());
				p.execute();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new Exception("Error while adding work item log");
		}
	}

	public List<WI_Log> getWILogs(int id) throws Exception {
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT wl.id AS wlId, wl.work_item_id AS wlWI, ");
		stbd.append("wl.change_date AS wlCD, wl.changed_status AS wlCS, wi.name AS wiN ");
		stbd.append("FROM wi_log wl ");
		stbd.append("INNER JOIN work_item wi ON wi.id = wl.work_item_id ");
		if (id != 0)
			stbd.append("WHERE wl.work_item_id=?");

		PreparedStatement p;
		ResultSet rs = null;
		List<WI_Log> wiLList = new ArrayList<WI_Log>();
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

		try {
			p = this.connection.prepareStatement(stbd.toString());
			if (id != 0)
				p.setInt(1, id);

			rs = p.executeQuery();

			while (rs.next()) {
				WI_Log wiL = new WI_Log();
				Work_Item wi = new Work_Item();

				wiL.setId(rs.getInt("wlId"));
				wiL.setFormatedDate(date.format(rs.getDate("wlCD")).replace(
						"-", "/"));
				wiL.setChanged_status(rs.getInt("wlCS"));
				wi.setName(rs.getString("wiN"));

				wiL.setWi(wi);

				wiLList.add(wiL);
			}
		} catch (Exception e) {
			throw new Exception("Error!", e);
		}
		return wiLList;
	}
}
