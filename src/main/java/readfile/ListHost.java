package readfile;

public class ListHost {
	private String host;
    private String sw;
    
    public ListHost(String hostId, String switchId) {
        this.host = hostId;
        this.sw = switchId;
    }
    
    public String getHost() {
		return host;
	}

	public String getSwitch() {
		return sw;
	}
	
	public void setHost(String host) {
        this.host = host;
    }

    public void setSwitch(String sw) {
        this.sw = sw;
    }
}
