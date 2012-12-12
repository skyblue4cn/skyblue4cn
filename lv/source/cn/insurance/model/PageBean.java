package cn.insurance.model;

import java.util.ArrayList;
import java.util.List;


/**分页用到的基类*/
public class PageBean {

	/*当前页*/
	private Integer curPage ;
	
	/*当前页的开始的第一个的索引*/
	private int curFromIndex ;
	
	/*所有数据的个数*/
	private Integer allNumber ;
	
	/*每页显示的行数*/
	private Integer rowsPerPage ;
	
	/*最大的页数*/
	private Integer maxPage ;
	
	/*对象集合*/
	private List dataList = new ArrayList() ;
	
	/*页面的集合（1---maxPage），暂时没有找到更好的办法,有待完善*/
	private List<Integer> numList = new ArrayList<Integer>() ;
	
	
	
	/*constructor*/
	public PageBean(){
		this.rowsPerPage = 10 ;
	}
	
	
	public PageBean(int rowsPerPage){
		this.rowsPerPage = rowsPerPage ;
	}
	
	/**算最大页数*/
	public void setMaxPage(PageBean pageBean){
		if(pageBean.getAllNumber() % pageBean.getRowsPerPage() ==0){
			this.maxPage = pageBean.getAllNumber()/pageBean.getRowsPerPage() ;
		}else{
			this.maxPage = pageBean.getAllNumber()/pageBean.getRowsPerPage() +1 ;
		}
	}

	/**求出页面的集合*/
	public void setNumList(Integer maxPage){
		for(Integer i=1;i<=maxPage ; i++){
			numList.add(i) ;
		}
	}
	
	/*setter and getter*/
	public Integer getCurPage() {
		return curPage;
	}



	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}




	public Integer getMaxPage() {
		return maxPage;
	}



	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}



	public List<Integer> getNumList() {
		return numList;
	}



	public void setNumList(List<Integer> numList) {
		this.numList = numList;
	}



	public Integer getRowsPerPage() {
		return rowsPerPage;
	}



	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Integer getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public int getCurFromIndex() {
		return curFromIndex;
	}

	public void setCurFromIndex(int curFromIndex) {
		this.curFromIndex = curFromIndex;
	} ;
	
	public void setCurFromIndex(int curPage , int rowsPerPage) {
		this.curFromIndex = (curPage-1)*rowsPerPage+1 ;
	} ;
	
}
