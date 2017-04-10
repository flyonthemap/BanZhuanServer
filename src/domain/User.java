package domain;

/*
 * 储存用户相关的信息
 */
public class User {
	// 用户Id
	private int userId;
	// 用户手机号
	private String phoneNum;
	private String password;
	private String nickname;
	private int gender;
	// 用户头像的地址
	private String portrait;
	private int loginState;
	// 当前的积分
	private int currentIntegral;
	// 已经兑换的积分
	private int exchangedIngegral;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public int getLoginState() {
		return loginState;
	}
	public void setLoginState(int loginState) {
		this.loginState = loginState;
	}
	public int getCurrentIntegral() {
		return currentIntegral;
	}
	public void setCurrentIntegral(int currentIntegral) {
		this.currentIntegral = currentIntegral;
	}
	public int getExchangedIngegral() {
		return exchangedIngegral;
	}
	public void setExchangedIngegral(int exchangedIngegral) {
		this.exchangedIngegral = exchangedIngegral;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", phoneNum=" + phoneNum
				+ ", password=" + password + ", nickname=" + nickname
				+ ", gender=" + gender + ", portrait=" + portrait
				+ ", loginState=" + loginState + ", currentIntegral="
				+ currentIntegral + ", exchangedIngegral=" + exchangedIngegral
				+ "]";
	}
	
}
