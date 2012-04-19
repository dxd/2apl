package apapl.data;

import apapl.plans.Plan;
import apapl.plans.PlanSeq;
import apapl.program.BeliefUpdate;
import apapl.program.BeliefUpdates;
import apapl.program.PGrule;

public class Prohibition {
	
	private Literal     prohibition;
	private Literal		sanction;
	private byte		priority;
	
	/**
	 * Constructs a new empty prohibition.
	 */
	public Prohibition()
	{
		
	}
	

	public Prohibition(Literal prohibition)
	{
		this.prohibition = prohibition;

	}
	

	public Prohibition(Literal prohibition, Literal sanction)
	{
		this.prohibition = prohibition;
		this.sanction = sanction;
	}

	public void addSanction(Literal sanction)
	{
		this.sanction = sanction;
	}
	
	public void addLiteral(Literal prohibition)
	{
		this.prohibition = prohibition;
	}
	
	public void addPriority(byte priority)
	{
		this.priority = priority;
	}
		
	/**
	 * Gives a string representation of this object.
	 */
	public String toString(boolean inplan)
	{
		String r = "";
		r = r + prohibition.toString(inplan) + " -> " + sanction.toString(inplan);
		//if (r.length()>=5) r = r.substring(0,r.length()-5);	
		return r;
	}
	
	public String toString()
	{
		return toString(false);
	}
	
	public String toRTF(boolean inplan)
	{
		if (prohibition.size()<=0) return "";
		
		String r = "";
		String s = "\\cf1  and \\cf0 ";
	
			r = r + prohibition.toRTF(inplan) + " -> " + sanction.toRTF(inplan) + s;
			
	
			
		if (r.length()>=s.length()) r = r.substring(0,r.length()-s.length());	
		
		return r;
	}


	public Byte getPriority() {
		
		return priority;
	}


	public boolean existIn(PlanSeq ps, BeliefUpdates bu) {

        for (BeliefUpdate b : bu.getRules())
        {
        	for (Literal l : b.getPost())
        	{
        		if (l == this.sanction)
        		{
        			for (Plan p : ps.getPlans())
            		{
            			//TODO!!!!
            			String plan = p.toString().toLowerCase().trim();
            			String action = b.getAct().toString().toLowerCase().trim();
            			if (plan.contains(action))
            			{
            				return true;
            			}
            		}
        		}
        	}
        }
		return false;
	}


	public Literal getSanction() {

		return sanction;
	}


	public void setPriority(byte priority) {

		this.priority = priority;
	}
	
	

}
