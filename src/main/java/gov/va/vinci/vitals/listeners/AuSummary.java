package gov.va.vinci.vitals.listeners;

import java.util.Map;

import org.apache.uima.collection.EntityProcessStatus;

import com.google.gson.JsonSyntaxException;

import gov.va.vinci.leo.listener.AuSummaryListener;

public class AuSummary extends AuSummaryListener {

	public AuSummary(Map<String, String> auAnnotationMap) throws JsonSyntaxException {
	  super(auAnnotationMap);
	  // TODO Auto-generated constructor stub
  }

	@Override
  public void collectionProcessComplete(EntityProcessStatus aStatus) {
	  // TODO Auto-generated method stub
	  super.collectionProcessComplete(aStatus);
	  this.outputStatsToConsole();
  }

}
