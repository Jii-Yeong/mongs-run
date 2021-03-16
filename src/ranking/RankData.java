package ranking;

import java.io.Serializable;

public class RankData implements Comparable<RankData>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7113901543373795492L;
	private String name;
	private int score;
	
	public RankData() {
		super();
	}

	public RankData(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (score ^ (score >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RankData))
			return false;
		RankData other = (RankData) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "RankData [name=" + name + ", score=" + score + "]";
	}
	
	@Override
	public int compareTo(RankData o) {
		return score - o.score;
	}
}
