package task.jdbcinterface;

import java.util.List;

import task.objects.WI_Log;
import task.objects.Work_Item;

public interface WiDAO {
	public void add(Work_Item wi) throws Exception;
	public List<WI_Log> getWILogs(int id) throws Exception;
	public List<Work_Item> getWIs(int id, int userId, int status, boolean showAll) throws Exception;
}
