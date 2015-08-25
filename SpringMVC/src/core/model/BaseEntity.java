package core.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseEntity implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer sUuid; //uuid编号
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sUuid", unique = true)
	public Integer getSUuid() {
		return sUuid;
	}
	public void setSUuid(Integer uuid) {
		sUuid = uuid;
	}
	
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * asc/desc
	 */
	private String order = "asc";
	/**
	 * Integer 的集合
	 */
	private List<Integer> ILC;
	/**
	 * String 的集合
	 */
	private List<String> SLC;
	/**
	 * Date 的集合
	 */
	private List<Date> DLC;
	/**
	 * 主键id,逗号分隔
	 */
	private String ids;

	@Transient
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	@Transient
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	@Transient
	public List<Integer> getILC() {
		return ILC;
	}
	public void setILC(List<Integer> ilc) {
		ILC = ilc;
	}
	@Transient
	public List<String> getSLC() {
		return SLC;
	}
	public void setSLC(List<String> slc) {
		SLC = slc;
	}
	@Transient
	public List<Date> getDLC() {
		return DLC;
	}
	public void setDLC(List<Date> dlc) {
		DLC = dlc;
	}
	@Transient
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	protected static String line = System.getProperty("line.separator");

}
