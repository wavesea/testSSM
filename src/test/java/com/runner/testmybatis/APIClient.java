///**
// * @author LiHaibo
// * @project testMybatis
// * @package com.runner.testmybatis
// * @name Test.java 
// * @date 2016-10-13 上午9:29:52
// * @Version 1.0
// *
// */
//package com.runner.testmybatis;
//
//import java.io.UnsupportedEncodingException;
//import java.sql.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//// Referenced classes of package com.jasson.im.api:
////            MOItem, RPTItem
//
///**
// * API连接实现
// * 
// * @author LiHaibo
// * 
// * @fileName APIClient.java
// * @date 2016-10-14 上午8:53:20
// * @Version 1.0
// * 
// */
//public class APIClient {
//
//	public static final int IMAPI_SUCC = 0;
//	public static final int IMAPI_CONN_ERR = -1;
//	public static final int IMAPI_CONN_CLOSE_ERR = -2;
//	public static final int IMAPI_INS_ERR = -3;
//	public static final int IMAPI_DEL_ERR = -4;
//	public static final int IMAPI_QUERY_ERR = -5;
//	public static final int IMAPI_DATA_ERR = -6;
//	public static final int IMAPI_API_ERR = -7;
//	public static final int IMAPI_DATA_TOOLONG = -8;
//	public static final int IMAPI_INIT_ERR = -9;
//	public static final int IMAPI_IFSTATUS_INVALID = -10;
//	public static final int IMAPI_GATEWAY_CONN_ERR = -11;
//	private String dbUser;
//	private String dbPwd;
//	private String apiCode_;
//	private String dbUrl;
//	private Connection conn;
//
//	public APIClient() {
//		dbUser = null;
//		dbPwd = null;
//		apiCode_ = null;
//		dbUrl = null;
//		conn = null;
//	}
//
//	public int init(String dbIP, String dbUser, String dbPwd, String apiCode) {
//		release();
//		this.dbUser = dbUser;
//		this.dbPwd = dbPwd;
//		apiCode_ = apiCode;
//		dbUrl = "jdbc:mysql://" + dbIP + "/im";
//		return testConnect();
//	}
//
//	public int init(String dbIP, String dbUser, String dbPwd, String apiCode,
//			String dbName) {
//		release();
//		this.dbUser = dbUser;
//		this.dbPwd = dbPwd;
//		apiCode_ = apiCode;
//		dbUrl = "jdbc:mysql://" + dbIP + "/" + dbName;
//		return testConnect();
//	}
//
//	public int sendSM(String mobile, String content, long smID) {
//		return sendSM(new String[] { mobile }, content, smID, smID);
//	}
//
//	public int sendSM(String mobiles[], String content, long smID) {
//		return sendSM(mobiles, content, smID, smID);
//	}
//
//	public int sendSM(String mobiles[], String content, long smID, long srcID) {
//		return sendSM(mobiles, content, smID, srcID, "");
//	}
//
//	public int sendSM(String mobiles[], String content, String sendTime,
//			long smID, long srcID) {
//		return sendSM(mobiles, content, smID, srcID, "", sendTime);
//	}
//
//	public int sendSM(String mobile, String content, long smID, String url) {
//		return sendSM(new String[] { mobile }, content, smID, url);
//	}
//
//	public int sendSM(String mobiles[], String content, long smID, String url) {
//		return sendSM(mobiles, content, smID, smID, url);
//	}
//
//	public int sendSM(String mobiles[], String content, long smID, long srcID,
//			String url) {
//		return sendSM(mobiles, content, smID, smID, url, "");
//	}
//
//	public int sendSM(String mobiles[], String content, long smID, long srcID,
//			String url, String sendTime) {
//		if (dbUrl == null) {
//			return -9;
//		}
//		if (mobiles == null || mobiles.length == 0) {
//			return -6;
//		}
//		StringBuffer mobileBuf = new StringBuffer();
//		for (int i = 0; i < mobiles.length; i++) {
//			mobileBuf.append(",").append(mobiles[i]);
//		}
//
//		if (mobileBuf.length() > 1) {
//			mobileBuf.delete(0, 1);
//		} else {
//			return -6;
//		}
//		String contenttmp = replaceSpecilAlhpa(content);
//		if (contenttmp.length() < 1) {
//			return -6;
//		}
//		if (contenttmp.length() > 2000) {
//			contenttmp = contenttmp.substring(0, 2000);
//		}
//		if (!checkSmID(smID) || !checkSmID(srcID)) {
//			return -6;
//		}
//		if (url != null && url.length() > 0) {
//			if (url.length() > 110) {
//				return -8;
//			}
//			if ((url + contenttmp).length() > 120) {
//				return -8;
//			}
//		}
//		int ret = checkGatConn();
//		if (ret != 1) {
//			return ret;
//		}
//		if (!"".equals(sendTime) && isDateTime(sendTime) == null) {
//			return -6;
//		} else {
//			return mTInsert(mobileBuf.toString(), contenttmp, smID, srcID, url,
//					sendTime);
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//	public MOItem[] receiveSM() {
//		Statement st;
//		String getMoSql;
//		String delMoSql;
//		ArrayList moList;
//		StringBuffer snBuf;
//		if (dbUrl == null) {
//			return null;
//		}
//		if (conn == null) {
//			int state = initConnect();
//			if (state != 0) {
//				return null;
//			}
//		}
//		st = null;
//		ResultSet rs = null;
//		getMoSql = "select * from api_mo_" + apiCode_ + " limit 5000";
//		delMoSql = "delete from api_mo_" + apiCode_ + " where AUTO_SN in (";
//		moList = new ArrayList();
//		snBuf = new StringBuffer("-1");
//		st = conn.createStatement();
//		ResultSet rs;
//		MOItem mItemtmp;
//		for (rs = st.executeQuery(getMoSql); rs.next(); moList.add(mItemtmp)) {
//			mItemtmp = new MOItem();
//			mItemtmp.setSmID(rs.getLong("SM_ID"));
//			mItemtmp.setContent(rs.getString("CONTENT"));
//			mItemtmp.setMobile(rs.getString("MOBILE"));
//			mItemtmp.setMoTime(rs.getString("MO_TIME"));
//			snBuf.append(",").append(rs.getString("AUTO_SN"));
//		}
//
//		rs.close();
//		st.executeUpdate(delMoSql + snBuf.toString() + ")");
//		break MISSING_BLOCK_LABEL_291;
//		Exception e;
//		// e;
//		MOItem amoitem[];
//		releaseConn();
//		amoitem = null;
//		closeStatment(st);
//		return amoitem;
//		Exception exception;
//		// exception;
//		closeStatment(st);
//		throw exception;
//		closeStatment(st);
//		MOItem moItem[] = new MOItem[0];
//		return (MOItem[]) moList.toArray(moItem);
//	}
//
//	public MOItem[] receiveSM(long srcID, int amount) {
//		Statement st;
//		String getMoSql;
//		String delMoSql;
//		ArrayList moList;
//		StringBuffer snBuf;
//		if (dbUrl == null) {
//			return null;
//		}
//		if (conn == null) {
//			int state = initConnect();
//			if (state != 0) {
//				return null;
//			}
//		}
//		st = null;
//		ResultSet rs = null;
//		getMoSql = "select * from api_mo_" + apiCode_;
//		if (srcID != -1L) {
//			getMoSql = getMoSql + " where SM_ID=" + srcID;
//		}
//		if (amount != -1) {
//			getMoSql = getMoSql + " limit " + amount;
//		}
//		delMoSql = "delete from api_mo_" + apiCode_ + " where AUTO_SN in (";
//		moList = new ArrayList();
//		snBuf = new StringBuffer("-1");
//		st = conn.createStatement();
//		ResultSet rs;
//		MOItem mItemtmp;
//		for (rs = st.executeQuery(getMoSql); rs.next(); moList.add(mItemtmp)) {
//			mItemtmp = new MOItem();
//			mItemtmp.setSmID(rs.getLong("SM_ID"));
//			mItemtmp.setContent(rs.getString("CONTENT"));
//			mItemtmp.setMobile(rs.getString("MOBILE"));
//			mItemtmp.setMoTime(rs.getString("MO_TIME"));
//			snBuf.append(",").append(rs.getString("AUTO_SN"));
//		}
//
//		rs.close();
//		st.executeUpdate(delMoSql + snBuf.toString() + ")");
//		break MISSING_BLOCK_LABEL_372;
//		Exception e;
//		// e;
//		MOItem amoitem[];
//		releaseConn();
//		amoitem = null;
//		closeStatment(st);
//		return amoitem;
//		Exception exception;
//		// exception;
//		closeStatment(st);
//		throw exception;
//		closeStatment(st);
//		MOItem moItem[] = new MOItem[0];
//		return (MOItem[]) moList.toArray(moItem);
//	}
//
//	public RPTItem[] receiveRPT() {
//		Statement st;
//		String getRPTSql;
//		String delRPTSql;
//		ArrayList rptList;
//		StringBuffer snBuf;
//		if (dbUrl == null) {
//			return null;
//		}
//		ResultSet rs = null;
//		st = null;
//		if (conn == null) {
//			int state = initConnect();
//			if (state != 0) {
//				return null;
//			}
//		}
//		getRPTSql = "select * from api_rpt_" + apiCode_ + " limit 5000";
//		delRPTSql = "delete from api_rpt_" + apiCode_ + " where AUTO_SN in (";
//		RPTItem rptItem[] = (RPTItem[]) null;
//		rptList = new ArrayList();
//		snBuf = new StringBuffer("-1");
//		st = conn.createStatement();
//		ResultSet rs;
//		RPTItem rptItemtmp;
//		for (rs = st.executeQuery(getRPTSql); rs.next(); rptList
//				.add(rptItemtmp)) {
//			rptItemtmp = new RPTItem();
//			rptItemtmp.setSmID(rs.getLong("SM_ID"));
//			rptItemtmp.setCode(rs.getInt("RPT_CODE"));
//			rptItemtmp.setMobile(rs.getString("MOBILE"));
//			rptItemtmp.setDesc(rs.getString("RPT_DESC"));
//			rptItemtmp.setRptTime(rs.getString("RPT_TIME"));
//			snBuf.append(",").append(rs.getString("AUTO_SN"));
//		}
//
//		rs.close();
//		st.executeUpdate(delRPTSql + snBuf.toString() + ")");
//		break MISSING_BLOCK_LABEL_364;
//		SQLException e;
//		// e;
//		RPTItem arptitem[];
//		releaseConn();
//		if (e.getErrorCode() != 1146 && e.getErrorCode() != 1142) {
//			break MISSING_BLOCK_LABEL_330;
//		}
//		arptitem = new RPTItem[0];
//		closeStatment(st);
//		return arptitem;
//		arptitem = null;
//		closeStatment(st);
//		return arptitem;
//		Exception ex;
//		// ex;
//		arptitem = null;
//		closeStatment(st);
//		return arptitem;
//		Exception exception;
//		// exception;
//		closeStatment(st);
//		throw exception;
//		closeStatment(st);
//		RPTItem rptItem[] = new RPTItem[0];
//		return (RPTItem[]) rptList.toArray(rptItem);
//	}
//
//	public RPTItem[] receiveRPT(long smID, int amount) {
//		Statement st;
//		String getRPTSql;
//		String delRPTSql;
//		ArrayList rptList;
//		StringBuffer snBuf;
//		if (dbUrl == null) {
//			return null;
//		}
//		ResultSet rs = null;
//		st = null;
//		if (conn == null) {
//			int state = initConnect();
//			if (state != 0) {
//				return null;
//			}
//		}
//		getRPTSql = "select * from api_rpt_" + apiCode_;
//		if (smID != -1L) {
//			getRPTSql = getRPTSql + " where SM_ID=" + smID;
//		}
//		if (amount != -1) {
//			getRPTSql = getRPTSql + " limit " + amount;
//		}
//		delRPTSql = "delete from api_rpt_" + apiCode_ + " where AUTO_SN in (";
//		RPTItem rptItem[] = (RPTItem[]) null;
//		rptList = new ArrayList();
//		snBuf = new StringBuffer("-1");
//		st = conn.createStatement();
//		ResultSet rs;
//		RPTItem rptItemtmp;
//		for (rs = st.executeQuery(getRPTSql); rs.next(); rptList
//				.add(rptItemtmp)) {
//			rptItemtmp = new RPTItem();
//			rptItemtmp.setSmID(rs.getLong("SM_ID"));
//			rptItemtmp.setCode(rs.getInt("RPT_CODE"));
//			rptItemtmp.setMobile(rs.getString("MOBILE"));
//			rptItemtmp.setDesc(rs.getString("RPT_DESC"));
//			rptItemtmp.setRptTime(rs.getString("RPT_TIME"));
//			snBuf.append(",").append(rs.getString("AUTO_SN"));
//		}
//
//		rs.close();
//		st.executeUpdate(delRPTSql + snBuf.toString() + ")");
//		break MISSING_BLOCK_LABEL_448;
//		SQLException e;
//		// e;
//		RPTItem arptitem[];
//		releaseConn();
//		if (e.getErrorCode() != 1146 && e.getErrorCode() != 1142) {
//			break MISSING_BLOCK_LABEL_411;
//		}
//		arptitem = new RPTItem[0];
//		closeStatment(st);
//		return arptitem;
//		arptitem = null;
//		closeStatment(st);
//		return arptitem;
//		Exception ex;
//		// ex;
//		arptitem = null;
//		closeStatment(st);
//		return arptitem;
//		Exception exception;
//		// exception;
//		closeStatment(st);
//		throw exception;
//		closeStatment(st);
//		RPTItem rptItem[] = new RPTItem[0];
//		return (RPTItem[]) rptList.toArray(rptItem);
//	}
//
//	public void release() {
//		dbUser = null;
//		dbPwd = null;
//		apiCode_ = null;
//		dbUrl = null;
//		releaseConn();
//	}
//
//	private int testConnect() {
//		Statement st;
//		st = null;
//		ResultSet rs = null;
//		try {
//			if (conn != null) {
//				releaseConn();
//			}
//			getConn();
//			st = conn.createStatement();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			return -1;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}
//		try {
//			String tableName = "api_mo_" + apiCode_;
//			ResultSet rs = st.executeQuery("select * from " + tableName
//					+ " limit 1");
//			rs.close();
//		} catch (SQLException e) {
//			try {
//				st.close();
//			} catch (Exception exception1) {
//			}
//			return -7;
//		}
//		break MISSING_BLOCK_LABEL_137;
//		Exception exception;
//		// exception;
//		try {
//			st.close();
//		} catch (Exception exception2) {
//		}
//		throw exception;
//		try {
//			st.close();
//		} catch (Exception exception3) {
//		}
//		return 0;
//	}
//
//	private int initConnect() {
//		try {
//			getConn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		}
//		return 0;
//	}
//
//	private void getConn() throws ClassNotFoundException, SQLException {
//		Class.forName("org.gjt.mm.mysql.Driver");
//		conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
//	}
//
//	private void releaseConn() {
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException sqlexception) {
//			}
//		}
//		conn = null;
//	}
//
//	private int mTInsert(String mobile, String content, long smID, long srcID,
//			String url, String sendTime) {
//		String sendMTSql;
//		Statement st;
//		if (conn == null) {
//			int state = initConnect();
//			if (state != 0) {
//				return -1;
//			}
//		}
//		if (sendTime == null || "".equals(sendTime)) {
//			sendTime = getCurDateTime();
//		}
//		sendMTSql = "";
//		if (url == null || url.trim().length() == 0) {
//			sendMTSql = "insert into api_mt_" + apiCode_
//					+ " (SM_ID,SRC_ID,MOBILES,CONTENT,SEND_TIME) values ("
//					+ smID + "," + srcID + ",'" + mobile + "','" + content
//					+ "','" + sendTime + "')";
//		} else {
//			sendMTSql = "insert into api_mt_"
//					+ apiCode_
//					+ " (SM_ID,SRC_ID,MOBILES,CONTENT,IS_WAP,URL,SEND_TIME) values ("
//					+ smID + "," + srcID + ",'" + mobile + "','" + content
//					+ "'," + 1 + ",'" + url + "','" + sendTime + "')";
//		}
//		st = null;
//		try {
//			st = conn.createStatement();
//			st.executeUpdate(gb2Iso(sendMTSql));
//			break MISSING_BLOCK_LABEL_299;
//		} catch (SQLException e) {
//			releaseConn();
//		}
//		closeStatment(st);
//		return -3;
//		Exception exception;
//		exception;
//		closeStatment(st);
//		throw exception;
//		closeStatment(st);
//		return 0;
//	}
//
//	private void closeStatment(Statement st) {
//		try {
//			st.close();
//		} catch (Exception exception) {
//		}
//	}
//
//	private String replaceSpecilAlhpa(String content) {
//		if (content == null || content.trim().length() == 0) {
//			return "";
//		}
//		String spec_char = "\\'";
//		String retStr = "";
//		for (int i = 0; i < content.length(); i++) {
//			if (spec_char.indexOf(content.charAt(i)) >= 0) {
//				retStr = retStr + "\\";
//			}
//			retStr = retStr + content.charAt(i);
//		}
//
//		return retStr;
//	}
//
//	private boolean checkSmID(long smID) {
//		return smID >= 0L && smID <= 0x5f5e0ffL;
//	}
//
//	private String gb2Iso(String str) {
//		if (str == null) {
//			return "";
//		}
//		String temp = "";
//		try {
//			byte buf[] = str.trim().getBytes("GBK");
//			temp = new String(buf, "iso8859-1");
//		} catch (UnsupportedEncodingException e) {
//			temp = str;
//		}
//		return temp;
//	}
//
//	private int checkGatConn()
//    {
//        int ret;
//        Statement st;
//        String sql;
//        ret = 1;
//        ResultSet rs = null;
//        st = null;
//        if(conn == null)
//        {
//            initConnect();
//        }
//        sql = "select if_status,conn_succ_status from tbl_api_info as api where api.if_code='" + apiCode_ + "' limit 1";
//        try
//        {
//            st = conn.createStatement();
//            ResultSet rs;
//            for(rs = st.executeQuery(sql); rs.next();)
//            {
//                if("2".equals(rs.getString("if_status")))
//                {
//                    ret = -10;
//                }
//                if("0".equals(rs.getString("conn_succ_status")))
//                {
//                    ret = -11;
//                }
//            }
//
//            rs.close();
//        }
//        catch(SQLException e)
//        {
//            try
//            {
//                st.close();
//            }
//            catch(Exception exception1) { }
//            return -7;
//        }
//        break MISSING_BLOCK_LABEL_160;
//        Exception exception;
//        exception;
//        try
//        {
//            st.close();
//        }
//        catch(Exception exception2) { }
//        throw exception;
//        try
//        {
//            st.close();
//        }
//        catch(Exception exception3) { }
//        return ret;
//    }
//
//	public static String getCurDateTime() {
//		SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return nowDate.format(new Date());
//	}
//
//	public static String isDateTime(String str) {
//		if (str == null) {
//			return null;
//		}
//		if (str.length() != 19) {
//			return null;
//		}
//		if (str.split(" ")[0].length() != 10) {
//			return null;
//		}
//		if (str.split(" ")[1].length() != 8) {
//			return null;
//		}
//		int temp = Integer.parseInt(str.substring(5, 7));
//		if (12 < temp || temp < 1) {
//			return null;
//		}
//		temp = Integer.parseInt(str.substring(8, 10));
//		if (31 < temp || temp < 1) {
//			return null;
//		}
//		temp = Integer.parseInt(str.substring(11, 13));
//		if (23 < temp || temp < 0) {
//			return null;
//		}
//		temp = Integer.parseInt(str.substring(14, 16));
//		if (59 < temp || temp < 0) {
//			return null;
//		}
//		temp = Integer.parseInt(str.substring(17, 19));
//		if (59 < temp || temp < 0) {
//			return null;
//		}
//		Date returnDate = null;
//		DateFormat df = DateFormat.getDateInstance();
//		try {
//			returnDate = df.parse(str);
//			return returnDate.toString();
//		} catch (Exception e) {
//			return null;
//		}
//	}
// }