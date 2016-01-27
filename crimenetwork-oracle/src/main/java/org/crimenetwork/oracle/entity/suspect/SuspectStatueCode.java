package org.crimenetwork.oracle.entity.suspect;


import java.util.HashMap;

public class SuspectStatueCode {

	private static volatile SuspectStatueCode INSTANCE = null;
	private HashMap<String, String> statues;
    private HashMap<String, String> specialstatus;
	private SuspectStatueCode() {
		statues=new HashMap<String, String>();
		specialstatus=new HashMap<String, String>();
		/*
		 * <s:select id="status" name="suspectInfo.status" list="#
		 * {'00':'未知','01':'国家公务人员','02':'企事业管理人员','03':'企事业职员','04':'工人',
		 * '05':'农民','06':'个体工商业者','07':'在校学生','08':'离退休人员','09':'无业人员',
		 * '10':'军人','99':'其他'}"
		 */
		statues.put("00", "未知");
		statues.put("01", "国家公务人员");
		statues.put("02", "企事业管理人员");
		statues.put("03", "企事业职员");
		statues.put("04", "工人");
		statues.put("05", "农民");
		statues.put("06", "个体工商业者");
		statues.put("07", "在校学生");
		statues.put("08", "离退休人员");
		statues.put("09", "无业人员");
		statues.put("10", "军人");
		statues.put("99", "其他");
		
		/*
		 *  <s:select id="specialstatus" name="suspectInfo.specialstatus"list="#
		 *  {'00':'未知','01':'人大代表','02':'政协委员','03':'民主党首','04':'中高干部',
		 *  '05':'高知高工','06':'公安民警','07':'其他民警','08':'检法干部','09':'工商税务',
		 *  '10':'海关稽查','11':'保安警卫','12':'外交官员','13':'外交家属','14':'外商职员',
		 *  '15':'华侨人士','16':'侨眷侨属','17':'留学人员','18':'外籍人员','19':'港澳通人士',
		 *  '20':'宗教界人士','21':'邪教人士','22':'民族分裂分子','23':'民运人员','24':'未成年人',
		 *  '99':'其他'}"
		 */
		
		specialstatus.put("00", "未知");
		specialstatus.put("01", "人大代表");
		specialstatus.put("02", "政协委员");
		specialstatus.put("03", "民主党首");
		specialstatus.put("04", "中高干部");
		specialstatus.put("05", "高知高工");
		specialstatus.put("06", "公安民警");
		specialstatus.put("07", "其他民警");
		specialstatus.put("08", "检法干部");
		specialstatus.put("09", "工商税务");
		specialstatus.put("10", "海关稽查");
		specialstatus.put("11", "保安警卫");
		specialstatus.put("12", "外交官员");
		specialstatus.put("13", "外交家属");
		specialstatus.put("14", "外商职员");
		specialstatus.put("15", "华侨人士");
		specialstatus.put("16", "侨眷侨属");
		specialstatus.put("17", "留学人员");
		specialstatus.put("18", "外籍人员");
		specialstatus.put("19", "港澳通人士");
		specialstatus.put("20", "宗教界人士");
		specialstatus.put("21", "邪教人士");
		specialstatus.put("22", "民族分裂分子");
		specialstatus.put("23", "民运人员");
		specialstatus.put("24", "未成年人");
		specialstatus.put("99", "其他");
		
		
		
	}

	public static SuspectStatueCode getAnInstance() {
		if (INSTANCE == null) {
			synchronized (SuspectStatueCode.class) {
				if (INSTANCE == null) {
					INSTANCE = new SuspectStatueCode();
				}
			}
		}
		return INSTANCE;
	}
	
	public String getSpecialstatusByCode(String code){
		return specialstatus.get(code);
	}
	
	public String getStatusByCode(String code){
		return statues.get(code);
	}
	
	
	public static void main(String[] args) {
		//SuspectStatueCode abGraph=SuspectStatueCode.getAnInstance();
		
		
	}

}
