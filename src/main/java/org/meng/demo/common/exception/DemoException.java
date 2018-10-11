package org.meng.demo.common.exception;

/**
 * 自定义异常
 * 
 * @author	Lu1FFy
 * @date 2018-09-19 14:38:43
 */
public class DemoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public DemoException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public DemoException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public DemoException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public DemoException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
