package apapl.deliberation;

import apapl.*;
import apapl.program.*;
import apapl.data.APLFunction;
import apapl.plans.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The deliberation step in which plans are executed. In this step the first
 * action of all the plans the module has currently adopted is executed.
 */
public class ExecuteAllPlans implements DeliberationStep
{
	ArrayList<AbstractAction> abstractActions = new ArrayList<AbstractAction>();
	
	/**
	 * Executes the first action of all plans in the planbase.
	 * 
	 * @return the result object containing information about this step.
	 **/
	public DeliberationResult execute(APLModule module)
	{
		ExecuteAllPlansResult result = new ExecuteAllPlansResult();
		Planbase planbase = module.getPlanbase();
		Goalbase gb = module.getGoalbase();
		Beliefbase bb = module.getBeliefbase();

		ArrayList<PlanSeq> toRemove = new ArrayList<PlanSeq>();
		
	
		
		for (PlanSeq ps : planbase) 
		{	// If the goal is still a goal of the module, execute the first plan of
			// this sequence. Otherwise, remove the plan sequence.
			if (ps.testActivationGoal(gb,bb))
			{	
				 if (ps.isAtomic() && module.getAtomic() != ps)
					continue;
				 if (ps.getProhibited())
					continue;
				 
				LinkedList<Plan> plans = ps.getPlans();
			
				PlanResult r = null;

				if (plans.size() > 0)
				{ Plan p = plans.getFirst();
			    
					// Some plans (e.g. atomic plans) might cause the module to have
					// achieved its goal while executing the plan. In such a case an
					// ActivationGoalAchievedException is thrown.
					try
					{	
						r = p.execute(module);
					
	
						// If after execution the plan becomes empty
						if( ps.isEmpty() ) toRemove.add(ps);

						// Generate an internal event in case of execution failure
						if (r.failed())
		 				{ module.notifyIEvent(ps.getID());
						}
						result.addPlanResult(r);
					}
					catch (ActivationGoalAchievedException e) 
					{ 
						// When the goal has been achieved, the plan executed successfully
						toRemove.add(ps);
						result.addPlanResult(new PlanResult(p, PlanResult.SUCCEEDED, "Activation goal has been achieved."));
		 			}
					catch (ModuleDeactivatedException e)
					{
						// Show action as successful in log
						result.addPlanResult(new PlanResult(p, PlanResult.SUCCEEDED, "Module has lost its execution control."));
						// Execute no more plans in this deliberation step
						break;
					}
				}	
			}
			else
			{ toRemove.add(ps);
			}
		}

		// Remove empty plans and plans of which the goal is achieved
		for (PlanSeq ps : toRemove) 
		{
			planbase.removePlan(ps);
			//module.getSchedule().remove(ps);
		}

		return( result );
	}
	private void findAA(){
		
	}
	
	public void threading() {
		 ExecutorService executor = Executors.newFixedThreadPool(3);
		    List<Future<PlanResult>> list = new ArrayList<Future<PlanResult>>();
		    for (int i = 0; i < abstractActions.size(); i++) {
		      Callable<PlanResult> worker = abstractActions.get(i);
		      abstractActions.remove(i);
		      Future<PlanResult> submit = executor.submit(worker);
		      list.add(submit);
		    }

		    //System.out.println(list.size());
		    // Now retrieve the result
		    for (Future<PlanResult> future : list) {
		      try {
		        future.get();
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      } catch (ExecutionException e) {
		        e.printStackTrace();
		      }
		    }
		   
		    executor.shutdown();
	}
	public String toString()
	{
		return "Execute Plans";
	}
}
