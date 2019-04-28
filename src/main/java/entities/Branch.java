package entities;

public class Branch {
	private String ifsc;
    private int bankId;
    private String name;
    private String address;
    private String city;
    private String district;
    private String state;
    
    public Branch() {}
	public Branch(String ifsc, int bankId, String name, String address, String city, String district, String state) {
		super();
		this.ifsc = ifsc;
		this.bankId = bankId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.district = district;
		this.state = state;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
