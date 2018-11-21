package cn.wkf.store.entity;

public class ResponseResult {

	private Integer state = 200;
	private String message;
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseResult(Integer state, Exception e) {
		super();
		this.state = state;
		this.message = e.getMessage();
	}
	public ResponseResult() {
		super();
	}
	
	
}
