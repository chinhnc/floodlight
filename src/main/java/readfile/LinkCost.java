package readfile;

public class LinkCost {
	private String src;
    private String dst;
    private Integer bandwidth;
    private Integer delay;
    private Integer loss;
    private Integer costs;

    public LinkCost(String srcId, String dstId, Integer bandwidth, Integer delay, Integer loss) {
        this.src = srcId;
        this.dst = dstId;
        this.delay = delay;
        this.bandwidth = bandwidth;
        this.loss = loss;
    }
    
    public LinkCost(String srcId, String dstId, Integer costs) {
        this.src = srcId;
        this.dst = dstId;
        this.costs = costs;
    }

	public String getSrc() {
		return src;
	}

	public String getDst() {
		return dst;
	}

	public int getBw() {
		return bandwidth;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public int getLoss() {
		return loss;
	}
	
	public Integer getCosts() {
    	return costs;
    }
	public void setSrc(String src) {
        this.src = src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public void setBw(Integer bandwidth) {
    	this.bandwidth = bandwidth;
    }
    
    public void setDelay(Integer delay) {
    	this.delay = delay;
    }
    
    public void setLoss(Integer loss) {
    	this.loss = loss;
    }
    
    public void setCosts(Integer costs) {
    	this.costs = costs;
    }
    
    
}
