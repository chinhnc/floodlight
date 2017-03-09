package readfile;

public class LinkCost {
	private String src;
    private String dst;
    private Integer bandwidth;
    private Integer loss;
    private Integer costs;

    public LinkCost(String srcId, String dstId, Integer bandwidth, Integer loss) {
        this.src = srcId;
        this.dst = dstId;
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
	
	public int getLoss() {
		return loss;
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
    
    public void setLoss(Integer loss) {
    	this.loss = loss;
    }
    
    public void setCosts(Integer costs) {
    	this.costs = costs;
    }
    
    public Integer getCosts() {
    	return costs;
    }
}
