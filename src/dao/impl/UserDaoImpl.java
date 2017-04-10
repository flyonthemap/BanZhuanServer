package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JDBCUtils;
import dao.UserDao;
import domain.User;

public class UserDaoImpl implements UserDao {
//	private static final String REGISTER_SUCCESS = "{\"code\":0}";
	private static final int REGISTER_SUCCESS = 0;

	@Override
	public int save(User u) {
		Connection conn = JDBCUtils.getConnection();
		String sql = " INSERT INTO                                "
				+ " `t_user` (`phoneNum`, `password`, `nickname`)  "
				+ " VALUES                                  "
				+ "  (?, ?, ?)                                 ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getPhoneNum());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getNickname());

			int result = ps.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("用户保存失败！");
			} else {

				return REGISTER_SUCCESS;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("用户保存失败!");
		} finally {

			JDBCUtils.close(conn, ps, null);
		}
	}

	public User getUserByPhone(String phoneNum) {
		User u = null;

		Connection conn = JDBCUtils.getConnection();
		String sql = "select * from t_user where phoneNum=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getInt("userId"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setCurrentIntegral(rs.getInt("curIntegral"));
				u.setPortrait(rs.getString("portrait"));
				u.setGender(rs.getInt("gender"));
				u.setExchangedIngegral(rs.getInt("exchangedIntegral"));
			}

			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�û�ʧ��!");
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}

	}

	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();

		Connection conn = JDBCUtils.getConnection();

		String sql = "select * from t_user ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				User u = new User();
				u.setUserId(rs.getInt("id"));
				u.setNickname(rs.getString("name"));
				u.setPassword(rs.getString("password"));

				list.add(u);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("用户查询失败!");
		} finally {

			JDBCUtils.close(conn, ps, rs);
		}

	}

}
