package core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体类
 * @author jiazh
 */
public class PageBean<T> {
	
	/**
	 * 当前页码
	 */
	private Integer start = 1;
	/**
	 * 页面数量
	 */
	private Integer limit = 15;
	/**
	 * 总记录条数
	 */
	private Long totalCount = 0L;
	
	private List<T> records = new ArrayList<T>();
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	
}
