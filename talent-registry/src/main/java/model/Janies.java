package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Janies {
	private long id;
	private String name;
	private java.sql.Date birthday;
	private java.sql.Date joinDate;
	private String homeTown;
	private String bloodType;
	private int age;
	private String memberColor;

	public Janies(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Janies(String id, String name, String birthday, String joinDate, String homeTown, String bloodType,
			String age, String memberColor) {

		long lId = 0;
		int iAge = 0;
		java.sql.Date dBirthday = null;
		java.sql.Date dJoinDate = null;

		try {
			iAge = Integer.parseInt(age);
			this.age = iAge;
		} catch (NumberFormatException e) {
			this.id = 0;
		}

		try {
			lId = Long.parseLong(id);
			this.id = lId;
		} catch (NumberFormatException e) {
			this.id = 0;
		}

		try {
			dBirthday = convertStringToSQLDate(birthday);
			this.birthday = dBirthday;
		} catch (ParseException e) {
			this.birthday = null;
		}

		try {
			dJoinDate = convertStringToSQLDate(joinDate);
			this.joinDate = dJoinDate;
		} catch (ParseException e) {
			this.joinDate = null;
		}

		this.id = lId;
		this.name = name;
		this.birthday = dBirthday;
		this.joinDate = dJoinDate;
		this.homeTown = homeTown;
		this.bloodType = bloodType;
		this.age = iAge;
		this.memberColor = memberColor;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public java.sql.Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String blood) {
		this.bloodType = blood;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMemberColor() {
		return memberColor;
	}

	public void setMemberColor(String color) {
		this.memberColor = color;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedBirthday = (birthday != null) ? dateFormat.format(birthday) : "N/A";
		String formattedAdmissionDate = (joinDate != null) ? dateFormat.format(joinDate) : "N/A";

		return "Janies{" +
				"id=" + id +
				", name='" + name + '\'' +
				", birthday=" + formattedBirthday +
				", admission_date=" + formattedAdmissionDate +
				", birth_place='" + homeTown + '\'' +
				", blood='" + bloodType + '\'' +
				", age=" + age +
				", color='" + memberColor + '\'' +
				'}';
	}

	private static java.sql.Date convertStringToSQLDate(String dateString) throws ParseException {
		if (dateString == null) {
			return null; // もしdateStringがnullなら、nullを返す
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(dateString);
		return new java.sql.Date(parsedDate.getTime());
	}
}
