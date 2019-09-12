package cn.hanquan.pojo;

public class Comment {
	private int id;
	private String cont;
	private String createtime;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Comments [id=" + id + ", cont=" + cont + ", createtime=" + createtime + ", name=" + name + "]";
	}
	
	
}
